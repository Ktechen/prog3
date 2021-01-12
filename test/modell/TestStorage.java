package modell;

import controller.crud.Create;
import modell.data.storage.Storage;
import modell.data.content.Person;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class TestStorage {

    @Test
    public void equalsStorage() throws InterruptedException {
        Storage storage = Storage.getInstance();
        storage.clear();

        final Collection<Tag> t = new LinkedList<>();
        final Duration d = Duration.ofSeconds(2000);

        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Create create = new Create();
        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");

        Assertions.assertEquals(storage.getMedia().size(), Storage.getInstance().getMedia().size());
    }

}
