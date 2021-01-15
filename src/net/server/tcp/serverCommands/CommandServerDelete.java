package net.server.tcp.serverCommands;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class CommandServerDelete extends CommandServer{


    public CommandServerDelete(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    public void run() {
    }
}
