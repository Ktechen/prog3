package controller.event.events.commands.show;

import controller.event.EventHandler;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.show.EventShowAll;
import controller.handleInput.InputConverter;

public class CommandShowEventFilter extends CommandEvent {
    public CommandShowEventFilter(InputConverter converter, EventHandler eventHandler) {
        super(converter, eventHandler);
    }

    public void eventFilter(String filterby) {
        EventShowAll eventShowAll = new EventShowAll(this, filterby);
        eventHandler.handle(eventShowAll);
    }
}
