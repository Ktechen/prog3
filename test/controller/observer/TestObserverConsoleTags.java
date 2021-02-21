package controller.observer;

import controller.crud.Create;
import controller.observer.observers.ObserverConsoleTags;
import modell.data.content.Person;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.CallsRealMethods;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;

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




}
