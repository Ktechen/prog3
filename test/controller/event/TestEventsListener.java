package controller.event;

import controller.event.events.event.add.EventAddMediaFiles;
import controller.event.events.event.add.EventAddUploader;
import controller.event.events.listener.add.ELAddMediafiles;
import controller.event.events.listener.add.ELAddUploader;
import modell.data.content.Person;
import modell.data.content.InteractiveVideo;
import controller.storage.Storage;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;
import modell.mediaDB.Video;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
        Storage storage = Storage.getInstance();
        storage.clear();
        EventAddUploader event = Mockito.mock(EventAddUploader.class);
        Mockito.when(event.getText()).thenReturn("PaulPanzer");

        ELAddUploader elAddUploader = new ELAddUploader();
        elAddUploader.onInputEvent(event);

        Set<Uploader> uploaderSet = storage.getPerson();

        Assertions.assertEquals("PaulPanzer", uploaderSet.iterator().next().getName());
    }

    @Test
    public void EventListenerAddMediaFiles() throws InterruptedException {
        Storage storage = Storage.getInstance();
        storage.clear();
        EventAddMediaFiles eventAddMediaFiles = Mockito.mock(EventAddMediaFiles.class);
        Mockito.when(eventAddMediaFiles.getType()).thenReturn(InteractiveVideo.class.getSimpleName());
        Mockito.when(eventAddMediaFiles.getArr()).thenReturn(arr);

        ELAddMediafiles elAddMediafiles = new ELAddMediafiles();
        elAddMediafiles.onInputEvent(eventAddMediaFiles);

        Collection<Tag> tag = new ArrayList<>();
        tag.add(Tag.News);
        tag.add(Tag.Lifestyle);
        //(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Person person, String type)

        List<Video> list = storage.getMedia();

        Assertions.assertEquals(200, list.get(0).getWidth());
        Assertions.assertEquals(300, list.get(0).getHeight());
        Assertions.assertEquals("mix", list.get(0).getEncoding());
        Assertions.assertEquals(8373, list.get(0).getBitrate());
        Assertions.assertEquals(Duration.parse("PT20m"), list.get(0).getLength());
        Assertions.assertEquals(tag, list.get(0).getTags());
        Assertions.assertEquals("KevinTechen", list.get(0).getUploader().getName());
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
