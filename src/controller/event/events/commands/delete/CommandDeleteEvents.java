package controller.event.events.commands.delete;

import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.delete.EventDeletePerString;
import controller.event.events.listener.delete.ELDeleteVideoPerAdress;
import controller.event.events.listener.delete.ELDeleteVideoPerUser;
import controller.handleInput.InputConverter;
import modell.data.storage.Storage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandDeleteEvents extends CommandEvent {

    private String value;

    public CommandDeleteEvents(InputConverter converter, EventHandler<EventListener> eventHandler, String value) {
        super(converter, eventHandler);
        this.value = value;
    }

    public void eventDelete() {
        EventDeletePerString eventDeletePerString = new EventDeletePerString(this, value);
        ELDeleteVideoPerAdress elDeleteVideoPerAdress = new ELDeleteVideoPerAdress();
        ELDeleteVideoPerUser elDeleteVideoPerUser = new ELDeleteVideoPerUser();

        Pattern pattern = Pattern.compile(Storage.TYPE_OF_SOURCE);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
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
