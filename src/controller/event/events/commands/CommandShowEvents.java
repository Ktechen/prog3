package controller.event.events.commands;

import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.event.show.EventShowAll;
import controller.event.events.listener.show.ELShowAll;

public class CommandShowEvents {

    private EventHandler<EventListener> eventHandler;

    public CommandShowEvents(EventHandler<EventListener> eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void setHandler(EventHandler<controller.event.EventListener> handler) {
        this.eventHandler = handler;
    }

    public void eventShowAll(String filterby) throws InterruptedException {
        EventShowAll eventShowAll = new EventShowAll(this, filterby);
        ELShowAll elShowAll = new ELShowAll();

        eventHandler.add(elShowAll);
        setHandler(eventHandler);

        if (null != this.eventHandler) {
            eventHandler.handle(eventShowAll);
        }
    }
}
