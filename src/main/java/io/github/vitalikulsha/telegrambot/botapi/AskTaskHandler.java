package io.github.vitalikulsha.telegrambot.botapi;

import io.github.vitalikulsha.telegrambot.model.UserDataCache;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AskTaskHandler implements InputMessage {
    private UserDataCache userDataCache;

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return null;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();
        userDataCache.setUsersCurrentBotState(userId, BotState.INITIAL);

        return null;
    }
}
