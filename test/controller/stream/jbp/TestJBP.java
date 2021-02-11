package controller.stream.jbp;

import controller.crud.Create;
import controller.crud.Update;
import modell.bean.BeanItemInteractiveVideo;
import modell.bean.BeanStorage;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.InteractiveVideo;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.time.Duration;
import java.util.*;

public class TestJBP {

    @BeforeEach
    public void setup() {
        Storage storage = Storage.getInstance();
        storage.clear();

        final Collection<Tag> t = new LinkedList<>();
        final Duration d = Duration.ofSeconds(2000);

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        final Create create = new Create();
        final Update update = new Update();
        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");

        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(1);

        for (int i = 0; i < 5; i++) {
            update.accessCount(content.getAddress());
        }
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
        Storage.getInstance().clear();
    }

    @Test
    public void testJBPSaveAndCheckLoadObject() {
        String filename = "test123.xml";
        File file = new File(filename);
        JBP jbp = new JBP(filename);
        jbp.save();
        jbp.load();

        Assertions.assertEquals(4, Storage.getInstance().getMedia().size());
        Assertions.assertEquals(2, Storage.getInstance().getPerson().size());

        //System.out.println(Arrays.toString(Storage.getInstance().getMedia().));

        file.delete();
        Storage.getInstance().clear();
    }

    @Test
    public void testJBPCheckAllParameterRight() {
        String filename = "CheckAllParameterRight.xml";
        File file = new File(filename);
        JBP jbp = new JBP(filename);
        jbp.save();
        jbp.load();

        InteractiveVideo content = (InteractiveVideo) Storage.getInstance().getMedia().get(Storage.getInstance().getMedia().size()-1);

        Assertions.assertEquals(5, content.getAccessCount());
        Assertions.assertEquals(9174, content.getBitrate());
        Assertions.assertEquals(400, content.getHeight());
        Assertions.assertEquals(200, content.getWidth());
        Assertions.assertEquals("edcs", content.getEncoding());
        Assertions.assertEquals("Tdas", content.getType());
        Assertions.assertEquals("Reinerfall", content.getUploader().getName());

        file.delete();
    }

}
