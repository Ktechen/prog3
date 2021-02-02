package controller.event;

import controller.crud.Create;
import controller.crud.Update;
import controller.event.events.commands.update.CommandUpdateEvent;
import controller.event.events.listener.update.ELUpdateCounter;
import controller.handleInput.InputConverter;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class TestEventUpdate {

    @Test
    public void TestUpdateEvent() {
        Storage.getInstance().clear();
        final Create create = new Create();

        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);

        create.interactiveVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Kevin"), "Mix"
        );

        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(0);
        EventHandler<ELUpdateCounter> handler = new EventHandler<>();
        ELUpdateCounter elUpdateCounter = new ELUpdateCounter();
        handler.add(elUpdateCounter);
        final CommandUpdateEvent commandUpdateEvent = new CommandUpdateEvent(new InputConverter(), handler);

        commandUpdateEvent.eventUpdateCounter(content.getAddress()); // 1
        commandUpdateEvent.eventUpdateCounter(content.getAddress()); // 3 +2
        commandUpdateEvent.eventUpdateCounter(content.getAddress()); // 6 +3
        commandUpdateEvent.eventUpdateCounter(content.getAddress()); // 10 +4
        commandUpdateEvent.eventUpdateCounter(content.getAddress()); // 15 +5
        commandUpdateEvent.eventUpdateCounter(content.getAddress()); // 21 +6

        final Update update = new Update();

        //Bug fixed

        Assertions.assertEquals(6,  update.getAccessCount(content.getAddress()));
    }

}
