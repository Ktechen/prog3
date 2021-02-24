package controller.observer;

import controller.crud.Create;
import controller.crud.Delete;
import controller.observer.observers.ObserverConsoleSize;
import modell.data.content.Person;
import controller.storage.Storage;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class TestObserverConsoleSize {

    @Test
    public void consoleSizeThrowDoesNotException() {
        Create observer = Mockito.mock(Create.class);
        Mockito.when(observer.getCapacity()).thenReturn(BigDecimal.valueOf(300000));

        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(observer);

        try {
            observerConsoleSize.update();
        } catch (Exception e) {
            fail();
        }
    }

    /*
     @Source: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
     */
    @Test
    public void consoleSizeGetSystemOutMessage() {

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        Create observer = Mockito.mock(Create.class);
        Mockito.when(observer.getCapacity()).thenReturn(BigDecimal.valueOf(3000 * 3000));
        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(observer);
        observerConsoleSize.update();
        Assertions.assertEquals("Die Capacity von 90 % wurde überschritten", outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    public void consoleSizeGetSystemOutMessageSize300() {

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        Create observer = Mockito.mock(Create.class);
        Mockito.when(observer.getCapacity()).thenReturn(BigDecimal.valueOf(300));
        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(observer);
        observerConsoleSize.update();
        Assertions.assertEquals("", outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    public void consoleSizeGetSystemOutMessageSizeMax() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        Create observer = Mockito.mock(Create.class);
        Mockito.when(observer.getCapacity()).thenReturn(Storage.getInstance().getMaxSize());
        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(observer);
        observerConsoleSize.update();
        Assertions.assertEquals("Die Capacity von 90 % wurde überschritten", outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    public void consoleSizeCheckCurrentSize() {
        Storage.getInstance().clear();
        final Create create = new Create();
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        new ObserverConsoleSize(create);

        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);
        final Uploader uploader = new Person("Kevin");

        for (int i = 0; i < 9; i++) {
            create.interactiveVideo(2000, 2000, "mix", 7323, Duration.parse("PT20m"), tagCollection, uploader, "type");
        }

        Assertions.assertEquals("Die Capacity von 90 % wurde überschritten", outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void consoleSizeAdd9MediasAndRemove9CurrentSizeEquals0() {
        Storage.getInstance().clear();
        final Create create = new Create();
        new ObserverConsoleSize(create);

        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);
        final Uploader uploader = new Person("Kevin");

        for (int i = 0; i < 9; i++) {
            create.interactiveVideo(2000, 2000, "mix", 7323, Duration.parse("PT20m"), tagCollection, uploader, "type");
        }

        final Delete delete = new Delete();

        List<MediaContent> mediaContents = Storage.getInstance().getMedia();

        for (MediaContent content : mediaContents) {
            delete.perAddress(content.getAddress());
        }

        Assertions.assertEquals(BigDecimal.valueOf(0), Storage.getInstance().getCurrentSize());
    }

}
