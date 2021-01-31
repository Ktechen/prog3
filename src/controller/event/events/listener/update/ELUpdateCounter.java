package controller.event.events.listener.update;

import controller.crud.Update;
import controller.event.Event;
import controller.event.EventListener;

public class ELUpdateCounter implements EventListener {
    @Override
    public void onInputEvent(Event event) {
        final Update update = new Update();
        update.accessCount(event.getText());
    }
}
