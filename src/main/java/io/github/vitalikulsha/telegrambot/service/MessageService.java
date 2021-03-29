package io.github.vitalikulsha.telegrambot.service;

import io.github.vitalikulsha.telegrambot.botapi.HandlerBotState;
import io.github.vitalikulsha.telegrambot.util.BotState;
import io.github.vitalikulsha.telegrambot.model.UserDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Slf4j
public class MessageService {
    private UserDataCache userDataCache;
    @Autowired
    HandlerBotState handlerBotState;

    public MessageService(UserDataCache userDataCache) {
        this.userDataCache = userDataCache;
    }

    public SendMessage onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                log.info("New message from User:{}, chatId: {},  with text: {}",
                        message.getFrom().getUserName(), message.getChatId(), message.getText());
                sendMessage = handleInputMessage(message);
            }
        }
        return sendMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        SendMessage replyMessage = new SendMessage();
        String messageText = message.getText();
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();
        BotState botState = handlerBotState.initBotState(messageText);

        if (userDataCache.getUsersCurrentBotState(userId) == BotState.INITIAL) {
            botState = handlerBotState.handlerBotStateInitial(replyMessage, messageText, chatId);
        }

        if (userDataCache.getUsersCurrentBotState(userId) == BotState.STEP_01) {
            botState = handlerBotState.handlerBotStateStep01(replyMessage, messageText, chatId);
        }

        if (userDataCache.getUsersCurrentBotState(userId) == BotState.STEP_02) {
            botState = handlerBotState.handlerBotStateStep02(replyMessage, messageText, chatId);
        }

        if (userDataCache.getUsersCurrentBotState(userId) == BotState.STEP_03) {
            botState = handlerBotState.handlerBotStateStep03(replyMessage, messageText, chatId);
        }

        userDataCache.setUsersCurrentBotState(userId, botState);
        return replyMessage;
    }
}
