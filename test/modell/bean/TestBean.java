package modell.bean;

import controller.crud.Create;
import modell.data.content.Person;
import controller.storage.Storage;
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

        create.video(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Amadeus")
        );

        create.licensedVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Rudi das Rüsselschwein"), "Peter Uwe"
        );

        create.licensedAudioVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Kevin"), "Mix", 2323
        );

        create.audio(
                300, Duration.parse("PT20m"), tagCollection, 3232, "mix", new Person("Bieber")
        );

        create.licensedAudio(
                300, Duration.parse("PT20m"), tagCollection, 3232, "mix", new Person("Bieber"), "Reiner"
        );

        create.audioVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Amadeus"), 31231
        );

    }

    @Test
    public void getConvertedMediaList() {
        BeanStorage beanStorage = new BeanStorage();
        beanStorage.addToMediaList();
        beanStorage.addToUploaderList();
        Assertions.assertEquals(7, beanStorage.getMedia().size());
        Assertions.assertEquals(4, beanStorage.getUploaders().size());
    }


}
