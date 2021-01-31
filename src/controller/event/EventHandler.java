package controller.event;

import java.util.LinkedList;
import java.util.List;

public class EventHandler<T extends EventListener> {

    private final List<T> listenerList = new LinkedList<>();

    public void add(T listener) {
        this.listenerList.add(listener);
    }

    public void remove(T listener) {
        this.listenerList.remove(listener);
    }

    public void handle(Event event) {
        for (T listener : listenerList) listener.onInputEvent(event);
    }

}
