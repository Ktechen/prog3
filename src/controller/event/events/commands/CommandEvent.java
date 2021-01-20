package controller.event.events.commands;

import controller.event.EventHandler;
import controller.event.EventListener;
import controller.handleInput.InputConverter;

public abstract class CommandEvent {

    InputConverter converter;
    EventHandler<EventListener> eventHandler;

    public CommandEvent(InputConverter converter, EventHandler<EventListener> eventHandler) {
        this.converter = converter;
        this.eventHandler = eventHandler;
    }

    public void setHandler(EventHandler<controller.event.EventListener> handler) {
        this.eventHandler = handler;
    }
}
