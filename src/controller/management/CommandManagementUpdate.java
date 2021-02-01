package controller.management;

import controller.crud.Update;
import controller.event.EventHandler;
import controller.event.events.commands.update.CommandUpdateEvent;
import controller.handleInput.InputConverter;

import java.io.*;

public class CommandManagementUpdate extends CommandManagement implements Command {

    public static final String SEND_MSG = InputConverter.UPDATE_TEXT;

    public CommandManagementUpdate(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    /**
     * Without Network Data Stream
     */
    public CommandManagementUpdate() {
        super(null, null);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(SEND_MSG);
        this.handleArgs(this.getMessage().toString());
    }

    @Override
    public void handleArgs(String args) throws IOException {
        try {
            new CommandUpdateEvent(new InputConverter(), new EventHandler<>()).eventUpdateCounter(args);
            final Update update = new Update();
            long current = update.getAccessCount(args);
            if (!isOffline()) {
                this.sendMessage("Click: " + current);
            } else {
                System.out.println("Click: " + current);
            }

        } catch (IllegalArgumentException e) {
            if (!isOffline()) {
                this.sendMessage(e.getMessage());
            } else {
                e.printStackTrace();
            }
        }
    }
}
