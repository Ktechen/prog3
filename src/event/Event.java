package event;

import java.util.EventObject;

public abstract class Event extends EventObject {

    private String text;
    private Object[] arr;
    private String type;

    public Event(Object source) {
        super(source);
    }

    public Event(Object source, String text) {
        super(source);
        this.text = text;
    }

    public Event(Object source, Object[] arr, String type) {
        super(source);
        this.arr = arr;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return this.text;
    }

    public Object[] getArr() {
        return arr;
    }


}
