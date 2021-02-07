package modell.bean;

import controller.crud.Create;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class TestBean {

    /**
     * Default Setup with 5 Created Medias
     */
    @BeforeEach
    public void setup() {
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

        create.interactiveVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Amadeus"), "Mix"
        );
        create.interactiveVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Rudi das Rüsselschwein"), "Mix"
        );
        create.interactiveVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Uwe"), "Mix"
        );
        create.interactiveVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Peter"), "Mix"
        );
    }

    @Test
    public void getConvertedMediaList() {
        BeanStorage beanStorage = new BeanStorage();
        beanStorage.addToMediaList();
        Assertions.assertEquals(5, beanStorage.getMedia().size());


    }

}
