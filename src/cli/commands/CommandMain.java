package cli.commands;

import cli.Console;
import cli.commands.options.CommandAdd;
import cli.commands.options.CommandDelete;
import cli.commands.options.CommandShow;
import cli.commands.options.CommandUpdate;
import observer.observers.ObserverConsoleSize;

import java.io.IOException;

public class CommandMain implements ICommand {

    public CommandMain() {
    }

    @Override
    public void run() throws IOException {

        Console console = new Console();

        do {
            System.out.println(toString());
            switch (console.input("-------------")) {
                case ":c":
                    try {
                        CommandAdd commandAdd = new CommandAdd();
                        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(commandAdd);
                        commandAdd.run();
                    } catch (IllegalArgumentException | NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case ":r":
                    try {
                        new CommandShow().run();
                    } catch (IllegalArgumentException | NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case ":d":
                    try {
                        new CommandDelete().run();
                    } catch (IllegalArgumentException | NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case ":u":
                    new CommandUpdate().run();
                    break;
                case ":config":
                    break;
                case ":p":
                    break;
            }
        } while (true);

    }

    public String toString() {
        return "\n" + Keys.ADD.get() + " Wechsel in den Einfügemodus\n" +
                Keys.SHOW.get() + " Wechsel in den Anzeigemodus\n" +
                Keys.DELETE.get() + " Wechsel in den Löschmodus\n" +
                Keys.CHANGE.get() + " Wechsel in den Änderungsmodus\n" +
                Keys.CONFIG.get() + " Wechsel in den Konfigurationsmodus\n" +
                Keys.PERSISTENCE.get() + " Wechsel in den Persistenzmodus";
    }
}
