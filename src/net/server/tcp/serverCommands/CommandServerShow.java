package net.server.tcp.serverCommands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CommandServerShow extends CommandServer{


    public CommandServerShow(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    public void run() {
    }
}
