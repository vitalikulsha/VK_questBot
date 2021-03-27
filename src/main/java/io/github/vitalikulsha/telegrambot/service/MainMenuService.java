package io.github.vitalikulsha.telegrambot.service;

import io.github.vitalikulsha.telegrambot.util.BotMenuCommand;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainMenuService {
    private ReplyKeyboardMarkup replyKeyboardMarkup;

    public ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow rowFirst = new KeyboardRow();
        KeyboardRow rowSecond = new KeyboardRow();
        rowFirst.add(BotMenuCommand.START_QUEST.getCommand());
        rowSecond.add(BotMenuCommand.HINT.getCommand());
        rowSecond.add(BotMenuCommand.HELP.getCommand());
        keyboard.add(rowFirst);
        keyboard.add(rowSecond);
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

}
