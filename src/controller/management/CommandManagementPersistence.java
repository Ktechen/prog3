package controller.management;

import controller.handleInput.InputConverter;

import java.io.*;

public class CommandManagementPersistence extends CommandManagement implements Command{

    public static final String SEND_MSG = InputConverter.PERSISTENCE_TEXT;

    public CommandManagementPersistence(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    public CommandManagementPersistence() {
        super(null, null);
    }

    @Override
    public void run() {
    }

    @Override
    public void handleArgs(String args) throws IOException {

    }
}
