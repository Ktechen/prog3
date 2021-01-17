package controller.event.events.listener.show;

import controller.crud.Read;
import controller.event.Event;
import controller.event.EventListener;

import java.util.HashMap;

public class ELShowUsedTags implements EventListener {
    @Deprecated
    @Override
    public void onInputEvent(Event event) {
        HashMap<String, Boolean> map = new Read().getFindedTags();

        System.out.println(map.keySet() + " | " + map.values());
    }
}
