package controller.event.events.commands.update;

import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.update.EventUpdateCounter;
import controller.event.events.listener.update.ELUpdateCounter;
import controller.handleInput.InputConverter;

public class CommandUpdateEvent extends CommandEvent {


    public CommandUpdateEvent(InputConverter converter, EventHandler<EventListener> eventHandler) {
        super(converter, eventHandler);
    }

    public void eventUpdateCounter(String address) {
        EventUpdateCounter eventUpdateCounter = new EventUpdateCounter(this, address);
        ELUpdateCounter elUpdateCounter = new ELUpdateCounter();
        eventHandler.add(elUpdateCounter);
        eventHandler.handle(eventUpdateCounter);
    }
}
