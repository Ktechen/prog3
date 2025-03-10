package controller.event.events.commands.show;

import controller.crud.Read;
import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.show.EventShowAll;
import controller.event.events.event.show.EventShowUsedTags;
import controller.event.events.event.show.EventShowUsernamePerIndex;
import controller.handleInput.InputConverter;

import java.util.HashMap;
import java.util.List;

@Deprecated
public class CommandShowEvents extends CommandEvent {

    public CommandShowEvents(InputConverter converter, EventHandler<EventListener> eventHandler) {
        super(converter, eventHandler);
    }

    public StringBuffer eventShowAll(String filterby) {
        EventShowAll eventShowAll = new EventShowAll(this, filterby);

        StringBuffer stringBuffer = new StringBuffer();

        EventListener eventListener = event -> {
            final Read read = new Read();

            List<String> videos = read.getFullListOrFilterbyTyp(event.getText());

            for (String video : videos) {
                stringBuffer.append(video).append("\n");
            }
        };

        eventHandler.add(eventListener);

        if (null != this.eventHandler) {
            eventHandler.handle(eventShowAll);
        }

        return stringBuffer;
    }

    public StringBuffer eventShowUsedTags(String value) {
        EventShowUsedTags eventShowUsedTags = new EventShowUsedTags(this, value);
        StringBuffer stringBuffer = new StringBuffer();

        EventListener eventListener = event -> {
            final Read read = new Read();

            HashMap<String, Boolean> map = read.getFindedTags();
            stringBuffer.append(map.keySet()).append(" | ").append(map.values());
        };

        eventHandler.add(eventListener);
        
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
        
        if (null != this.eventHandler) {
            eventHandler.handle(eventShowUsernamePerIndex);
        }

        return stringBuffer;
    }
}
