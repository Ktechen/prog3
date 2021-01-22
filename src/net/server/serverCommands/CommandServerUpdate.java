package net.server.serverCommands;

import controller.crud.Update;
import controller.event.EventHandler;
import controller.event.events.commands.CommandDeleteEvents;
import controller.event.events.commands.CommandUpdateEvent;
import controller.handleInput.InputConverter;

import java.io.*;

public class CommandServerUpdate extends CommandServer implements Command {

    public CommandServerUpdate(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(InputConverter.UPDATE_TEXT);
        this.handleArgs(this.getMessage().toString());
    }

    @Override
    public void handleArgs(String args) throws IOException {
        try {
            new CommandUpdateEvent(new InputConverter(), new EventHandler<>()).eventUpdateCounter(args);
            final Update update = new Update();
            long current = update.getAccessCount(args);
            this.sendMessage("Click: " + current);
        } catch (IllegalArgumentException e) {
            this.sendMessage(e.getMessage());
        }
    }
}
