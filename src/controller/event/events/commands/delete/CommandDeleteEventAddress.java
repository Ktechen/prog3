package controller.event.events.commands.delete;

import controller.event.EventHandler;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.delete.EventDeletePerAddress;
import controller.event.events.listener.delete.ELDeleteVideoPerAdress;
import controller.handleInput.InputConverter;

public class CommandDeleteEventAddress extends CommandEvent<ELDeleteVideoPerAdress> {
    public CommandDeleteEventAddress(InputConverter converter, EventHandler<ELDeleteVideoPerAdress> eventHandler) {
        super(converter, eventHandler);
    }

    public void eventDeletePerAddress(String address) {
        EventDeletePerAddress eventDeletePerAddress = new EventDeletePerAddress(this, address);
        eventHandler.handle(eventDeletePerAddress);
    }
}
