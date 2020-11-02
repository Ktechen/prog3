package event.events.listener.delete;

import crud.Delete;
import event.Event;
import event.EventListener;

public class ELDeleteVideoPerUser implements EventListener {
    @Override
    public void onInputEvent(Event event) {
        new Delete().perUser(event.getText());
    }
}
