package controller.management;

import controller.event.EventHandler;
import controller.event.events.commands.CommandDeleteEvents;
import controller.handleInput.InputConverter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandManagementDelete extends CommandManagement implements Command {

    public static final String SEND_MSG = InputConverter.DELETE_USER + "\n" + InputConverter.DELETE_ADDRESSE;

    public CommandManagementDelete(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(SEND_MSG);
        this.handleArgs(this.getMessage());
    }

    @Override
    public void handleArgs(String args) throws IOException {
        new CommandDeleteEvents(new InputConverter(), new EventHandler<>(), args).eventDelete();
        if (!isOffline()) {
            this.sendMessage(args + " was been deleted");
        } else {
            System.out.println("Element was been deleted");
        }
    }
}
