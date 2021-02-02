package controller.event;

import controller.crud.Create;
import controller.event.events.commands.delete.CommandDeleteEventAddress;
import controller.event.events.listener.delete.ELDeleteVideoPerAdress;
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

public class TestEventDelete {

    @Test
    public void testDeletePerAddress(){
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

        EventHandler<ELDeleteVideoPerAdress> address = new EventHandler<>();
        ELDeleteVideoPerAdress elDeleteVideoPerAdress = new ELDeleteVideoPerAdress();
        address.add(elDeleteVideoPerAdress);
        final CommandDeleteEventAddress commandDeleteEventAddress = new CommandDeleteEventAddress(new InputConverter(), address);
        commandDeleteEventAddress.eventDeletePerAddress(content.getAddress());

        Assertions.assertEquals(0, Storage.getInstance().getMedia().size());
    }

}
