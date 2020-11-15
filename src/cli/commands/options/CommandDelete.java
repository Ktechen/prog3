package cli.commands.options;

import cli.Console;
import cli.commands.ICommand;
import event.EventHandler;
import event.EventListener;
import event.events.event.delete.EventDeletePerString;
import event.events.listener.delete.ELDeleteVideoPerAdress;
import event.events.listener.delete.ELDeleteVideoPerUser;

import java.io.IOException;

public class CommandDelete implements ICommand {

    private EventHandler<EventListener> eventHandler;
    private final Console console;

    public void setHandler(EventHandler handler) {
        this.eventHandler = handler;
    }

    public CommandDelete() {
        this.console = new Console();
        this.eventHandler = new EventHandler();
    }

    @Override
    public void run() throws InterruptedException {
        System.out.println(toString());

        String value = console.readInput("---------------");

        EventDeletePerString eventDeletePerString = new EventDeletePerString(this, value);
        ELDeleteVideoPerAdress elDeleteVideoPerAdress = new ELDeleteVideoPerAdress();
        ELDeleteVideoPerUser elDeleteVideoPerUser = new ELDeleteVideoPerUser();

        if (value.indexOf("FILE:///") == 0) {
            eventHandler.add(elDeleteVideoPerAdress);
        } else {
            eventHandler.add(elDeleteVideoPerUser);
        }

        setHandler(eventHandler);

        if (null != this.eventHandler) {
            eventHandler.handle(eventDeletePerString);
        }
    }

    @Override
    public String toString() {
        return "[Produzentenname]löscht den Produzenten \n" +
                "[Abrufadresse]löscht die Mediadatei\n" +
                "back - back to main";
    }

}
