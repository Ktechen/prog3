package controller.event.events.commands.show;

import controller.event.EventHandler;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.show.EventShowUsernamePerIndex;
import controller.event.events.listener.show.ELShowUsernamePerIndexValue;
import controller.handleInput.InputConverter;

public class CommandShowEventUsername extends CommandEvent {
    public CommandShowEventUsername(InputConverter converter, EventHandler eventHandler) {
        super(converter, eventHandler);
    }

    public void eventUsername(String name) {
        EventShowUsernamePerIndex eventShowUsernamePerIndex = new EventShowUsernamePerIndex(this, name.replaceAll("\\s", ""));
        eventHandler.handle(eventShowUsernamePerIndex);
    }
}
