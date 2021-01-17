package controller.event.events.commands;

import controller.crud.Read;
import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.event.show.EventShowAll;
import controller.event.events.event.show.EventShowUsedTags;
import controller.event.events.event.show.EventShowUsernamePerIndex;
import modell.mediaDB.MediaContent;

import java.util.HashMap;
import java.util.List;

public class CommandShowEvents {

    private EventHandler<EventListener> eventHandler;

    public CommandShowEvents(EventHandler<EventListener> eventHandler) {
        this.eventHandler = eventHandler;
    }

    private void setHandler(EventHandler<controller.event.EventListener> handler) {
        this.eventHandler = handler;
    }

    public StringBuffer eventShowAll(String filterby) {
        EventShowAll eventShowAll = new EventShowAll(this, filterby);

        StringBuffer stringBuffer = new StringBuffer();

        EventListener eventListener = event -> {
            final Read read = new Read();

            List<MediaContent> videos = read.getFullListOrFilterbyTyp(event.getText());

            for (MediaContent video : videos) {
                stringBuffer.append(video.toString() + "\n");
            }
        };

        eventHandler.add(eventListener);
        setHandler(eventHandler);

        if (null != this.eventHandler) {
            eventHandler.handle(eventShowAll);
        }

        return stringBuffer;
    }

    public StringBuffer eventShowUsedTags() {
        EventShowUsedTags eventShowUsedTags = new EventShowUsedTags(this);
        StringBuffer stringBuffer = new StringBuffer();

        EventListener eventListener = event -> {
            final Read read = new Read();

            HashMap<String, Boolean> map = read.getFindedTags();
            stringBuffer.append(map.keySet()).append(" | ").append(map.values());
        };

        eventHandler.add(eventListener);
        setHandler(eventHandler);

        if (null != this.eventHandler) {
            eventHandler.handle(eventShowUsedTags);
        }

        return stringBuffer;
    }

    public StringBuffer eventUsernamePerIndexValue(String name) {
        EventShowUsernamePerIndex eventShowUsernamePerIndex = new EventShowUsernamePerIndex(this, name);
        StringBuffer stringBuffer = new StringBuffer();

        EventListener eventListener = event -> {
            final Read read = new Read();
            HashMap<String, Integer> map = read.listAllUsernamePerIndexValue(name);
            stringBuffer.append(map.keySet()).append(" | ").append(map.values());
        };

        eventHandler.add(eventListener);
        setHandler(eventHandler);

        if (null != this.eventHandler) {
            eventHandler.handle(eventShowUsernamePerIndex);
        }

        return stringBuffer;
    }
}
