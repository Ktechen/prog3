package controller.event.events.commands.show;

import controller.event.EventHandler;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.show.EventShowUsedTags;
import controller.event.events.listener.show.ELShowUsedTags;
import controller.handleInput.InputConverter;

public class CommandShowEventUsedTags extends CommandEvent {
    public CommandShowEventUsedTags(InputConverter converter, EventHandler eventHandler) {
        super(converter, eventHandler);
    }

    public void eventusedTags() {
        EventShowUsedTags eventShowUsedTags = new EventShowUsedTags(this);
        eventHandler.handle(eventShowUsedTags);
    }
}
