package cli.commands;

import cli.Console;
import cli.commands.options.CommandAdd;
import cli.commands.options.CommandDelete;
import cli.commands.options.CommandShow;
import observer.Observable;
import observer.observers.ObserverConsoleSize;

import java.io.IOException;

public class CommandMain implements ICommand {

    public CommandMain() {
    }

    @Override
    public void run() throws IOException, IllegalMonitorStateException {
        do {
            switch (input()) {
                case ":c":
                    try {
                        CommandAdd commandAdd = new CommandAdd();
                        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(commandAdd);
                        commandAdd.run();
                    } catch (IllegalMonitorStateException | IllegalArgumentException | NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case ":r":
                    new CommandShow().run();
                    break;
                case ":d":
                    new CommandDelete().run();
                    break;
                case ":u":
                    break;
                case ":config":
                    break;
                case ":p":
                    break;
            }
        } while (true);

    }

    private String input() {

        Console console = new Console();

        String value = null;

        try {
            System.out.println(toString());
            value = String.valueOf(console.readInput("Select a Option: "));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return value;
    }

    public String toString() {
        return Keys.ADD.get() + " Wechsel in den Einfügemodus\n" +
                Keys.SHOW.get() + " Wechsel in den Anzeigemodus\n" +
                Keys.DELETE.get() + " Wechsel in den Löschmodus\n" +
                Keys.CHANGE.get() + " Wechsel in den Änderungsmodus\n" +
                Keys.CONFIG.get() + " Wechsel in den Konfigurationsmodus\n" +
                Keys.PERSISTENCE.get() + " Wechsel in den Persistenzmodus";
    }
}
