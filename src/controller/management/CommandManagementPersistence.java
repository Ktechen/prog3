package controller.management;

import controller.handleInput.InputConverter;
import controller.handleInput.stream.StreamOptions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandManagementPersistence extends CommandManagement implements Command {

    public static final String SEND_MSG = InputConverter.PERSISTENCE_TEXT;

    public CommandManagementPersistence(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    /**
     * Without Network Data Stream
     */
    public CommandManagementPersistence() {
        super(null, null);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(InputConverter.PERSISTENCE_TEXT);
        this.handleArgs(this.getMessage());
    }

    @Override
    public void handleArgs(String args) throws IOException {
        try {
            StreamOptions streamOptions = new StreamOptions();
            streamOptions.run(args);
        } catch (NullPointerException | IllegalArgumentException e) {
            if (!isOffline()) {
                this.sendMessage(e.getMessage());
            } else {
                System.out.println(e.getMessage());
            }
        }
    }
}
