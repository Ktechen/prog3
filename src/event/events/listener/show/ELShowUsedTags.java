package event.events.listener.show;

import crud.Read;
import event.Event;
import event.EventListener;

import java.util.HashMap;

public class ELShowUsedTags implements EventListener {
    @Override
    public void onInputEvent(Event event) {
        HashMap<String, Boolean> map = new Read().getFindedTags();

        System.out.println(map.keySet() + " | " + map.values());
    }
}
