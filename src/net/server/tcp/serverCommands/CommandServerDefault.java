package net.server.tcp.serverCommands;

import controller.crud.Create;
import controller.observer.observers.ObserverConsoleSize;
import controller.observer.observers.ObserverConsoleTags;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandServerDefault extends CommandServer implements Command {

    public CommandServerDefault(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage("Input was incorrect try again");
    }

    @Override
    public void handleArgs(String args) throws IOException {

    }
}
