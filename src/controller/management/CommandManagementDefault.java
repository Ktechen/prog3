package controller.management;

import controller.handleInput.InputConverter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandManagementDefault extends CommandManagement implements Command {

    public static final String SEND_MSG = InputConverter.OPTION_NOT_VALID;

    public CommandManagementDefault(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    /**
     * Without Network Data Stream
     */
    public CommandManagementDefault() {
        super(null, null);
    }

    @Override
    public void run() throws IOException {
        this.handleArgs(SEND_MSG);
    }

    @Override
    public void handleArgs(String args) throws IOException {
        if (!isOffline()) {
            this.sendMessage(args);
        } else {
            System.out.println(args);
        }
    }

}
