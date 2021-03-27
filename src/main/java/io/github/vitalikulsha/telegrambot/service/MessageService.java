package io.github.vitalikulsha.telegrambot.service;

import io.github.vitalikulsha.telegrambot.util.BotCorrectAnswer;
import io.github.vitalikulsha.telegrambot.util.BotMenuCommand;
import io.github.vitalikulsha.telegrambot.util.BotReply;
import io.github.vitalikulsha.telegrambot.botapi.BotState;
import io.github.vitalikulsha.telegrambot.model.UserDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Slf4j
public class MessageService {
    private UserDataCache userDataCache;
    //BotState botState;

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
        BotState botState;
        String msg;
        if (messageText.equals("/start")) {
            botState = BotState.INITIAL;
            msg = BotReply.START.getCommand();
        } else if (messageText.equalsIgnoreCase(BotMenuCommand.START_QUEST.getCommand())) {
            botState = BotState.STEP_01;
            msg = BotReply.START_QUEST.getCommand();
        } else if (messageText.equals(BotMenuCommand.HELP.getCommand())) {
            botState = BotState.INITIAL;
            msg = BotReply.HELP.getCommand();
        } else if (messageText.equals(BotMenuCommand.HINT.getCommand())) {
            botState = BotState.STEP_03;
            msg = BotReply.HINT.getCommand();
        } else {
            botState = BotState.INITIAL;
            msg = "Do no";
        }

        if (userDataCache.getUsersCurrentBotState(userId) == BotState.STEP_01) {
            if (messageText.equalsIgnoreCase(BotCorrectAnswer.ANSWER_ONE.getCommand())) {
                botState = BotState.STEP_02;
                msg = "Right! This Star! Next task";
            } else if (messageText.equals(BotMenuCommand.HINT.getCommand())) {
                botState = BotState.STEP_01;
                msg = BotReply.HINT_STEP01.getCommand();
            } else {
                botState = BotState.STEP_01;
                msg = "No right!";
            }
        }

        if (userDataCache.getUsersCurrentBotState(userId) == BotState.STEP_02) {
            if (messageText.equalsIgnoreCase(BotCorrectAnswer.ANSWER_TWO.getCommand())) {
                botState = BotState.STEP_03;
                msg = "Right! This Zodiac! Next task";
            } else if (messageText.equals(BotMenuCommand.HINT.getCommand())) {
                botState = BotState.STEP_02;
                msg = BotReply.HINT_STEP02.getCommand();
            } else {
                botState = BotState.STEP_02;
                msg = "No right!";
            }
        }

        if (userDataCache.getUsersCurrentBotState(userId) == BotState.STEP_03) {
            if (messageText.equalsIgnoreCase(BotCorrectAnswer.ANSWER_THREE.getCommand())) {
                botState = BotState.INITIAL;
                msg = "Right! This Constellation! You win!";
            } else if (messageText.equals(BotMenuCommand.HINT.getCommand())) {
                botState = BotState.STEP_03;
                msg = BotReply.HINT_STEP03.getCommand();
            } else {
                botState = BotState.STEP_03;
                msg = "No right!";
            }
        }

        userDataCache.setUsersCurrentBotState(userId, botState);
        return replyMessage.setText(msg);
    }
}
