package modell.storage;

import controller.crud.Create;
import modell.bean.BeanItemLicensedAudio;
import modell.data.content.Audio;
import controller.storage.Storage;
import modell.data.content.Person;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploadable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
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

    @Test
    public void addOnlyMediaContentOrUploadable() {
        Storage storage = Storage.getInstance();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);

        try {
            storage.addMedia(new Audio(300, Duration.parse("PT20m"), tags, 2932, "mix", new Person("Kev")));
        } catch (Exception e) {
            Assertions.fail();
        }

    }

    @Test
    public void addNotMediaContentOrUploadable() {
        Storage storage = Storage.getInstance();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);

        BeanItemLicensedAudio beanItemLicensedAudio = new BeanItemLicensedAudio();
        try {
            storage.addMedia((Uploadable) beanItemLicensedAudio);
        } catch (ClassCastException e) {
            Assertions.assertTrue(true);
        }
    }

}
