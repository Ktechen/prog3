package controller.event;

import controller.event.events.commands.CommandAddEvents;
import controller.handleInput.InputConverter;
import modell.data.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestEventAdd {

    @Test
    public void testAddEventInteractiveVideo() {
        Storage.getInstance().clear();
        final CommandAddEvents commandAddEvents = new CommandAddEvents(new InputConverter(), new EventHandler<>());

        String[] value = new String[8];
        value[0] = "300";
        value[1] = "300";
        value[2] = "mix";
        value[3] = "3043";
        value[4] = "PT30m";
        value[5] = "News";
        value[6] = "KevinTechen";
        value[7] = "F.B.I Gaming Studio";

        commandAddEvents.eventInteractiveVideo(value);

        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testAddEventInteractiveVideoWithNull() {
        Storage.getInstance().clear();
        final CommandAddEvents commandAddEvents = new CommandAddEvents(new InputConverter(), new EventHandler<>());

        Assertions.assertThrows(NullPointerException.class, () -> {
            commandAddEvents.eventInteractiveVideo(null);
        });

        Assertions.assertEquals(0, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testAddEventInteractiveVideoWrongArray() {
        Storage.getInstance().clear();
        final CommandAddEvents commandAddEvents = new CommandAddEvents(new InputConverter(), new EventHandler<>());

        String[] value = new String[8];
        value[0] = "300";
        value[1] = "300";
        value[2] = "mix";
        value[3] = "3043";
        value[4] = "PT30m";
        value[5] = "News";
        value[6] = "KevinTechen";

        try {
            commandAddEvents.eventInteractiveVideo(value);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("One Parameter is null", e.getMessage());
        }

        Assertions.assertEquals(0, Storage.getInstance().getMedia().size());
    }

}
