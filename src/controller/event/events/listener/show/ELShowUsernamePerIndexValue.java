package controller.event.events.listener.show;

import controller.crud.Read;
import controller.event.Event;
import controller.event.EventListener;

import java.util.HashMap;

public class ELShowUsernamePerIndexValue implements EventListener {
    @Deprecated
    @Override
    public void onInputEvent(Event event) {
        HashMap<String, Integer> map = new Read().listAllUsernamePerIndexValue(event.getText());

        System.out.println(map.keySet() + " | " + map.values());
    }
}
