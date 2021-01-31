package controller.event;

import controller.crud.Create;
import controller.event.events.commands.CommandShowEvents;
import controller.handleInput.InputConverter;
import modell.data.content.Audio;
import modell.data.content.Person;
import modell.data.content.interaction.InteractiveVideo;
import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class TestEventShow {

    private final Collection<Tag> t = new LinkedList<>();
    private final Duration d = Duration.ofSeconds(2000);

    @BeforeEach
    public void setup() {
        Create create = new Create();
        Storage storage = Storage.getInstance();
        storage.clear();

        create.person("Kevin Techen");

        t.add(Tag.News);
        t.add(Tag.Lifestyle);

        create.interactiveVideo(100, 400, "edcs", 9174, d, t, new Person("TimPorsche"), "Tdas");
        create.interactiveVideo(200, 400, "edcs", 9174, d, t, new Person("Reinerfall"), "Tdas");
        create.interactiveVideo(300, 400, "edcs", 9174, d, t, new Person("HöchenFlug"), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, new Person("KevinTechen"), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, new Person("KevinTechen"), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, new Person("KevinTechen"), "Tdas");
        create.interactiveVideo(500, 400, "edcs", 9174, d, t, new Person("KevinTechen"), "Tdas");
    }

    @Test
    public void filterByNull() {
        CommandShowEvents commandShowEvents = new CommandShowEvents(new InputConverter(), new EventHandler<>());
        StringBuffer stringBuffer = commandShowEvents.eventShowAll(null);
        Assertions.assertTrue(stringBuffer.length() != 0);
        Assertions.assertEquals(2181, stringBuffer.length());
    }

    @Test
    public void filterByInteractiveVideo() {
        CommandShowEvents commandShowEvents = new CommandShowEvents(new InputConverter(), new EventHandler<>());
        StringBuffer stringBuffer = commandShowEvents.eventShowAll(InteractiveVideo.class.getSimpleName());
        Assertions.assertTrue(stringBuffer.length() != 0);
        Assertions.assertEquals(2181, stringBuffer.length());
    }

    @Test
    public void filterByNoTyps() {
        CommandShowEvents commandShowEvents = new CommandShowEvents(new InputConverter(), new EventHandler<>());
        StringBuffer stringBuffer = commandShowEvents.eventShowAll(Audio.class.getSimpleName());
        Assertions.assertTrue(stringBuffer.length() == 0);
        Assertions.assertEquals(0, stringBuffer.length());
    }

    @Test
    public void showUsedTags() {
        CommandShowEvents commandShowEvents = new CommandShowEvents(new InputConverter(), new EventHandler<>());
        StringBuffer stringBuffer = commandShowEvents.eventShowUsedTags();
        String expected = "[Tutorial, Lifestyle, Animal, News] | [false, true, false, true]";

        Assertions.assertEquals(expected, stringBuffer.toString());
    }
}
