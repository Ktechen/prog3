package controller.event.events.commands;

import controller.event.EventHandler;
import controller.event.EventListener;
import controller.handleInput.InputConverter;

public abstract class CommandEvent {

    protected InputConverter converter;
    protected EventHandler eventHandler;

    public CommandEvent(InputConverter converter, EventHandler eventHandler) {
        this.converter = converter;
        this.eventHandler = eventHandler;
    }

    public void setHandler(EventHandler handler) {
        this.eventHandler = handler;
    }

}
