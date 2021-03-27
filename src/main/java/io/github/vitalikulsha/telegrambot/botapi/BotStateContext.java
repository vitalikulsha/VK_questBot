package io.github.vitalikulsha.telegrambot.botapi;

import java.util.HashMap;
import java.util.Map;

public class BotStateContext {
    private Map<BotState, InputMessage> messageHandlers = new HashMap<>();
}
