package net.server.tcp.serverCommands;

import controller.event.EventHandler;
import controller.event.events.commands.CommandDeleteEvents;
import controller.handleInput.InputConverter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandServerDelete extends CommandServer {


    public CommandServerDelete(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    public void run() throws IOException {
        this.sendMessage(InputConverter.DELETE_USER + "\n" + InputConverter.DELETE_ADDRESSE);
        this.handleArgs(this.getMessage().toString());
    }

    private void handleArgs(String args) {
        new CommandDeleteEvents(new InputConverter(), new EventHandler<>(), args).eventDelete();
    }
}
