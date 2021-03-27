package io.github.vitalikulsha.telegrambot.util;

public enum BotCorrectAnswer {
    ANSWER_ONE("Звезда"),
    ANSWER_TWO("Зодиак"),
    ANSWER_THREE("Созвездие");
    String command;

    public String getCommand() {
        return command;
    }

    BotCorrectAnswer(String command) {
        this.command = command;
    }

}
