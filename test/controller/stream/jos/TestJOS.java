package controller.stream.jos;

import controller.crud.Create;
import modell.data.content.Person;
import controller.storage.Storage;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class TestJOS {

    @BeforeEach
    public void setup() {
        Storage storage = Storage.getInstance();
        storage.clear();

        final Collection<Tag> t = new LinkedList<>();
        final Duration d = Duration.ofSeconds(2000);

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Create create = new Create();
        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");
    }

    @Test
    public void createViaJOSFile() {
        String filename = "TestJOS";
        JOS jos = new JOS(filename);
        jos.save(Storage.getInstance());
        File file = new File(JOS.PATH + filename);

        Assertions.assertTrue(file.exists());
        Assertions.assertEquals(filename, file.getName());
        Assertions.assertTrue(file.getUsableSpace() > 0);
        file.delete();
    }

    @Test
    public void loadViaJOSFile() {
        String filename = "TestJOS";
        JOS jos = new JOS(filename);
        jos.save(Storage.getInstance());
        File file = new File(JOS.PATH + filename);
        Storage o = (Storage) jos.load();

        Assertions.assertEquals(2, o.getMedia().size());
        Assertions.assertEquals(2, o.getPerson().size());
        file.delete();
    }

    @Test
    public void saveFileIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new JOS(null);
        });
    }
}
