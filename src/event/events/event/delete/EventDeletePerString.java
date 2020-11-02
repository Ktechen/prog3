package event.events.event.delete;

import event.Event;

public class EventDeletePerString extends Event {
    public EventDeletePerString(Object source, String text) {
        super(source, text);
    }
}
