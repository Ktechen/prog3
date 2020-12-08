package controller.gui.delegate.view;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import modell.data.storage.Storage;
import modell.mediaDB.Content;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Video;


import java.util.Comparator;
import java.util.List;

public class ActionSort {

    public List<Video> address(ActionEvent actionEvent, Storage storage, Label updateDisplay) {
        List<Video> video = storage.getMedia();
        video.sort(Comparator.comparing(Content::getAddress));
        updateDisplay.setText("Sorted by Abrufaddresse");
        return video;
    }

    public List<Video> clicks(ActionEvent actionEvent, Storage storage, Label updateDisplay) {
        List<Video> video = storage.getMedia();
        video.sort(Comparator.comparing(Content::getAccessCount));
        updateDisplay.setText("Sorted by AccessCount");
        return video;
    }

    public List<Uploadable> user(ActionEvent actionEvent, Storage storage, Label updateDisplay) {
        List<Uploadable> video = storage.getMedia();
        video.sort(Comparator.comparing(o -> o.getUploader().getName()));
        updateDisplay.setText("Sorted by Uploader");
        return video;
    }
}
