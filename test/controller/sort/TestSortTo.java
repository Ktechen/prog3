package controller.sort;

import modell.data.content.Audio;
import modell.data.content.AudioVideo;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.Content;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;

public class TestSortTo {

    @Test
    public void sortByAddress() {
        Storage.getInstance().clear();
        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);



        Storage.getInstance().addMedia(
                new Audio(9403, Duration.parse("PT20m"),
                        tagCollection, 333, "Mix",
                        new Person("Jombo"))
        );

        Storage.getInstance().addMedia(
                new Audio(9323, Duration.parse("PT30m"),
                        tagCollection, 2211, "Lol",
                        new Person("Tim"))
        );

        Storage.getInstance().addMedia(
                new AudioVideo(300, 300,
                        "mix", 3923,
                        Duration.parse("PT20m"), tagCollection,
                        new Person("FBI"), 3932)
        );

        //long bitrate, Duration duration, Collection<Tag> tags, int samplingRate, String endcoding, Uploader uploader
        Storage.getInstance().addMedia(
                new Audio(300, Duration.parse("PT10m"),
                        tagCollection, 30943, "Mix",
                        new Person("Paul"))
        );

        Storage.getInstance().addMedia(
                new Audio(400, Duration.parse("PT50m"),
                        tagCollection, 2222, "Von",
                        new Person("Rambo"))
        );


        //int width, int height, String endcoding, long bitrate, Duration duration, Collection<Tag> tags, Uploader uploader, int samplingRate
        Storage.getInstance().addMedia(
                new AudioVideo(300, 300,
                        "mix", 3923,
                        Duration.parse("PT20m"), tagCollection,
                        new Person("FBI"), 3932)
        );


        Collections.shuffle(Storage.getInstance().getMedia());

        List<MediaContent> contentList = Storage.getInstance().getMedia();
        contentList.sort(Comparator.comparing(Content::getAddress));

        SortTo sortTo = new SortTo();
        List<MediaContent> list = sortTo.address();

        for (int i = 0; i < contentList.size(); i++) {
            Assertions.assertEquals(list.get(i), contentList.get(i));
        }
    }

}
