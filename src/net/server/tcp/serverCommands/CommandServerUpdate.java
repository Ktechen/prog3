package net.server.tcp.serverCommands;

import java.io.*;

public class CommandServerUpdate extends CommandServer implements Command{

    public CommandServerUpdate(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    @Override
    public void run() throws IOException {

    }

    @Override
    public void handleArgs(String args) throws IOException {

    }
}
