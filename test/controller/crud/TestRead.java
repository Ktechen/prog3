package controller.crud;

import modell.data.content.Person;
import controller.storage.Storage;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class TestRead {

    @Test
    public void testFilterByShowTags() {
        Storage.getInstance().clear();

        final Create create = new Create();
        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);

        Collection<Tag> tagCollectionA = new ArrayList<>();
        tagCollectionA.add(Tag.News);
        tagCollectionA.add(Tag.Animal);

        create.interactiveVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Kevin"), "Mix"
        );

        create.interactiveVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollectionA,
                new Person("Kevin"), "Mix"
        );

        final Read read = new Read();

        LinkedList<String> list = read.getTagsByFilter(true);

        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(Tag.News.toString()+", ", list.get(1));
        Assertions.assertEquals(Tag.Animal.toString()+", ", list.get(0));

    }

}
