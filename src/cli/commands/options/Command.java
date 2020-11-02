package cli.commands.options;

import cli.Console;
import event.EventHandler;
import event.EventListener;

public abstract class Command {

    private EventHandler<EventListener> eventHandler;
    private Console console;

    public Command(EventHandler<EventListener> eventHandler, Console console) {
        this.eventHandler = eventHandler;
        this.console = console;
    }
}
