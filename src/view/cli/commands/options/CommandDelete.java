package view.cli.commands.options;

import modell.data.storage.Storage;
import view.cli.Console;
import view.cli.commands.ICommand;
import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.event.delete.EventDeletePerString;
import controller.event.events.listener.delete.ELDeleteVideoPerAdress;
import controller.event.events.listener.delete.ELDeleteVideoPerUser;

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

        if (value.indexOf(Storage.TYPE_OF_SOURCE) == 0) {
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
