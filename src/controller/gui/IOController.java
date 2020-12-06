package controller.gui;

import controller.handle.create.IO.CreateOptionIO;
import controller.stream.Const;
import controller.stream.jos.JOS;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modell.data.storage.Storage;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.MediaContent;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class IOController implements Initializable {

    @FXML
    private ListView<String> viewListJOS;

    @FXML
    private ListView<String> viewListJBP;

    private List<String> filenamesJOS = new LinkedList<>();
    private List<String> filenamesJBP = new LinkedList<>();

    @FXML
    private TextField inputJOS;

    @FXML
    private TextField inputJBP;

    @FXML
    private Label updateAllLists;

    private Storage storage;

    public IOController() {
        this.storage = StorageAsSingelton.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.loadDir();
        this.update();
        this.updateAllLists.setText("initialized");
    }

    /**
     * Source: https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java?page=1&tab=votes#tab-top
     */
    private void loadDir() {
        File folder = new File(Const.path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                this.filenamesJOS.add(file.getName());
            }
        }
    }

    private void update() {
        this.viewListJOS.setItems(FXCollections.observableList(filenamesJOS));
        this.viewListJBP.setItems(FXCollections.observableList(filenamesJBP));
    }

    //#region JOS

    public void onActionLoadJOS(ActionEvent actionEvent) {
        final String filename = inputJOS.getText();

        int search = filename.indexOf(".");
        if (search == -1) {
            this.updateAllLists.setText("Text isn't a file type");
        } else {
            JOS jos = new JOS(filename);
            Object o = jos.load();

            //Call this object
            new CreateOptionIO(o);
            this.updateAllLists.setText(filename + " was been loaded");
        }

        this.update();
    }

    public void onActionSaveJOS(ActionEvent actionEvent) {
        String value = inputJOS.getText();

        List<MediaContent> list = storage.getMedia();
        boolean found = false;
        int index = 0;
        for (MediaContent mediaContent : list) {
            if (value.compareTo(mediaContent.getAddress()) == 0) {
                String filename = mediaContent.getClass().getSimpleName() + "@" + mediaContent.hashCode() + ".ser";
                JOS jos = new JOS(filename);
                jos.save(mediaContent);
                found = true;
                filenamesJOS.add(filename);
                this.updateAllLists.setText("Media was been saved");
            }
        }

        if (!found) {
            this.updateAllLists.setText("Address not found");
        }

        this.update();
    }

    private void addTextFromViewList(MouseEvent event, TextField textField) {
        String value = event.getPickResult().toString();
        String startValue = "[text=";

        if (value.contains(startValue)) {
            int start = value.indexOf(startValue);
            String input = value.substring(start + startValue.length() + 1, value.indexOf(",") - 1);
            textField.setText(input);
        }
    }

    public void onClickJOS(MouseEvent event) {
        addTextFromViewList(event, this.inputJOS);
    }

    //#endregion

    //#region JBP
    public void onActionSaveJBP(ActionEvent actionEvent) {
    }

    public void onActionLoadJBP(ActionEvent actionEvent) {
    }

    public void onClickJBP(MouseEvent event) {
        addTextFromViewList(event, this.inputJBP);
    }

    //#endregion
}
