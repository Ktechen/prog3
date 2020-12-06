package controller.gui;

import controller.crud.Create;
import controller.stream.jos.JOS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modell.data.storage.Storage;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.InteractiveVideo;
import modell.mediaDB.MediaContent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class IOController implements Initializable {

    @FXML
    private ListView<String> viewListJOS;

    @FXML
    private ListView<String> viewListJBP;

    private List<String> filenames = new LinkedList<>();

    @FXML
    private TextField inputJOS;

    @FXML
    private Label updateDisplay;

    private Storage storage;

    public IOController() {
        this.storage = StorageAsSingelton.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.loadDir();
        this.update();
        this.updateDisplay.setText("initialized");
    }

    private void loadDir() {
        File folder = new File(JOS.path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                this.filenames.add(file.getName());
            }
        }
    }

    private void update() {
        this.viewListJOS.setItems(FXCollections.observableList(filenames));
        this.viewListJBP.setItems(FXCollections.observableList(filenames));
    }

    //#region JOS

    public void onActionLoadJOS(ActionEvent actionEvent) {
        final String filename = inputJOS.getText();

        int search = filename.indexOf(".");
        if (search == -1) {
            this.updateDisplay.setText("Text isn't a file type");
        } else {
            JOS jos = new JOS(filename);
            Object o = jos.load();

            if (o instanceof InteractiveVideo) {
                System.out.println("o instanceof InteractiveVideo");
            }
        }

        this.filenames.remove(filename);
        this.update();
    }

    public void onActionSaveJOS(ActionEvent actionEvent) {
        String value = inputJOS.getText();

        List<MediaContent> list = storage.getMedia();
        boolean found = false;
        int index = 0;
        for (MediaContent mediaContent : list) {
            if (value.compareTo(mediaContent.getAddress()) == 0) {
                String filename = mediaContent.getClass().getSimpleName() + "@" + mediaContent.hashCode() + "@" + "JOS" +
                        ".ser";
                JOS jos = new JOS(filename);
                jos.save(mediaContent);
                found = true;
                filenames.add(filename);
                this.updateDisplay.setText("Media was been saved");
            }
        }

        if (!found) {
            this.updateDisplay.setText("Address not found");
        }

        this.update();
    }

    //#endregion

    //#region JBP
    public void onActionSaveJBP(ActionEvent actionEvent) {
    }

    public void onActionLoadJBP(ActionEvent actionEvent) {
    }
    //#endregion
}
