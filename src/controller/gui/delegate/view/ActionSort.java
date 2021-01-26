package controller.gui.delegate.view;

import controller.sort.SortTo;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploadable;


import java.util.List;

public class ActionSort {

    public List<MediaContent> address(ActionEvent actionEvent, Label updateDisplay) {
        updateDisplay.setText("Sorted by Abrufaddresse");
        return new SortTo().address();
    }

    public List<MediaContent> clicks(ActionEvent actionEvent, Label updateDisplay) {
        updateDisplay.setText("Sorted by AccessCount");
        return new SortTo().clicks();
    }

    public List<Uploadable> user(ActionEvent actionEvent, Label updateDisplay) {
        updateDisplay.setText("Sorted by Uploader");
        return new SortTo().user();
    }
}
