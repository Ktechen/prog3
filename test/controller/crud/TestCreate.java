package controller.crud;

import controller.observer.observers.ObserverConsoleSize;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class TestCreate {

    @Test
    public void defaultGetCapacity() {
        Storage.getInstance().clear();
        final Create create = new Create();
        Assertions.assertEquals(BigDecimal.valueOf(0), create.getCapacity());
    }

    @Test
    public void createVideo() {
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

        Assertions.assertEquals(BigDecimal.valueOf(0), create.getCapacity());
    }


    @Test
    public void createAllTypesOfMedia() {
        Storage.getInstance().clear();
        final Create create = new Create();
        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);
        final Uploader uploader = new Person("Kevin");

        try {
            create.interactiveVideo(300, 400, "mix", 7323, Duration.parse("PT20m"), tagCollection, uploader, "type");
            create.licensedAudioVideo(300, 500, "mix", 7323, Duration.parse("PT20m"), tagCollection, uploader, "Tim Pole", 2323);
            create.audio(3232, Duration.parse("PT10m"), tagCollection, 232, "mix", uploader);
            create.video(300, 400, "mix", 7323, Duration.parse("PT20m"), tagCollection, uploader);
            create.audioVideo(300, 400, "mix", 7323, Duration.parse("PT20m"), tagCollection, uploader, 23232);
            create.licensedAudio(3232, Duration.parse("PT10m"), tagCollection, 232, "mix", uploader, "Otto Walkes");
            create.licensedVideo(300, 400, "mix", 7323, Duration.parse("PT20m"), tagCollection, uploader, "Reiner Meiner");
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(7, Storage.getInstance().getMedia().size());
    }

    @Test
    public void createWrongInputNumberFormatExceptionWidth() {
        Storage.getInstance().clear();
        final Create create = new Create();
        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);
        final Uploader uploader = new Person("Kevin");

        Assertions.assertThrows(NumberFormatException.class, ()->{
            create.interactiveVideo(-1, 400, "mix", 7323, Duration.parse("PT20m"), tagCollection, uploader, "type");
        });
    }

    @Test
    public void createWrongInputNullPointerExceptionEncodingAndType(){
        Storage.getInstance().clear();
        final Create create = new Create();
        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);
        final Uploader uploader = new Person("Kevin");

        Assertions.assertThrows(NullPointerException.class, ()->{
            create.interactiveVideo(400, 400, null, 4560, Duration.parse("PT20m"), tagCollection, uploader, null);
        });
    }

    @Test
    public void createWrongInputNullPointerExceptionType(){
        Storage.getInstance().clear();
        final Create create = new Create();
        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);
        final Uploader uploader = new Person("Kevin");


        Assertions.assertThrows(NullPointerException.class, ()->{
            create.interactiveVideo(400, 400, "null", 561320, Duration.parse("PT20m"), tagCollection, uploader, null);
        });
    }

    @Test
    public void createWrongInputNullPointerExceptionEncoding(){
        Storage.getInstance().clear();
        final Create create = new Create();
        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);
        final Uploader uploader = new Person("Kevin");

        Assertions.assertThrows(NullPointerException.class, ()->{
            create.interactiveVideo(400, 400, null, 520, Duration.parse("PT20m"), tagCollection, uploader, "type");
        });
    }

    @Test
    public void createWrongInputNumberFormatExceptionHeight(){
        Storage.getInstance().clear();
        final Create create = new Create();
        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);
        final Uploader uploader = new Person("Kevin");

        Assertions.assertThrows(NumberFormatException.class, ()->{
            create.interactiveVideo(400, -1, "mix", 7323, Duration.parse("PT20m"), tagCollection, uploader, "type");
        });
    }

    @Test
    public void createWrongInputNumberFormatExceptionWidthAndHeight(){
        Storage.getInstance().clear();
        final Create create = new Create();
        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);
        final Uploader uploader = new Person("Kevin");

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            create.interactiveVideo(-231, -3232, "mix", -324, Duration.parse("PT20m"), tagCollection, uploader, "type");
        });
    }

    @Test
    public void AddObserver() {
        Storage.getInstance().clear();
        final Create create = new Create();
        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(create);

        Assertions.assertEquals(1, Create.getObserverList().size());
        create.leave(observerConsoleSize);
    }

    @Test
    public void RemoveObserver() {
        Storage.getInstance().clear();
        final Create create = new Create();
        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(create);

        Assertions.assertEquals(1, Create.getObserverList().size());

        create.leave(observerConsoleSize);

        Assertions.assertEquals(0, Create.getObserverList().size());
    }

}
