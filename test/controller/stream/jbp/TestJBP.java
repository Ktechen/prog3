package controller.stream.jbp;

import controller.crud.Create;
import controller.crud.Update;
import modell.data.content.Person;
import controller.storage.Storage;
import modell.mediaDB.InteractiveVideo;
import modell.mediaDB.MediaContent;
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

        final Create create = new Create();
        final Update update = new Update();
        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");

        MediaContent content1 = (MediaContent) Storage.getInstance().getMedia().get(0);

        MediaContent content2 = (MediaContent) Storage.getInstance().getMedia().get(1);

        for (int i = 0; i < 5; i++) {
            update.accessCount(content2.getAddress());
        }

        for (int i = 0; i < 1000; i++) {
            update.accessCount(content1.getAddress());
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
        File file = new File(JBP.PATH + filename);
        JBP jbp = new JBP(filename);
        jbp.save();
        file.delete();
        Storage.getInstance().clear();
    }

    @Test
    public void testJBPSaveAndCheckLoadObject() {
        String filename = "test1234.xml";
        File file = new File(JBP.PATH + filename);
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
        String filename = "test12345.xml";
        File file = new File(JBP.PATH + filename);
        JBP jbp = new JBP(filename);
        jbp.save();
        jbp.load();

        InteractiveVideo content0 = (InteractiveVideo) Storage.getInstance().getMedia().get(2);
        InteractiveVideo content1 = (InteractiveVideo) Storage.getInstance().getMedia().get(3);


        Assertions.assertEquals(5, content1.getAccessCount());
        Assertions.assertEquals(9174, content1.getBitrate());
        Assertions.assertEquals(400, content1.getHeight());
        Assertions.assertEquals(200, content1.getWidth());
        Assertions.assertEquals("edcs", content1.getEncoding());
        Assertions.assertEquals("Tdas", content1.getType());
        Assertions.assertEquals("Reinerfall", content1.getUploader().getName());

        Assertions.assertEquals(1000, content0.getAccessCount());
        Assertions.assertEquals(9174, content0.getBitrate());
        Assertions.assertEquals(400, content0.getHeight());
        Assertions.assertEquals(100, content0.getWidth());
        Assertions.assertEquals("edcs", content0.getEncoding());
        Assertions.assertEquals("Tdas", content0.getType());
        Assertions.assertEquals("TimPorsche", content0.getUploader().getName());

        file.delete();
    }

    @Test
    public void testJBPSaveAllTypesOfMediaAndCheckTheyAreInStorage() {
        Storage.getInstance().clear();
        final String filename = "test1235633.xml";
        final File file = new File(JBP.PATH + filename);
        final JBP jbp = new JBP(filename);

        final Collection<Tag> t = new LinkedList<>();
        final Duration d = Duration.ofSeconds(2000);

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        final Create create = new Create();
        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.audioVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), 2323);
        create.video(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"));
        create.licensedVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Holder");
        create.audio(300, Duration.parse("PT20m"), t, 3232, "mix", new Person("Kevin Power"));
        create.licensedAudio(300, Duration.parse("PT20m"), t, 3232, "mix", new Person("Kevin Power"), "Peter");
        create.licensedAudioVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Holder", 23232);

        jbp.save();
        jbp.load();

        //Add as Duplication
        Assertions.assertEquals(14, Storage.getInstance().getMedia().size());

        file.delete();
    }

}
