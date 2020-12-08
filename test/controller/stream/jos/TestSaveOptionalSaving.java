package controller.stream.jos;

import controller.crud.Create;
import controller.stream.optionalsaving.OptionalSaving;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class TestSaveOptionalSaving {

    @Test
    public void saveAFile() {
        final String filename = "my.txt";
        try {
            Storage storage = StorageAsSingelton.getInstance();
            storage.clear();

            final Collection<Tag> t = new LinkedList<>();
            final Duration d = Duration.ofSeconds(2000);

            t.add(Tag.Lifestyle);
            t.add(Tag.Animal);

            Create create = new Create();
            create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
            create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");

            OptionalSaving optionalSaving = new OptionalSaving(filename);
            optionalSaving.save(storage.getMedia().get(0));

        } catch (Exception e) {
            Assertions.fail();
        }

        File file = new File(filename);
        file.delete();
    }

    @Test
    public void loadAFile() {
        final String filename = "joFileALter.txt";
        try {
            Storage storage = StorageAsSingelton.getInstance();
            storage.clear();

            final Collection<Tag> t = new LinkedList<>();
            final Duration d = Duration.ofSeconds(2000);

            t.add(Tag.Lifestyle);
            t.add(Tag.Animal);

            Create create = new Create();
            create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("Tim Porsche"), "Tdas");
            create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reiner fall"), "Tdas");

            OptionalSaving optionalSaving = new OptionalSaving(filename);
            optionalSaving.save(storage.getMedia().get(0));
        } catch (Exception e) {
            Assertions.fail();
        }

        try {
            OptionalSaving optionalSaving = new OptionalSaving(filename);
            Object o = optionalSaving.load();

            Assertions.assertFalse(o.toString().isEmpty());
        } catch (Exception e) {
            Assertions.fail();
        }

        File file = new File(filename);
        file.delete();
    }

}
