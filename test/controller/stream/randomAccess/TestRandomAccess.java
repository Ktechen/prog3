package controller.stream.randomAccess;

import controller.crud.Create;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Ich RandomAccess noch nicht richtig Implementiert und bin damit noch nicht fertig
 */
public class TestRandomAccess {
    @BeforeEach
    public void setup() {
        Storage storage = Storage.getInstance();
        storage.clear();

        final Collection<Tag> t = new LinkedList<>();

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        final Duration d = Duration.ofSeconds(2000);

        Create create = new Create();
        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
    }

    @Test
    public void testSaveRandomAccessCheckPositionOfSavePoint() throws IllegalAccessException {
        Assertions.fail();

        RandomAccess randomAccess = new RandomAccess();
        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(0);
        MediaContent content2 = (MediaContent) Storage.getInstance().getMedia().get(1);

        randomAccess.save(content.getAddress());
        randomAccess.save(content2.getAddress());

        AtomicLong actual1 = new AtomicLong();
        AtomicLong actual2 = new AtomicLong();


        Assertions.assertEquals(0, actual1.get());
        Assertions.assertEquals(1, actual2.get());

        File file = new File(RandomAccess.FILE);
        file.delete();
    }

    @Test
    public void testSaveAndLoadRandomData() throws IllegalAccessException {
        Assertions.fail();

        RandomAccess randomAccess = new RandomAccess();
        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(0);
        MediaContent content2 = (MediaContent) Storage.getInstance().getMedia().get(1);

        randomAccess.save(content.getAddress());
        randomAccess.save(content2.getAddress());

        //Simulation of empty storage
        Storage.getInstance().clear();

        randomAccess.load(content.getAddress());

        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
        File file = new File(RandomAccess.FILE);
        file.delete();
    }

    @Test
    public void testSaveThrowIllegalAccessException() {
        Assertions.fail();

        RandomAccess randomAccess = new RandomAccess();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            randomAccess.save("dasdsaasdjkasjdask ndasd");
        });
    }

    @Test
    public void testSaveThrowNullpointer() {
        Assertions.fail();

        RandomAccess randomAccess = new RandomAccess();
        Assertions.assertThrows(NullPointerException.class, () -> {
            randomAccess.save(null);
        });
    }

    @Test
    public void testLoadThrowIllegalAccessException() {
        Assertions.fail();

        RandomAccess randomAccess = new RandomAccess();
        Assertions.assertThrows(IllegalAccessException.class, () -> {
            randomAccess.load(Storage.TYPE_OF_SOURCE + "dadhsjdasködasdasdas");
        });
    }

    @Test
    public void testLoadThrowNullPointer() {
        Assertions.fail();

        RandomAccess randomAccess = new RandomAccess();
        Assertions.assertThrows(NullPointerException.class, () -> {
            randomAccess.load(null);
        });
    }

    @Test
    public void testLoadThrowIllegalArgument() {
        Assertions.fail();

        RandomAccess randomAccess = new RandomAccess();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            randomAccess.load("aasdasdsadasd");
        });
    }

    @Test
    public void testSaveAndLoadRandomDataWithMoreMedias() throws IllegalAccessException {
        Assertions.fail();

        RandomAccess randomAccess = new RandomAccess();
        Storage.getInstance().clear();
        final Create create = new Create();
        final Collection<Tag> tags = new ArrayList<>();

        tags.add(Tag.Lifestyle);
        tags.add(Tag.Animal);

        create.video(
                200, 300,
                "mix", 9732,
                Duration.parse("PT20m"), tags, new Person("Jocko Ono")
        );

        create.audio(
                3822, Duration.parse("PT20m"),
                tags, 323, "mix",
                new Person("Dean Wincester")
        );

        create.audioVideo(
                300, 200, "mix",
                8372, Duration.parse("PT10m"), tags,
                new Person("Otto Clockwork"), 232
        );

        for (int i = 0; i < Storage.getInstance().getMedia().size(); i++) {
            MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(i);
            randomAccess.save(content.getAddress());
        }

        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(Storage.getInstance().getMedia().size() - 1);
        randomAccess.load(content.getAddress());

        Assertions.assertEquals(6, Storage.getInstance().getMedia().size());
        File file = new File(RandomAccess.FILE);
        file.delete();
    }
}
