package controller;

import controller.crud.*;
import modell.data.storage.Storage;
import modell.data.content.Person;

import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Video;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.*;


public class TestCrud {

    private final Collection<Tag> t = new LinkedList<>();
    private final Duration d = Duration.ofSeconds(2000);

    @Test
    public void create() throws InterruptedException {

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Create create = new Create();

        create.interactiveVideo(500, 400, "edcs", 9174, d, t, new Person("KevinTechen"), "Tdas");

        System.out.println(storage.getMedia().get(0).getUploader().getName().compareTo("KevinTechen") == 0 && storage.getMedia().get(0).getWidth() == 500);

        Assertions.assertTrue(storage.getMedia().get(0).getUploader().getName().compareTo("KevinTechen") == 0 && storage.getMedia().get(0).getWidth() == 500);
    }

    @Test
    public void read() throws InterruptedException {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Create create = new Create();
        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("TimPorsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reinerfall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("HöchenFlug"), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas");

        String name = "KevinTechen";

        Read read = new Read();

        HashMap<String, Integer> map = read.listAllUsernamePerIndexValue(name);

        Assertions.assertEquals(4, map.get(name));
    }

    @Test
    public void readALL() throws InterruptedException {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");


        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas");

        create.licensedAudioVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "LordPau", 300);

        Read read = new Read();

        List<Video> list = read.fullList();

        Assertions.assertEquals(8, list.size());
    }

    @Test
    public void deletePerAddress() throws InterruptedException {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, storage.getPerson().get(0), "Tdas");

        Delete delete = new Delete();

        delete.perAddress(storage.getMedia().get(0).getAddress());

        Assertions.assertEquals(3, storage.getMedia().size());
    }

    @Test
    public void deletePerAddressCheckPerson() throws InterruptedException {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        Create create = new Create();

        Person person = new Person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, person, "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, person, "Tdas");

        Delete delete = new Delete();

        delete.perAddress(storage.getMedia().get(3).getAddress());

        Assertions.assertEquals(2, storage.getPerson().size());
    }

    @Test
    public void deletePerAddressUniqueUser() throws InterruptedException {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        Create create = new Create();

        Person person = new Person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, person, "Tdas");

        Delete delete = new Delete();

        delete.perAddress(storage.getMedia().get(2).getAddress());

        Assertions.assertEquals(2, storage.getPerson().size());

    }

    @Test
    public void deletePerAddressNotUniqueUser() throws InterruptedException {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        Create create = new Create();

        Person person = new Person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, person, "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, person, "Tdas");

        Delete delete = new Delete();

        delete.perAddress(storage.getMedia().get(3).getAddress());

        Assertions.assertEquals(2, storage.getPerson().size());

    }

    @Test
    public void deletePerUser() throws InterruptedException {
        final Create create = new Create();
        final Delete delete = new Delete();
        final Collection<Tag> t = new LinkedList<>();
        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);
        final Duration d = Duration.ofSeconds(2000);
        Person person = new Person("Höchen Flug");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, person, "Tdas");

        create.licensedAudioVideo(300, 599, "edcs", 9174, d, t, person, "Tim", 233);
        create.licensedAudioVideo(3221, 400, "gjtzu", 9174, d, t, person, "Tim", 233);

        delete.perUser("HöchenFlug");

        Assertions.assertEquals(2, storage.getMedia().size());
    }

    @Test
    public void deletePerUserCheckPeronList() throws InterruptedException {
        final Create create = new Create();
        final Delete delete = new Delete();
        final Collection<Tag> t = new LinkedList<>();
        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);
        final Duration d = Duration.ofSeconds(2000);
        Person person = new Person("Höchen Flug");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, person, "Tdas");

        create.licensedAudioVideo(300, 599, "edcs", 9174, d, t, person, "Tim", 233);
        create.licensedAudioVideo(3221, 400, "gjtzu", 9174, d, t, person, "Tim", 233);

        delete.perUser("HöchenFlug");

        Assertions.assertEquals(2, storage.getPerson().size());
    }

    @Test
    public void update() throws InterruptedException {
        Update update = new Update();

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, storage.getPerson().get(0), "Tdas");

        update.accessCount(storage.getMedia().get(3).getAddress());

        Assertions.assertEquals(1, storage.getAccessCounter(storage.getMedia().get(3).getAddress()));
    }

    @Test
    public void updatePerGetAdress() throws InterruptedException {
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        Create create = new Create();
        Update update = new Update();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, storage.getPerson().get(0), "Tdas");

        update.accessCount(storage.getMedia().get(3).getAddress());
        update.accessCount(storage.getMedia().get(3).getAddress());
        update.accessCount(storage.getMedia().get(3).getAddress());
        update.accessCount(storage.getMedia().get(3).getAddress());

        Assertions.assertEquals(4, storage.getCountOfUse().get(storage.getMedia().get(3).getAddress()));
    }

    @Test
    public void updateRun() throws InterruptedException {
        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        Create create = new Create();
        Update update = new Update();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("TimPorsche"), "Tdas");
        update.accessCount(storage.getMedia().get(0).getAddress());

        Assertions.assertEquals(1, storage.getAccessCounter(storage.getMedia().get(0).getAddress()));
    }

    @Test
    public void updatePerGetAdressMore() throws InterruptedException {
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, storage.getPerson().get(0), "Tdas");

        Update update = new Update();
        update.accessCount(storage.getMedia().get(3).getAddress());
        update.accessCount(storage.getMedia().get(3).getAddress());
        update.accessCount(storage.getMedia().get(3).getAddress());

        update.accessCount(storage.getMedia().get(0).getAddress());
        update.accessCount(storage.getMedia().get(0).getAddress());
        update.accessCount(storage.getMedia().get(0).getAddress());
        update.accessCount(storage.getMedia().get(0).getAddress());

        boolean test1 = (3 == storage.getCountOfUse().get(storage.getMedia().get(0).getAddress()));
        boolean test2 = (4 == storage.getCountOfUse().get(storage.getMedia().get(3).getAddress()));

        Assertions.assertEquals(test2, test1);
    }

    @Test
    public void readTyp() throws InterruptedException {

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        Create create = new Create();


        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, storage.getPerson().get(0), "Tdas");
        try {
            Read read = new Read();

            List<? extends Uploadable> videos = read.getFullListOrFilterbyTyp("InteractionAudioVideo");
        } catch (IllegalArgumentException e) {
            Assertions.fail();
        }

    }

    @Test
    public void readTypIsNull() throws InterruptedException {

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, storage.getPerson().get(0), "Tdas");
        create.licensedAudioVideo(400, 400, "edcs", 9174, d, t, storage.getPerson().get(0), "Stefan Pilz", 200);

        Read read = new Read();

        List<? extends Uploadable> l = read.getFullListOrFilterbyTyp(null);
        List<? extends Uploadable> r = read.getFullListOrFilterbyTyp("interactiveVideo");

        Assertions.assertNotEquals(l.size(), r.size());
    }

    @Test
    public void readTags() throws InterruptedException {
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, storage.getPerson().get(0), "Tdas");

        /*
        System.out.println(Tag.Lifestyle.name());
        System.out.println(Storage.usedTags.get("Lifestyle"));

        System.out.println(Tag.Animal.name());
        System.out.println(Storage.usedTags.get("Animal"));

        System.out.println(Tag.News.name());
        System.out.println(Storage.usedTags.get("News"));

        System.out.println(Tag.Tutorial.name());
        System.out.println(Storage.usedTags.get("Tutorial"));
        */

        boolean test = storage.getUsedTags().get(Tag.Lifestyle.name());
        boolean test1 = storage.getUsedTags().get(Tag.Animal.name());
        boolean test2 = storage.getUsedTags().get(Tag.News.name());
        boolean test3 = storage.getUsedTags().get(Tag.Tutorial.name());

        Assertions.assertTrue((test && test1) != (test2 && test3));
    }

}
