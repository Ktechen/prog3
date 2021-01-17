package controller.cli.commands.options;

import controller.cli.Console;
import controller.cli.commands.CommandMain;
import controller.cli.ICommand;
import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.event.show.EventShowAll;
import controller.event.events.event.show.EventShowUsedTags;
import controller.event.events.event.show.EventShowUsernamePerIndex;
import controller.event.events.listener.show.ELShowAll;
import controller.event.events.listener.show.ELShowUsedTags;
import controller.event.events.listener.show.ELShowUsernamePerIndexValue;

import java.io.IOException;

@Deprecated
public class CommandShow implements ICommand {

    private EventHandler<EventListener> eventHandler;
    private final Console console;

    public void setHandler(EventHandler handler) {
        this.eventHandler = handler;
    }

    public CommandShow() {

        eventHandler = new EventHandler();
        console = new Console();

    }

    @Override
    public void run() throws IOException, InterruptedException {
        System.out.println(toString());
        String input = console.input("-------------");

        switch (input) {
            case "1":

                String filterby = console.input("Filter: ");

                if (filterby.isEmpty()) {
                    filterby = null;
                }

                EventShowAll eventShowAll = new EventShowAll(this, filterby);
                ELShowAll elShowAll = new ELShowAll();

                eventHandler.add(elShowAll);
                setHandler(eventHandler);

                if (null != this.eventHandler) {
                    eventHandler.handle(eventShowAll);
                }

                break;
            case "2":

                String name = console.input("Name: ");

                EventShowUsernamePerIndex eventShowUsernamePerIndex = new EventShowUsernamePerIndex(this, name);
                ELShowUsernamePerIndexValue elShowUsernamePerIndexValue = new ELShowUsernamePerIndexValue();

                eventHandler.add(elShowUsernamePerIndexValue);
                setHandler(eventHandler);

                if (null != this.eventHandler) {
                    eventHandler.handle(eventShowUsernamePerIndex);
                }

                break;
            case "3":

                EventShowUsedTags eventShowUsedTags = new EventShowUsedTags(this);
                ELShowUsedTags elShowUsedTags = new ELShowUsedTags();

                eventHandler.add(elShowUsedTags);
                setHandler(eventHandler);

                if (null != this.eventHandler) {
                    eventHandler.handle(eventShowUsedTags);
                }

                break;
            case "back":
                new CommandMain().run();
                break;
        }
    }

    @Override
    public String toString() {
        return "1. Showall or filter\n" +
                "2. User per Index \n" +
                "3. Show used tags\n" +
                "back - back to main";
    }
}
