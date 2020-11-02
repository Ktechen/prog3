import crud.*;
import data.Storage;
import data.Person;

import data.StorageAsSingelton;
import mediaDB.Tag;
import mediaDB.Video;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.*;


public class TestCrud {

    private final Collection<Tag> t = new LinkedList<>();
    private final Duration d = Duration.ofSeconds(2000);

    @Test
    public void create() {

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Create create = new Create();

        create.interactiveVideo(500, 400, "edcs", 9174, d, t, new Person("Kevin Techen"), "Tdas" );

        Assertions.assertTrue(storage.getVideo().get(0).getUploader().getName().compareTo("Kevin Techen") == 0 && storage.getVideo().get(0).getWidth() == 500);
    }

    @Test
    public void read() {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Create create = new Create();
        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas" );
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas" );
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas" );
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas" );
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas" );

        String name = "Kevin Techen";

        Read read = new Read();

        HashMap<String, Integer> map = read.listAllUsernamePerIndexValue(name);

        Assertions.assertEquals(map.get(name), 4);
    }

    @Test
    public void readALL() {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");


        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas" );
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas" );
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas" );
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas" );
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "Tdas" );

        create.licensedAudioVideo(500, 400, "edcs", 9174, d, t, storage.getPerson().getFirst(), "LordPau", 300);

        Read read = new Read();

        LinkedList<Video> list = read.fullList();

        Assertions.assertEquals(8, list.size());
    }

    @Test
    public void deletePerAddress() {

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        Create create = new Create();

        create.person("Kevin Techen");

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("Höchen Flug"), "Tdas" );
        create.interactiveVideo(400, 400, "edcs", 9174, d, t, storage.getPerson().get(0),"Tdas");

        Delete delete = new Delete();

        delete.perAddress(storage.getVideo().getLast().getAddress());

        Assertions.assertEquals(3, storage.getVideo().size());
    }

    @Test
    public void deletePerAddressCheckPerson() {

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

        delete.perAddress(storage.getVideo().getLast().getAddress());

        Assertions.assertEquals(3, storage.getPerson().size());
    }

    @Test
    public void deletePerAddressUniqueUser() {

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

        delete.perAddress(storage.getVideo().getLast().getAddress());

        Assertions.assertEquals(2, storage.getPerson().size());

    }

    @Test
    public void deletePerAddressNotUniqueUser() {

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

        delete.perAddress(storage.getVideo().getLast().getAddress());

        Assertions.assertEquals(3, storage.getPerson().size());

    }

    @Test
    public void deletePerUser() {
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

        delete.perUser("Höchen Flug");

        Assertions.assertEquals(2, storage.getVideo().size());
    }

    @Test
    public void deletePerUserCheckPeronList() {
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

        delete.perUser("Höchen Flug");

        Assertions.assertEquals(2, storage.getPerson().size());
    }

    @Test
    public void update() {
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

        update.accessCounter(storage.getVideo().getLast().getAddress());

        Assertions.assertEquals(1, storage.getCountOfUse().get(storage.getVideo().getLast().getAddress()));
    }

    @Test
    public void updatePerGetAdress() {
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

        storage.getVideo().getLast().getAccessCount();
        storage.getVideo().getLast().getAccessCount();
        storage.getVideo().getLast().getAccessCount();
        storage.getVideo().getLast().getAccessCount();

        Assertions.assertEquals(4, storage.getCountOfUse().get(storage.getVideo().getLast().getAddress()));
    }

    @Test
    public void updatePerGetAdressMore() {
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

        storage.getVideo().getLast().getAccessCount();
        storage.getVideo().getLast().getAccessCount();
        storage.getVideo().getLast().getAccessCount();

        storage.getVideo().getFirst().getAccessCount();
        storage.getVideo().getFirst().getAccessCount();
        storage.getVideo().getFirst().getAccessCount();
        storage.getVideo().getFirst().getAccessCount();

        boolean test1 = (3 == storage.getCountOfUse().get(storage.getVideo().getFirst().getAddress()));
        boolean test2 = (4 == storage.getCountOfUse().get(storage.getVideo().getLast().getAddress()));

        Assertions.assertEquals(test2, test1);
    }

    @Test
    public void readTyp() {

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

            LinkedList<? extends Video> videos = read.getFullListOrFilterbyTyp("InteractionAudioVideo");
        } catch (IllegalArgumentException e) {
            Assertions.fail();
        }

    }

    @Test
    public void readTypIsNull() {

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

        LinkedList<? extends Video> l = read.getFullListOrFilterbyTyp(null);
        LinkedList<? extends Video> r = read.getFullListOrFilterbyTyp("interactiveVideo");

        Assertions.assertNotEquals(l.size(), r.size());
    }

    @Test
    public void readTags() {
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
