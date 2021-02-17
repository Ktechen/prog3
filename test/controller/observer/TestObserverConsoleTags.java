package controller.observer;

import controller.crud.Create;
import controller.observer.observers.ObserverConsoleTags;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class TestObserverConsoleTags {

    @Test
    public void testFirstObserverCall() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        HashMap<String, Boolean> map = new HashMap<>();
        map.put(Tag.Animal.name(), false);
        map.put(Tag.Tutorial.name(), false);
        map.put(Tag.Lifestyle.name(), false);
        map.put(Tag.News.name(), false);

        Create create = Mockito.mock(Create.class);
        Mockito.when(create.getTags()).thenReturn(map);
        ObserverConsoleTags observerConsoleTags = new ObserverConsoleTags(create);
        observerConsoleTags.update();

        Assertions.assertEquals("", outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    public void changeCallObserverAgain() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        HashMap<String, Boolean> map = new HashMap<>();
        map.put(Tag.Animal.name(), true);
        map.put(Tag.Tutorial.name(), false);
        map.put(Tag.Lifestyle.name(), false);
        map.put(Tag.News.name(), false);

        Create create = Mockito.mock(Create.class);
        Mockito.when(create.getTags()).thenReturn(map);
        ObserverConsoleTags observerConsoleTags = new ObserverConsoleTags(create);
        observerConsoleTags.update();

        String check = outContent.toString();

        Assertions.assertNotNull(check);
        Assertions.assertFalse(check.isEmpty());


        Assertions.fail();
        System.setOut(originalOut);
    }


}
