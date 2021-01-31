package controller.event.events.commands.add;

import controller.event.EventHandler;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.add.EventAddUploader;
import controller.event.events.listener.add.ELAddUploader;
import controller.handleInput.InputConverter;

public class CommandAddEventsUploader extends CommandEvent<ELAddUploader> {

    public CommandAddEventsUploader(InputConverter converter, EventHandler<ELAddUploader> eventHandler) {
        super(converter, eventHandler);
    }

    public void eventUser(String arr) {
        //TODO serial per TCP or UDP
        EventAddUploader eventAddUploader = new EventAddUploader(this, arr);
        eventHandler.handle(eventAddUploader);
    }
}
