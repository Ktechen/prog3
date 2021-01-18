package net.server.tcp.serverCommands;

import java.io.*;

public class CommandServerPersistence extends CommandServer implements Command{


    public CommandServerPersistence(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    @Override
    public void run() {
    }

    @Override
    public void handleArgs(String args) throws IOException {

    }
}
