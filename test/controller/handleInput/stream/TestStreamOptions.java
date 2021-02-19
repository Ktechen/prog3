package controller.handleInput.stream;

import controller.crud.Create;
import controller.stream.jos.JOS;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class TestStreamOptions {

    @BeforeEach
    public void setup() {
        Storage.getInstance().clear();
        final Create create = new Create();

        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);

        create.video(300, 300,
                "mix", 3232,
                Duration.parse("PT20m"), tags,
                new Person("Lev")
        );
    }

    @Test
    public void streamOptionIsNull() {
        StreamOptions streamOptions = new StreamOptions();

        Assertions.assertThrows(NullPointerException.class, () -> {
            streamOptions.run(null);
        });
    }

    @Test
    public void steamOptionIsEmpty() {
        StreamOptions streamOptions = new StreamOptions();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            streamOptions.run("");
        });
    }

    @Test
    public void streamOptionSelectSaveJOSLoad() {
        StreamOptions streamOptions = new StreamOptions();
        streamOptions.run(StreamOptions.SAVE_JOS);

        File file = new File(JOS.PATH + "file.txt");
        Assertions.assertTrue(file.exists());

        Storage.getInstance().clear();

        streamOptions.run(StreamOptions.LOAD_JOS);

        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());

        file.delete();
    }

    @Test
    public void streamOptionSelectSaveJBPLoad() {
        StreamOptions streamOptions = new StreamOptions();
        streamOptions.run(StreamOptions.SAVE_JBP);

        File file = new File(JOS.PATH + "file.xml");
        Assertions.assertTrue(file.exists());

        Storage.getInstance().clear();

        streamOptions.run(StreamOptions.LOAD_JBP);
        int size = Storage.getInstance().getMedia().size();
        file.delete();

        Assertions.assertEquals(1, size);

    }
}
