package net.server.tcp.serverCommands;

import java.io.*;

public class CommandServerDefault extends CommandServer {

    public CommandServerDefault(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    public void run() throws IOException {
        this.sendMessage("Input was incorrect try again");
    }
}
