package controller.gui.delegate.IO;


import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modell.data.storage.Storage;
import modell.mediaDB.MediaContent;

import java.util.List;

public class ActionRandomAccessFile {

    public void save(ActionEvent actionEvent, Storage storage, TextField inputOptional, List<String> optional, Label display) {
        String value = inputOptional.getText();

        List<MediaContent> list = storage.getMedia();
        boolean found = false;

        for (MediaContent mediaContent : list) {
            if (value.compareTo(mediaContent.getAddress()) == 0) {
                String filename = mediaContent.getClass().getSimpleName() + "@" + mediaContent.hashCode() + "@" + "OptionalSaving" +
                        ".ser";

                found = true;
                optional.add(filename);
                display.setText("Media was been saved");
            }
        }

        if (!found) {
            display.setText("Address not found");
        }
    }

    public void load(ActionEvent actionEvent, TextField inputOptional, Label display) {
        final String filename = inputOptional.getText();

        int search = filename.indexOf(".");
        if (search == -1) {
           display.setText("Text isn't a file type");
        } else {


            display.setText(filename + " was been loaded");
        }
    }
}
