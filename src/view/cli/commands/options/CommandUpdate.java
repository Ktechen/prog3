package view.cli.commands.options;

import view.cli.Console;
import view.cli.commands.ICommand;
import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.event.update.EventUpdateCounter;
import controller.event.events.listener.update.ELUpdateCounter;


public class CommandUpdate implements ICommand {

    private Console console;
    private final EventHandler<EventListener> eventHandler;

    public CommandUpdate() {
        console = new Console();
        eventHandler = new EventHandler<>();
    }

    //TODO connect to update func
    @Override
    public void run() throws InterruptedException {
        System.out.println(toString());
        String address = console.input("------------");
        EventUpdateCounter eventUpdateCounter = new EventUpdateCounter(this, address);
        ELUpdateCounter elUpdateCounter = new ELUpdateCounter();
        eventHandler.add(elUpdateCounter);
        eventHandler.handle(eventUpdateCounter);
    }

    @Override
    public String toString() {
        return "[Abrufadresse] erhöht den Zähler für die Abrufe um eins";
    }
}
