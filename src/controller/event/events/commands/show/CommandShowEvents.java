package controller.event.events.commands.show;

import controller.crud.Read;
import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.show.EventShowAll;
import controller.event.events.event.show.EventShowUsedTags;
import controller.event.events.event.show.EventShowUsernamePerIndex;
import controller.handleInput.InputConverter;
import modell.mediaDB.MediaContent;

import java.util.HashMap;
import java.util.List;

public class CommandShowEvents extends CommandEvent {

    public CommandShowEvents(InputConverter converter, EventHandler<EventListener> eventHandler) {
        super(converter, eventHandler);
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
        EventShowUsernamePerIndex eventShowUsernamePerIndex = new EventShowUsernamePerIndex(this, name.replaceAll("\\s", ""));
        StringBuffer stringBuffer = new StringBuffer();

        EventListener eventListener = event -> {
            final Read read = new Read();
            String nameChange = name.replaceAll("\\s", "");
            HashMap<String, Integer> map = read.listAllUsernamePerIndexValue(nameChange);
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
