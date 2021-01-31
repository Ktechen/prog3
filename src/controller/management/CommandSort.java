package controller.management;

import modell.data.storage.Storage;
import modell.mediaDB.Content;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploadable;

import java.util.Comparator;
import java.util.List;

public class CommandSort {

    public List<MediaContent> address() {
        List<MediaContent> video = Storage.getInstance().getMedia();
        video.sort(Comparator.comparing(Content::getAddress));
        return video;
    }

    public List<MediaContent> clicks() {
        List<MediaContent> video = Storage.getInstance().getMedia();
        video.sort(Comparator.comparing(Content::getAccessCount));
        return video;
    }

    public List<Uploadable> user() {
        List<Uploadable> video = Storage.getInstance().getMedia();
        video.sort(Comparator.comparing(o -> o.getUploader().getName()));
        return video;
    }
}
