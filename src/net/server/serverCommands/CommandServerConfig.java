package net.server.serverCommands;

import controller.crud.Create;
import controller.handleInput.InputConverter;
import controller.observer.Observer;
import controller.observer.observers.ObserverConsoleSize;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class CommandServerConfig extends CommandServer implements Command {

    public CommandServerConfig(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(InputConverter.CONFIG_ADD_TEXT + "\n" + InputConverter.CONFIG_REMOVE_TEXT + " e.g " + ObserverConsoleSize.class.getSimpleName());
        this.handleArgs(this.getMessage().toString());
    }

    @Override
    public void handleArgs(String args) throws IOException {
        final Create create = new Create();

        if (args.indexOf("add") == 0) {
            create.join(new ObserverConsoleSize(create));
            this.sendMessage(InputConverter.CONFIG_ADD_TEXT_VIEW);
        } else if (args.indexOf("remove") == 0) {
            if (args.equals(ObserverConsoleSize.class.getSimpleName())) {
                create.leave(this.foundInstanceOf(ObserverConsoleSize.class, create));
                this.sendMessage(InputConverter.CONFIG_REMOVE_TEXT_VIEW);

            } else {
                this.sendMessage(InputConverter.CONFIG_NOT_FOUND);
            }
        } else {
            this.sendMessage(InputConverter.OPTION_NOT_VALID);
        }
    }

    private Observer foundInstanceOf(Class c, Create create) {
        List<Observer> list = create.getList();
        Observer observer = null;

        for (Observer o : list) {
            if (o instanceof ObserverConsoleSize) {
                observer = o;
            }
        }

        return observer;
    }
}
