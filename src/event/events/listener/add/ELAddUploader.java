package event.events.listener.add;

import crud.Create;
import event.Event;
import event.EventListener;

public class ELAddUploader implements EventListener {
    @Override
    public void onInputEvent(Event event) {
        new Create().person(event.getText());
    }
}
