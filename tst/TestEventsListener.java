import data.Storage;
import data.StorageAsSingelton;
import data.content.InteractionAudioVideo;
import data.content.Person;
import event.events.event.add.EventAddMediaFiles;
import event.events.event.add.EventAddUploader;
import event.events.listener.add.ELAddMediafiles;
import event.events.listener.add.ELAddUploader;
import mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class TestEventsListener {

    public Object[] arr = new Object[8];

    @BeforeEach
    public void addArr(){
        arr[0] = Integer.parseInt("200");
        arr[1] = Integer.parseInt("300");
        arr[2] = "mix";
        arr[3] = Long.parseLong("8373");
        arr[4] = Duration.parse("PT20m");

        Collection<Tag> tag = new ArrayList<>();
        tag.add(Tag.News);
        tag.add(Tag.Lifestyle);
        arr[5] = tag;
        arr[6] = new Person("KevinTechen");
        arr[7] = "Typeof";
    }

    @Test
    public void EventListenerAddUpdate() {
        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        EventAddUploader event = Mockito.mock(EventAddUploader.class);
        Mockito.when(event.getText()).thenReturn("PaulPanzer");

        ELAddUploader elAddUploader = new ELAddUploader();
        elAddUploader.onInputEvent(event);

        Assertions.assertEquals("PaulPanzer", storage.getPerson().getFirst().getName());
    }

    @Test
    public void EventListenerAddMediaFiles() {
        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();
        EventAddMediaFiles eventAddMediaFiles = Mockito.mock(EventAddMediaFiles.class);
        Mockito.when(eventAddMediaFiles.getType()).thenReturn(InteractionAudioVideo.class.getSimpleName());
        Mockito.when(eventAddMediaFiles.getArr()).thenReturn(arr);

        ELAddMediafiles elAddMediafiles = new ELAddMediafiles();
        elAddMediafiles.onInputEvent(eventAddMediaFiles);

        Collection<Tag> tag = new ArrayList<>();
        tag.add(Tag.News);
        tag.add(Tag.Lifestyle);
        //(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Person person, String type)

        Assertions.assertEquals(200, storage.getMedia().getFirst().getWidth());
        Assertions.assertEquals(300, storage.getMedia().getFirst().getHeight());
        Assertions.assertEquals("mix", storage.getMedia().getFirst().getEncoding());
        Assertions.assertEquals(8373, storage.getMedia().getFirst().getBitrate());
        Assertions.assertEquals(Duration.parse("PT20m"), storage.getMedia().getFirst().getLength());
        Assertions.assertEquals(tag, storage.getMedia().getFirst().getTags());
        Assertions.assertEquals("KevinTechen", storage.getMedia().getFirst().getUploader().getName());
    }

    /* TODO change show element
    @Test
    public void ShowEvents(){
        Storage storage = StorageAsSingelton.getInstance();
        storage.clear();

        EventShowAll eventShowAll = Mockito.mock(EventShowAll.class);
        Mockito.when(eventShowAll.getText()).thenReturn(Arrays.toString(arr));

        ELShowAll elShowAll = new ELShowAll();
        elShowAll.onInputEvent(eventShowAll);
    }
     */
}
