package net.server.serverCommands;

import controller.handleInput.InputConverter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandServerDefault extends CommandServer implements Command {

    public CommandServerDefault(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(InputConverter.OPTION_NOT_VALID);
    }

    @Override
    public void handleArgs(String args) throws IOException {

    }
}
