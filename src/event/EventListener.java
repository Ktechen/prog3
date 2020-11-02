package event;


public interface EventListener extends java.util.EventListener {
    void onInputEvent(Event event);
}
