package controller.event.events.event.delete;

import controller.event.Event;

@Deprecated
public class EventDeletePerString extends Event {
    public EventDeletePerString(Object source, String text) {
        super(source, text);
    }
}
