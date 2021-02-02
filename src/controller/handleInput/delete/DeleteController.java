package controller.handleInput.delete;

import controller.crud.Delete;
import controller.event.EventHandler;
import controller.event.events.commands.delete.CommandDeleteEventAddress;
import controller.event.events.commands.delete.CommandDeleteEventUser;
import controller.event.events.listener.delete.ELDeleteVideoPerAdress;
import controller.event.events.listener.delete.ELDeleteVideoPerUser;
import controller.handleInput.CommandController;
import controller.handleInput.InputConverter;

final class DeleteController implements CommandController {

    private final Delete delete;
    private CommandDeleteEventAddress commandDeleteEventsAddress;
    private CommandDeleteEventUser commandDeleteEventUser;

    public DeleteController() {
        this.delete = new Delete();
        this.config();
    }

    public final void deleteViaUser(String user) {
        this.commandDeleteEventUser.eventDeletePerAddress(user);
    }

    public final void deleteViaAddress(String address) {
        this.commandDeleteEventsAddress.eventDeletePerAddress(address);
    }

    @Override
    public void config() {
        EventHandler<ELDeleteVideoPerAdress> address = new EventHandler<>();
        ELDeleteVideoPerAdress elDeleteVideoPerAdress = new ELDeleteVideoPerAdress();
        address.add(elDeleteVideoPerAdress);

        EventHandler<ELDeleteVideoPerUser> user = new EventHandler<>();
        ELDeleteVideoPerUser elDeleteVideoPerUser = new ELDeleteVideoPerUser();
        user.add(elDeleteVideoPerUser);

        this.commandDeleteEventsAddress = new CommandDeleteEventAddress(new InputConverter(), address);
        this.commandDeleteEventUser = new CommandDeleteEventUser(new InputConverter(), user);
    }
}
