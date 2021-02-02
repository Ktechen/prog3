package controller.handleInput.update;

import controller.event.EventHandler;
import controller.event.events.commands.update.CommandUpdateEvent;
import controller.event.events.listener.update.ELUpdateCounter;
import controller.handleInput.CommandController;
import controller.handleInput.InputConverter;

final class UpdateController implements CommandController {

    private CommandUpdateEvent commandUpdateEvent;

    public UpdateController() {
        this.config();
    }

    public final void update(String address){
        this.commandUpdateEvent.eventUpdateCounter(address);
    }

    @Override
    public void config() {
        EventHandler<ELUpdateCounter> handler = new EventHandler<>();
        ELUpdateCounter elUpdateCounter = new ELUpdateCounter();
        handler.add(elUpdateCounter);

        this.commandUpdateEvent = new CommandUpdateEvent(new InputConverter(), handler);
    }
}
