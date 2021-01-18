package net.server.tcp.serverCommands;

import java.io.IOException;

public interface Command {
    void run() throws IOException;

    void handleArgs(String args) throws IOException;
}
