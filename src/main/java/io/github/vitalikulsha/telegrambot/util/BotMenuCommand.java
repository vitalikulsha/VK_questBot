package io.github.vitalikulsha.telegrambot.util;

public enum BotMenuCommand {
    START_QUEST("Начать квест"),
    HINT("Подсказка"),
    HELP("Справка");
    String command;

    public String getCommand() {
        return command;
    }

    BotMenuCommand(String command) {
        this.command = command;
    }
}
