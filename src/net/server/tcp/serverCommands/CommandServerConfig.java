package net.server.tcp.serverCommands;

import controller.crud.Create;
import controller.handleInput.InputConverter;
import controller.observer.Observer;
import controller.observer.observers.ObserverConsoleSize;
import controller.observer.observers.ObserverConsoleTags;
import jdk.internal.util.xml.impl.Input;

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
        this.sendMessage(InputConverter.CONFIG_ADD + "\n" + InputConverter.CONFIG_REMOVE + " e.g " + ObserverConsoleSize.class.getSimpleName());
        this.handleArgs(this.getMessage().toString());
    }

    @Override
    public void handleArgs(String args) throws IOException {
        final Create create = new Create();

        if (args.indexOf("add") == 0) {
            create.join(new ObserverConsoleSize(create));
            this.sendMessage("ObserverConsoleSize was been added");
        } else if (args.indexOf("remove") == 0) {

            if (args.equals(ObserverConsoleSize.class.getSimpleName())) {
                List<Observer> list = create.getList();
                Observer observer = null;

                for (Observer o : list) {
                    if (o instanceof ObserverConsoleSize) {
                        observer = o;
                    }
                }

                create.leave(observer);
                this.sendMessage(ObserverConsoleSize.class.getSimpleName() + " was been removed");

            } else {
                this.sendMessage("Observer not found");
            }
        } else {
            this.sendMessage("Command not found");
        }
    }

}
