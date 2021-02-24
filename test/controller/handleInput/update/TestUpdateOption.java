package controller.handleInput.update;

import controller.crud.Create;
import modell.data.content.Person;
import controller.storage.Storage;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class TestUpdateOption {

    @Test
    public void testUpdateInputAddressNotFound() {
        final UpdateOption testUpdateOption = new UpdateOption();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testUpdateOption.run("lalal");
        });
    }

    @Test
    public void testUpdateInputIsNull() {
        final UpdateOption testUpdateOption = new UpdateOption();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testUpdateOption.run(null);
        });
    }

    @Test
    public void testUpdate() {
        Storage.getInstance().clear();
        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Tutorial);

        create.audio(300, Duration.parse("PT20m"), tags, 3232, "mix", new Person("God"));
        final UpdateOption updateOption = new UpdateOption();

        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(0);

        updateOption.run(content.getAddress());

        long counter = (long) Storage.getInstance().getCountOfUse().get(content.getAddress());

        Assertions.assertEquals(1, counter);
    }
}
