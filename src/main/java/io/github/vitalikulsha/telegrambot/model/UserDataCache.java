package io.github.vitalikulsha.telegrambot.model;

import io.github.vitalikulsha.telegrambot.util.BotState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCache {
    private Map<Integer, BotState> userBotStates = new HashMap<>();

    public void setUsersCurrentBotState(int userId, BotState botState) {
        userBotStates.put(userId, botState);
    }

    public BotState getUsersCurrentBotState(int userId) {
        BotState botState = userBotStates.get(userId);
        if (botState == null) {
            botState = BotState.INITIAL;
        }
        return botState;
    }

}
