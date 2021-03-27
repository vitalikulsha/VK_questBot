package io.github.vitalikulsha.telegrambot.botapi;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface InputMessage {
    SendMessage handle(Message message);

    BotState getHandlerName();
}
