package controller.event.events.commands;

import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.event.delete.EventDeletePerString;
import controller.event.events.listener.delete.ELDeleteVideoPerAdress;
import controller.event.events.listener.delete.ELDeleteVideoPerUser;
import controller.handleInput.InputConverter;
import modell.data.storage.Storage;

public class CommandDeleteEvents {

    private InputConverter converter;
    private EventHandler<EventListener> eventHandler;
    private final String value;

    public CommandDeleteEvents(InputConverter converter, EventHandler<EventListener> eventHandler, final String value) {
        this.converter = converter;
        this.eventHandler = eventHandler;
        this.value = value;
    }

    public void setHandler(EventHandler<controller.event.EventListener> handler) {
        this.eventHandler = handler;
    }

    public void eventDelete() {
        EventDeletePerString eventDeletePerString = new EventDeletePerString(this, value);
        ELDeleteVideoPerAdress elDeleteVideoPerAdress = new ELDeleteVideoPerAdress();
        ELDeleteVideoPerUser elDeleteVideoPerUser = new ELDeleteVideoPerUser();

        if (value.indexOf(Storage.TYPE_OF_SOURCE) == 0) {
            eventHandler.add(elDeleteVideoPerAdress);
        } else {
            eventHandler.add(elDeleteVideoPerUser);
        }

        setHandler(eventHandler);

        if (null != this.eventHandler) {
            eventHandler.handle(eventDeletePerString);
        }
    }
}
