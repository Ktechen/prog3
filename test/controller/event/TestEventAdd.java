package controller.event;

import controller.event.events.commands.add.CommandAddEventsMedia;
import controller.event.events.listener.add.ELAddMediafiles;
import controller.handleInput.InputConverter;
import modell.data.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestEventAdd {

    @Test
    public void testAddEventInteractiveVideo() {
        Storage.getInstance().clear();
        EventHandler<ELAddMediafiles> handler = new EventHandler();
        ELAddMediafiles elAddMediafiles = new ELAddMediafiles();
        handler.add(elAddMediafiles);

        final CommandAddEventsMedia commandAddEventsMedia = new CommandAddEventsMedia(new InputConverter(), handler);

        String[] value = new String[8];
        value[0] = "300";
        value[1] = "300";
        value[2] = "mix";
        value[3] = "3043";
        value[4] = "PT30m";
        value[5] = "News";
        value[6] = "KevinTechen";
        value[7] = "F.B.I Gaming Studio";

        commandAddEventsMedia.eventInteractiveVideo(value);

        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testAddEventInteractiveVideoWithNull() {
        Storage.getInstance().clear();
        EventHandler<ELAddMediafiles> handler = new EventHandler();
        ELAddMediafiles elAddMediafiles = new ELAddMediafiles();
        handler.add(elAddMediafiles);
        final CommandAddEventsMedia commandAddEventsMedia = new CommandAddEventsMedia(new InputConverter(), handler);

        Assertions.assertThrows(NullPointerException.class, () -> {
            commandAddEventsMedia.eventInteractiveVideo(null);
        });

        Assertions.assertEquals(0, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testAddEventInteractiveVideoWrongArray() {
        Storage.getInstance().clear();
        EventHandler<ELAddMediafiles> handler = new EventHandler();
        ELAddMediafiles elAddMediafiles = new ELAddMediafiles();
        handler.add(elAddMediafiles);
        final CommandAddEventsMedia commandAddEventsMedia = new CommandAddEventsMedia(new InputConverter(), handler);

        String[] value = new String[8];
        value[0] = "300";
        value[1] = "300";
        value[2] = "mix";
        value[3] = "3043";
        value[4] = "PT30m";
        value[5] = "News";
        value[6] = "KevinTechen";

        try {
            commandAddEventsMedia.eventInteractiveVideo(value);
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("One Parameter is null", e.getMessage());
        }

        Assertions.assertEquals(0, Storage.getInstance().getMedia().size());
    }

}
