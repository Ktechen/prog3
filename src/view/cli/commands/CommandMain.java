package view.cli.commands;

import view.cli.Console;
import view.cli.commands.options.CommandAdd;
import view.cli.commands.options.CommandDelete;
import view.cli.commands.options.CommandShow;
import view.cli.commands.options.CommandUpdate;
import controller.crud.Create;
import controller.observer.observers.ObserverConsoleSize;

import java.io.IOException;

public class CommandMain implements ICommand {

    public CommandMain() {
    }

    @Override
    public void run() throws InterruptedException {

        Console console = new Console();

        do {
            System.out.println(toString());
            switch (console.input("-------------")) {
                case ":c":
                    try {
                        Create create = new Create();
                        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(create);
                        new CommandAdd().run();
                    } catch (IllegalArgumentException | NullPointerException | InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case ":r":
                    try {
                        new CommandShow().run();
                    } catch (IllegalArgumentException | NullPointerException | InterruptedException | IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case ":d":
                    try {
                        new CommandDelete().run();
                    } catch (IllegalArgumentException | NullPointerException | InterruptedException e) {
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
