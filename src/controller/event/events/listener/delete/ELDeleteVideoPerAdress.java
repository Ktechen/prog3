package controller.event.events.listener.delete;

import controller.crud.Delete;
import controller.event.Event;
import controller.event.EventListener;

public class ELDeleteVideoPerAdress implements EventListener {
    @Override
    public void onInputEvent(Event event) throws InterruptedException {
        new Delete().perAddress(event.getText());
    }
}
