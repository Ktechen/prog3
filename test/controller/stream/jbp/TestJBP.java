package controller.stream.jbp;

import controller.crud.Create;
import modell.bean.BeanItemInteractiveVideo;
import modell.bean.BeanStorage;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class TestJBP {

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
    public void fileNameIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new JBP(null);
        });
    }

    @Test
    public void testJBP() {
        String filename = "test123.xml";
        File file = new File(filename);
        JBP jbp = new JBP(filename);
        jbp.save();
        file.delete();
    }

    @Test
    public void testJBPSaveAndCheckLoadObject() {
        String filename = "test123.xml";
        File file = new File(filename);
        JBP jbp = new JBP(filename);
        jbp.save();
        BeanStorage beanStorage = jbp.load();

        Assertions.assertEquals(2, beanStorage.getMedia().size());
        Assertions.assertEquals(2, beanStorage.getUploaders().size());

        file.delete();
    }

    @Test
    public void testJBPIsNull(){

    }
}
