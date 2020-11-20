package controller.event.events.listener.add;

import controller.crud.Create;
import controller.event.Event;
import controller.event.EventListener;

public class ELAddUploader implements EventListener {
    @Override
    public void onInputEvent(Event event) {
        new Create().person(event.getText());
    }
}
