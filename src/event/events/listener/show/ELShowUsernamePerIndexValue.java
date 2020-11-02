package event.events.listener.show;

import crud.Read;
import event.Event;
import event.EventListener;

import java.util.HashMap;

public class ELShowUsernamePerIndexValue implements EventListener {
    @Override
    public void onInputEvent(Event event) {
        HashMap<String, Integer> map = new Read().listAllUsernamePerIndexValue(event.getText());

        System.out.println(map.keySet() + " | " + map.values());
    }
}
