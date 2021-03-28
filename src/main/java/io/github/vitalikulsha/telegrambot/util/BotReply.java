package io.github.vitalikulsha.telegrambot.util;

public enum BotReply {
    HELLO("Тебя приветствует QuestBot! Сыграем?"),
    START("Выбери из предложенного меню."),
    START_QUEST("Задание №1"),
    HINT("Запусти квест, чтобы получить подсказки."),
    HINT_STEP01("Подсказка: ближайшая к нам звезда"),
    HINT_STEP02("Подсказка: их 12"),
    HINT_STEP03("Подсказка: скопление звезд"),
    HELP("Чат-бот поможет пройти тебе квест. Отвечай по порядку на вопросы бота и в конце получишь приз." +
            "Ты всегда можешь обратиться за подсказкой к боту, но только один раз.");

    String command;

    public String getCommand() {
        return command;
    }

    BotReply(String command) {
        this.command = command;
    }
}
