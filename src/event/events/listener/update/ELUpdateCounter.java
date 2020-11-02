package event.events.listener.update;

import crud.Update;
import event.Event;
import event.EventListener;

public class ELUpdateCounter implements EventListener {
    @Override
    public void onInputEvent(Event event) {
        new Update().run(event.getText());
    }
}
