package controller.crud;

import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class TestUpdate {

    @Test
    public void testUpdateCallIllegalArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Update update = new Update();
            update.accessCount("300");
            update.accessCount("300");
            update.accessCount("300");
            update.accessCount("300");
        });
    }

    @Test
    public void testUpdateAndGetClick() {
        Storage.getInstance().clear();
        Update update = new Update();

        Create create = new Create();
        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);

        create.interactiveVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Kevin"), "Mix"
        );

        MediaContent address = (MediaContent) Storage.getInstance().getMedia().get(0);
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());

        Assertions.assertEquals(4, update.getAccessCount(address.getAddress()));
    }

    @Test
    public void testUpdate100() {
        Storage.getInstance().clear();
        Update update = new Update();

        Create create = new Create();
        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);

        create.interactiveVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Kevin"), "Mix"
        );

        MediaContent address = (MediaContent) Storage.getInstance().getMedia().get(0);
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());

        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());

        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());

        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());

        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());

        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());
        update.accessCount(address.getAddress());

        Assertions.assertEquals((long)100, Storage.getInstance().getCountOfUse().get(address.getAddress()));
    }

}
