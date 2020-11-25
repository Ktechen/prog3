package modell;

import controller.crud.Create;
import modell.data.content.interaction.InteractiveVideo;
import modell.data.storage.Storage;
import modell.data.content.Person;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class TestAudioVideo {

    private final Collection<Tag> t = new LinkedList<Tag>();
    private final Duration d = Duration.ofSeconds(2000);
    private InteractiveVideo video123;

    @BeforeEach
    public void setup() {
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);
    }

    @Test
    public void size() throws InterruptedException {
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Create create = new Create();
        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        create.person("Kevin Techen");

        create.interactiveVideo(500, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            create.interactiveVideo(2000, 2001, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas");
        });

        storage.clear();
    }

    @Test
    public void createMedia() {

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        Person person = new Person("Kevin Techen");

        InteractiveVideo Video0 = new InteractiveVideo(400, 300, "encoding", 8167, d, t, person, "test");
        InteractiveVideo Video1 = new InteractiveVideo(500, 400, "encoding", 8167, d, t, person, "test");
        InteractiveVideo Video2 = new InteractiveVideo(600, 500, "encoding", 8167, d, t, person, "test");

        boolean check0 = Video0.getUploader().getName().compareTo(Video1.getUploader().getName()) == 0;
        boolean check1 = Video1.getUploader().getName().compareTo(Video2.getUploader().getName()) == 0;

        Assertions.assertEquals(check0, check1);
        storage.clear();
    }

    @Test
    public void createUserMoreUser() {

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        try {
            Person person0 = new Person("Kevin Techen");
            Person person1 = new Person("Paul Reiner");
            Person person2 = new Person("Tim Reiner");
            Person person3 = new Person("Carsten Off");
        } catch (IllegalArgumentException e) {
            Assertions.fail();
        }
    }

    @Test
    public void createUser() {

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        try {
            Person person = new Person("Kevin Techen");
            Person person1 = new Person("Kevin Paul");
        } catch (IllegalArgumentException e) {
            Assertions.fail();
        }
    }

}
