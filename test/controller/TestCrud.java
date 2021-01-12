package controller;

import controller.crud.*;
import modell.data.storage.Storage;
import modell.data.content.Person;

import modell.mediaDB.*;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.*;


public class TestCrud {

    private final Collection<Tag> t = new LinkedList<>();
    private final Duration d = Duration.ofSeconds(2000);

    @Test
    public void create() throws InterruptedException {

        Storage storage = Storage.getInstance();
        storage.clear();

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Create create = new Create();

        create.interactiveVideo(500, 400, "edcs", 9174, d, t, new Person("KevinTechen"), "Tdas");

        List<Uploadable> list = storage.getMedia();

        Assertions.assertEquals(list.get(0).getUploader().getName().compareTo("KevinTechen"), 0);
    }

    @Test
    public void read() throws InterruptedException {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Create create = new Create();
        Storage storage = Storage.getInstance();
        storage.clear();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("TimPorsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reinerfall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("HöchenFlug"), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");

        String name = "KevinTechen";

        Read read = new Read();

        HashMap<String, Integer> map = read.listAllUsernamePerIndexValue(name);

        Assertions.assertEquals(4, map.get(name));
    }

    @Test
    public void readALL() {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = Storage.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");

        create.licensedAudioVideo(500, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "LordPau", 300);

        Read read = new Read();

        List<MediaContent> list = read.fullList();

        Assertions.assertEquals(8, list.size());
    }

    @Test
    public void deletePerAddress() throws InterruptedException {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = Storage.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");

        Delete delete = new Delete();

        List<MediaContent> contents = storage.getMedia();

        delete.perAddress(contents.get(0).getAddress());

        Assertions.assertEquals(3, storage.getMedia().size());
    }

    @Test
    public void deletePerAddressUniqueUser() throws InterruptedException {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = Storage.getInstance();
        storage.clear();

        Create create = new Create();

        Person person = new Person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, person, "Tdas");

        Delete delete = new Delete();

        List<MediaContent> contents = storage.getMedia();

        delete.perAddress(contents.get(2).getAddress());

        Assertions.assertEquals(3, storage.getPerson().size());

    }

    @Test
    public void deletePerAddressNotUniqueUser() throws InterruptedException {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = Storage.getInstance();
        storage.clear();

        Create create = new Create();

        Person person = new Person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, person, "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, person, "Tdas");

        Delete delete = new Delete();

        List<MediaContent> contents = storage.getMedia();

        delete.perAddress(contents.get(3).getAddress());

        Assertions.assertEquals(3, storage.getPerson().size());
    }

    @Test
    public void deletePerUser() throws InterruptedException {
        final Create create = new Create();
        final Delete delete = new Delete();
        final Collection<Tag> t = new LinkedList<>();
        Storage storage = Storage.getInstance();
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
    public void deletePerUserCheckPersonList() throws InterruptedException {
        final Create create = new Create();
        final Delete delete = new Delete();
        final Collection<Tag> t = new LinkedList<>();
        Storage storage = Storage.getInstance();
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

        Storage storage = Storage.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");

        List<MediaContent> contents = storage.getMedia();

        update.accessCount(contents.get(3).getAddress());

        Assertions.assertEquals(1, storage.getAccessCounter(contents.get(3).getAddress()));
    }

    @Test
    public void updatePerGetAdress() throws InterruptedException {
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = Storage.getInstance();
        storage.clear();
        Create create = new Create();
        Update update = new Update();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");

        List<MediaContent> contents = storage.getMedia();

        update.accessCount(contents.get(3).getAddress());
        update.accessCount(contents.get(3).getAddress());
        update.accessCount(contents.get(3).getAddress());
        update.accessCount(contents.get(3).getAddress());

        Assertions.assertEquals(4, storage.getCountOfUse().get(contents.get(3).getAddress()));
    }

    @Test
    public void updateRun() throws InterruptedException {
        Storage storage = Storage.getInstance();
        storage.clear();
        Create create = new Create();
        Update update = new Update();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("TimPorsche"), "Tdas");
        List<MediaContent> contents = storage.getMedia();
        update.accessCount(contents.get(0).getAddress());

        Long address = storage.getAccessCounter(contents.get(0).getAddress());

        Assertions.assertEquals(1, storage.getAccessCounter(contents.get(0).getAddress()));
    }

    @Test
    public void updatePerGetAdressMore() throws InterruptedException {
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = Storage.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");

        List<MediaContent> contents = storage.getMedia();

        Update update = new Update();
        update.accessCount(contents.get(3).getAddress());
        update.accessCount(contents.get(3).getAddress());
        update.accessCount(contents.get(3).getAddress());

        update.accessCount(contents.get(0).getAddress());
        update.accessCount(contents.get(0).getAddress());
        update.accessCount(contents.get(0).getAddress());
        update.accessCount(contents.get(0).getAddress());


        String n1 = contents.get(0).getAddress();


        boolean test1 = (null != storage.getCountOfUse().get(n1));
        boolean test2 = (null != storage.getCountOfUse().get(contents.get(0).getAddress()));

        Assertions.assertEquals(test2, test1);
    }

    @Test
    public void readTyp() throws InterruptedException {

        Storage storage = Storage.getInstance();
        storage.clear();
        Create create = new Create();


        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");
        try {
            Read read = new Read();

            List<MediaContent> videos = read.getFullListOrFilterbyTyp("InteractiveVideo");
        } catch (IllegalArgumentException e) {
            Assertions.fail();
        }

    }

    @Test
    public void readTypIsNull() throws InterruptedException {

        Storage storage = Storage.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");
        create.licensedAudioVideo(400, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Stefan Pilz", 200);

        Read read = new Read();

        List<MediaContent> l = read.getFullListOrFilterbyTyp(null);
        List<MediaContent> r = read.getFullListOrFilterbyTyp("interactiveVideo");

        Assertions.assertNotEquals(l.size(), r.size());
    }

    @Test
    public void readTags() throws InterruptedException {
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = Storage.getInstance();
        storage.clear();

        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas");
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, (Uploader) storage.getPerson().iterator().next(), "Tdas");

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

        boolean test = (boolean) storage.getUsedTags().get(Tag.Lifestyle.name());
        boolean test1 = (boolean) storage.getUsedTags().get(Tag.Animal.name());
        boolean test2 = (boolean) storage.getUsedTags().get(Tag.News.name());
        boolean test3 = (boolean) storage.getUsedTags().get(Tag.Tutorial.name());

        Assertions.assertTrue((test && test1) != (test2 && test3));
    }

}
