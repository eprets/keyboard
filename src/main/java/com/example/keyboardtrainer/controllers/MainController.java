package com.example.keyboardtrainer.controllers;

import com.example.keyboardtrainer.models.*;
import com.example.keyboardtrainer.services.UserService;
import com.example.keyboardtrainer.services.TrainingService;
import com.example.keyboardtrainer.repositories.UserRepository;
import com.example.keyboardtrainer.repositories.LevelRepository;
import com.example.keyboardtrainer.repositories.ProgressRepository;
import com.example.keyboardtrainer.repositories.ExerciseCategoryRepository;
import com.example.keyboardtrainer.repositories.ExerciseTypeRepository;
import com.example.keyboardtrainer.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private ExerciseCategoryRepository exerciseCategoryRepository;

    @Autowired
    private ExerciseTypeRepository exerciseTypeRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private ProgressRepository progressRepository;


    // Главная страница
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Страница входа
    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/home";
        }
        return "login";
    }

    // Страница регистрации
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    // Регистрация пользователя
    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email,
                               Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);  // Убедитесь, что пароль будет захеширован
        user.setEmail(email);

        try {
            userService.registerUser(user);  // Регистрация пользователя
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    // Страница выбора параметров тренировки
    @GetMapping("/start-training")
    public String startTrainingPage(Model model) {
        List<ExerciseCategory> categories = exerciseCategoryRepository.findAll();
        List<ExerciseType> types = exerciseTypeRepository.findAll();
        List<Level> levels = levelRepository.findAll();  // Используем levelRepository

        // Убираем проверку на пустые данные здесь
        model.addAttribute("categories", categories);
        model.addAttribute("types", types);
        model.addAttribute("levels", levels);
        model.addAttribute("category", new ExerciseCategory());  // Инициализация категории
        model.addAttribute("type", new ExerciseType());          // Инициализация типа
        model.addAttribute("level", new Level());                // Инициализация уровня

        return "start-training";
    }

    // Начало тренировки после выбора параметров
    @PostMapping("/start-training")
    public String startTraining(@RequestParam("category") Long categoryId,
                                @RequestParam("type") Long typeId,
                                @RequestParam("level") Long levelId,
                                Model model) {
        // Выводим параметры в лог для отладки
        System.out.println("Selected Category ID: " + categoryId);
        System.out.println("Selected Type ID: " + typeId);
        System.out.println("Selected Level ID: " + levelId);

        // Ищем категории, типы и уровни по ID
        ExerciseCategory category = exerciseCategoryRepository.findById(categoryId).orElse(null);
        ExerciseType type = exerciseTypeRepository.findById(typeId).orElse(null);
        Level level = levelRepository.findById(levelId).orElse(null);

        // Проверка на null
        if (category == null || type == null || level == null) {
            model.addAttribute("error", "Выберите корректные параметры тренировки");
            return "start-training";  // Вернуть пользователя обратно, если параметры не выбраны
        }

        // Получаем список упражнений по выбранной категории, типу и уровню
        List<Exercise> exercises = exerciseRepository.findByCategoryAndTypeAndLevel(category, type, level);

        // Проверяем, что упражнения есть
        if (exercises.isEmpty()) {
            model.addAttribute("error", "Нет доступных упражнений для выбранных параметров");
            return "start-training"; // Возвращаем на страницу выбора, если упражнений нет
        }

        // Выбираем одно упражнение для тренировки (например, первое)
        Exercise exercise = exercises.get(0);

        model.addAttribute("exercise", exercise);
        model.addAttribute("category", category);
        model.addAttribute("type", type);
        model.addAttribute("level", level);

        return "trainer"; // Возвращаем страницу тренировки
    }


    // Страница тренировки
    @GetMapping("/trainer")
    public String trainer(@RequestParam("category") Long categoryId,
                          @RequestParam("type") Long typeId,
                          @RequestParam("level") Long levelId,  // Добавляем уровень
                          Model model) {
        // Получаем объекты категории, типа и уровня из базы данных
        ExerciseCategory category = exerciseCategoryRepository.findById(categoryId).orElse(null);
        ExerciseType type = exerciseTypeRepository.findById(typeId).orElse(null);
        Level level = levelRepository.findById(levelId).orElse(null); // Получаем уровень

        if (category == null || type == null || level == null) {
            model.addAttribute("error", "Неверно выбранные параметры тренировки");
            return "start-training"; // Возвращаем на страницу выбора, если параметры некорректны
        }

        // Получаем список упражнений по выбранной категории, типу и уровню
        List<Exercise> exercises = exerciseRepository.findByCategoryAndTypeAndLevel(category, type, level);

        if (exercises.isEmpty()) {
            model.addAttribute("error", "Нет доступных упражнений для выбранных параметров");
            return "start-training"; // Возвращаем на страницу выбора, если упражнений нет
        }

        // Выбираем одно упражнение для тренировки
        Exercise exercise = exercises.get(0); // Можно выбрать первое или рандомное упражнение

        model.addAttribute("exercise", exercise);
        model.addAttribute("category", category);
        model.addAttribute("type", type);
        model.addAttribute("level", level);


        return "trainer"; // Возвращаем страницу тренировки
    }

    // Проверка ответа пользователя
    @PostMapping("/check-answer")
    public String checkAnswer(@RequestParam("userInput") String userInput,
                              @RequestParam("exerciseId") Long exerciseId,
                              @RequestParam("accuracy") int accuracy,
                              @RequestParam("speed") int speed,
                              Model model) {
        // Получаем текущего пользователя
        User user = getCurrentUser();
        if (user == null) {
            return "redirect:/login";  // Если пользователь не авторизован, перенаправляем на страницу входа
        }

        // Получаем упражнение по ID
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);
        if (exercise == null) {
            model.addAttribute("message", "Упражнение не найдено.");
            return "trainer";
        }


        // Проверяем ответ
        if (userInput.trim().equalsIgnoreCase(exercise.getCorrectAnswer())) {
            model.addAttribute("message", "Ответ правильный!");

            // Получаем или создаем запись прогресса для пользователя
            Progress progress = progressRepository.findByUserIdAndExerciseId(user.getId(), exerciseId)
                    .orElse(new Progress());  // Если записи нет, создаем новую

            // Обновляем прогресс
            progress.setUserId(user.getId());
            progress.setExerciseId(exercise.getId());
            progress.setCategoryId(exercise.getCategory().getId());
            progress.setTypeId(exercise.getType().getId());
            progress.setLevelId(exercise.getLevel().getId());

            // Средняя точность и скорость
            progress.setAccuracy((progress.getAccuracy() + accuracy) / 2);
            progress.setSpeed((progress.getSpeed() + speed) / 2);

            // Считаем ошибки
            progress.setErrors(progress.getErrors() + (userInput.length() != exercise.getCorrectAnswer().length() ? 1 : 0));

            // Время тренировки в миллисекундах
            progress.setCompletionTime((int) (System.currentTimeMillis() / 1000)); // Время в секундах

            // Сохраняем или обновляем прогресс в базе
            progressRepository.save(progress);
        } else {
            model.addAttribute("message", "Ответ неправильный, попробуйте снова.");
        }

        return "trainer";
    }


    // Страница прогресса пользователя
    @GetMapping("/progress")
    public String progress(@RequestParam(value = "exerciseId", required = false) Long exerciseId, Model model) {
        // Получаем текущего пользователя
        User user = getCurrentUser();

        if (user == null) {
            return "redirect:/login";  // Если пользователь не авторизован, перенаправляем на страницу входа
        }

        List<Progress> progressList;
        if (exerciseId != null) {
            // Получаем прогресс для конкретного упражнения
            Optional<Progress> progress = progressRepository.findByUserIdAndExerciseId(user.getId(), exerciseId);
            progressList = progress.map(List::of).orElseGet(List::of); // Если прогресс найден, возвращаем его в списке
        } else {
            // Получаем прогресс по всем упражнениям для пользователя
            progressList = progressRepository.findByUserId(user.getId());
        }

        // Добавляем данные о прогрессе в модель
        model.addAttribute("progressList", progressList);

        return "progress";  // Отображаем страницу прогресса
    }


    // Главная страница пользователя после входа
    @GetMapping("/home")
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "home";
    }

    // Личный кабинет пользователя
    @GetMapping("/personal-cabinet")
    public String getPersonalCabinet(Model model) {
        User user = getCurrentUser();

        if (user == null) {
            return "redirect:/login";
        }

        String progress = userService.getProgressForUser(user);

        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("progress", progress);

        return "personal-cabinet";
    }

    // Получение текущего пользователя
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username);
    }
}