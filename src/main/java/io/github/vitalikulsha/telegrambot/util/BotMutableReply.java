package io.github.vitalikulsha.telegrambot.util;

public enum BotMutableReply {
    ANSWER_ONE("Солнце"),
    ANSWER_TWO("Зодиак"),
    ANSWER_THREE("Созвездие"),
    HINT_STEP01("Подсказка: ближайшая к нам звезда"),
    HINT_STEP02("Подсказка: их 12"),
    HINT_STEP03("Подсказка: скопление звезд");

    String command;

    public String getCommand() {
        return command;
    }

    BotMutableReply(String command) {
        this.command = command;
    }
}
