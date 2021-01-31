package controller.management;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import modell.data.storage.Storage;
import modell.mediaDB.Content;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Video;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
