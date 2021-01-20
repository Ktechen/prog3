package controller.event.events.commands;

import controller.event.EventHandler;
import controller.event.EventListener;
import controller.handleInput.InputConverter;

public class CommandUpdateEvent extends CommandEvent{

    public CommandUpdateEvent(InputConverter converter, EventHandler<EventListener> eventHandler) {
        super(converter, eventHandler);
    }
}
