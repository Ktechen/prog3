package controller.event.events.commands.update;

import controller.event.EventHandler;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.update.EventUpdateCounter;
import controller.handleInput.InputConverter;

public class CommandUpdateEvent extends CommandEvent {

    public CommandUpdateEvent(InputConverter converter, EventHandler eventHandler) {
        super(converter, eventHandler);
    }

    public void eventUpdateCounter(String address) {
        EventUpdateCounter eventUpdateCounter = new EventUpdateCounter(this, address);
        eventHandler.handle(eventUpdateCounter);
    }
}
