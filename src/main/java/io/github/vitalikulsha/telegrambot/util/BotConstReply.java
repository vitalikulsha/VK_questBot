package io.github.vitalikulsha.telegrambot.util;

public enum BotConstReply {
    HELLO("Тебя приветствует QuestBot! Сыграем?"),
    START("Выбери из предложенного меню."),
    START_QUEST("Итак, начнем! Первое задание."),
    HINT("Запусти квест, чтобы получить подсказки."),
    HELP("Чат-бот поможет пройти тебе квест. Отвечай по порядку на вопросы бота и в конце получишь приз." +
            "Ты всегда можешь обратиться за подсказкой к боту, но только один раз."),
    COMMENT("Правильно! Ты молодец! Держи следующее задание."),
    COMMENT_WIN("Правильно! Это созвездие! Ты выиграл!"),
    UNKNOWN("Эта команда мне неизвестная"),
    N0_RIGHT("Не правильно! Подумай еще!.");

    String command;

    public String getCommand() {
        return command;
    }

    BotConstReply(String command) {
        this.command = command;
    }
}
