package controller.event.events.commands;

import controller.event.EventHandler;
import controller.event.EventListener;
import controller.handleInput.InputConverter;

public abstract class CommandEvent<T extends EventListener> {

    protected InputConverter converter;
    protected EventHandler<T> eventHandler;

    public CommandEvent(InputConverter converter, EventHandler<T> eventHandler) {
        this.converter = converter;
        this.eventHandler = eventHandler;
    }

    public void setHandler(EventHandler<T> handler) {
        this.eventHandler = handler;
    }
}
