package controller.management;

import controller.handleInput.InputConverter;
import controller.handleInput.update.UpdateOption;
import modell.data.storage.Storage;

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
        this.handleArgs(this.getMessage());
    }

    @Override
    public void handleArgs(String args) throws IOException {
        try {
            UpdateOption updateOption = new UpdateOption();
            updateOption.run(args);

            if (!isOffline()) {
                this.sendMessage("Click: " + Storage.getInstance().getCountOfUse().get(args));
            } else {
                System.out.println("Click: " + Storage.getInstance().getCountOfUse().get(args));
            }

        } catch (IllegalArgumentException e) {
            if (!isOffline()) {
                this.sendMessage(e.getMessage());
            } else {
                System.err.println(e.getMessage());
            }
        }
    }
}
