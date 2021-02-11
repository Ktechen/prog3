package controller.stream.randomAccess;

import controller.crud.Create;
import javafx.beans.property.StringProperty;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.InteractiveVideo;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class TestRandomAccessHandle {

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
    }

    @Test
    public void convert() {
        RandomAccessHandle randomAccessHandle = new RandomAccessHandle();
        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(0);
        byte[] bytes = randomAccessHandle.convertToBytes(content.getAddress());

        Object o = randomAccessHandle.convertFromBytes(bytes);
        Assertions.assertTrue(o instanceof InteractiveVideo);
        InteractiveVideo video = (InteractiveVideo) o;

        Assertions.assertEquals(100, video.getWidth());
        Assertions.assertEquals(400, video.getHeight());
        Assertions.assertEquals("edcs", video.getEncoding());
        Assertions.assertEquals(9174, video.getBitrate());
        Assertions.assertEquals("Tdas", video.getType());
    }

}
