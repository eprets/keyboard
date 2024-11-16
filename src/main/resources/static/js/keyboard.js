// Функция для добавления символа в поле ввода
function addToInput(char) {
    var inputField = document.getElementById('userInput');
    inputField.value += char;
}

// Слушаем физическую клавиатуру
document.addEventListener('keydown', function(event) {
    var inputField = document.getElementById('userInput');
    var char = event.key.toUpperCase();

    if (/[A-Z ]/.test(char)) {
        inputField.value += char;
    }
});
