package controller.gui.delegate.main;

import controller.management.CommandSort;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploadable;


import java.util.List;

public class ActionSort {

    public List<MediaContent> address(ActionEvent actionEvent, Label updateDisplay) {
        updateDisplay.setText("Sorted by Abrufaddresse");
        return new CommandSort().address();
    }

    public List<MediaContent> clicks(ActionEvent actionEvent, Label updateDisplay) {
        updateDisplay.setText("Sorted by AccessCount");
        return new CommandSort().clicks();
    }

    public List<Uploadable> user(ActionEvent actionEvent, Label updateDisplay) {
        updateDisplay.setText("Sorted by Uploader");
        return new CommandSort().user();
    }
}
