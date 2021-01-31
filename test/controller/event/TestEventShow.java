package controller.event;

import controller.crud.Create;
import controller.event.events.commands.show.CommandShowEvents;
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

    @BeforeEach
    public void setup() {
        final Collection<Tag> t = new LinkedList<>();
        final Duration d = Duration.ofSeconds(2000);
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

    @Test
    public void showUsedTagsChangeAllToTrue() {
        final Create create = new Create();
        final Collection<Tag> t = new LinkedList<>();
        t.add(Tag.News);
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);
        t.add(Tag.Tutorial);

        create.interactiveVideo(500, 400, "edcs", 9174, Duration.ofSeconds(2000), t, new Person("KevinTechen"), "Tdas");

        CommandShowEvents commandShowEvents = new CommandShowEvents(new InputConverter(), new EventHandler<>());
        StringBuffer stringBuffer = commandShowEvents.eventShowUsedTags();
        String expected = "[Tutorial, Lifestyle, Animal, News] | [true, true, true, true]";

        Assertions.assertEquals(expected, stringBuffer.toString());
    }

    @Test
    public void UsernamePerIndexValue() {
        final CommandShowEvents commandShowEvents = new CommandShowEvents(new InputConverter(), new EventHandler<>());
        StringBuffer stringBuffer = commandShowEvents.eventUsernamePerIndexValue("KevinTechen");

        String expected = "[KevinTechen] | [4]";

        Assertions.assertEquals(expected, stringBuffer.toString());
    }


    @Test
    public void UsernamePerIndexValueWithWhiteSpaceInName() {
        final CommandShowEvents commandShowEvents = new CommandShowEvents(new InputConverter(), new EventHandler<>());
        StringBuffer stringBuffer = commandShowEvents.eventUsernamePerIndexValue("Kevin Techen");
        String expected = "[KevinTechen] | [4]";

        Assertions.assertEquals(expected, stringBuffer.toString());
    }

    @Test
    public void UsernamePerIndexValueWrongName(){
        final CommandShowEvents commandShowEvents = new CommandShowEvents(new InputConverter(), new EventHandler<>());
        StringBuffer stringBuffer = commandShowEvents.eventUsernamePerIndexValue("Paul Reiner");
        String expected = "[PaulReiner] | [0]";

        Assertions.assertEquals(expected, stringBuffer.toString());
    }
}
