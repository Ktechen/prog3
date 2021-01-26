package controller.crud;

import controller.observer.observers.ObserverConsoleSize;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TestCreate {

    @Test
    public void defaultGetCapacity() {
        Storage.getInstance().clear();
        final Create create = new Create();
        Assertions.assertEquals(BigDecimal.valueOf(0), create.getCapacity());
    }

    @Test
    public void createVideo() {
        Storage.getInstance().clear();
        final Create create = new Create();

        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);

        create.interactiveVideo(
                300, 300,
                "Mix", 3983,
                Duration.parse("PT20m"), tagCollection,
                new Person("Kevin"), "Mix"
        );

        Assertions.assertEquals(BigDecimal.valueOf(0), create.getCapacity());
    }


    @Test
    public void AddObserver() {
        Storage.getInstance().clear();
        final Create create = new Create();
        new ObserverConsoleSize(create);

        Assertions.assertEquals(1, create.getList().size());
    }

    @Test
    public void RemoveObserver() {
        Storage.getInstance().clear();
        final Create create = new Create();
        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(create);

        Assertions.assertEquals(1, create.getList().size());

        create.leave(observerConsoleSize);

        Assertions.assertEquals(0, create.getList().size());
    }

}
