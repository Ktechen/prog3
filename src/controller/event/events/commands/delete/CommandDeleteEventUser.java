package controller.event.events.commands.delete;

import controller.event.EventHandler;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.delete.EventDeletePerAddress;
import controller.event.events.event.delete.EventDeletePerUser;
import controller.event.events.listener.delete.ELDeleteVideoPerUser;
import controller.handleInput.InputConverter;

public class CommandDeleteEventUser extends CommandEvent {
    public CommandDeleteEventUser(InputConverter converter, EventHandler eventHandler) {
        super(converter, eventHandler);
    }

    public void eventDeletePerAddress(String user) {
        EventDeletePerUser eventDeletePerUser = new EventDeletePerUser(this, user);
        eventHandler.handle(eventDeletePerUser);
    }
}
