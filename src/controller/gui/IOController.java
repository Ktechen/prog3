package controller.gui;

import controller.gui.delegate.IO.ActionJOS;
import controller.gui.delegate.IO.ActionRandomAccessFile;
import controller.gui.delegate.Utils;
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

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class IOController implements Initializable {

    @FXML
    private ListView<String> viewListOptional;

    @FXML
    private ListView<String> listOfViewJOS;

    @FXML
    private TextField inputJOS;

    @FXML
    private ListView<String> viewListJBP;

    private List<String> optional = new LinkedList<>();
    private List<String> jos = new LinkedList<>();

    @FXML
    private TextField inputOptional;

    @FXML
    private TextField inputJBP;

    @FXML
    private Label display;

    private Storage storage;
    private Utils utils;
    private ActionJOS actionJOS;
    private ActionRandomAccessFile accessFile;

    public IOController() {
        this.utils = new Utils();
        this.actionJOS = new ActionJOS();
        this.accessFile = new ActionRandomAccessFile();
        this.storage = StorageAsSingelton.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.utils.loadDir(this.optional);
        this.update();
        this.display.setText("initialized");
    }

    private void update() {
        this.viewListOptional.setItems(FXCollections.observableList(optional));
        this.listOfViewJOS.setItems(FXCollections.observableList(jos));
    }

    //#region JOS

    public void OnActionSaveJOS(ActionEvent actionEvent) {
        this.actionJOS.save(actionEvent, this.inputJOS, this.storage.hashCode(), this.storage, this.display, this.jos);
        this.update();
    }

    public void onActionLoadJOS(ActionEvent actionEvent) {
        this.storage = this.actionJOS.load(actionEvent, this.inputJOS, this.display);
        this.update();
    }

    //#endregion

    //#region OptionalSaving

    public void onActionLoadRandomAccessFile(ActionEvent actionEvent) {
        this.accessFile.load(actionEvent, this.inputOptional, this.display);
        this.update();
    }

    public void onActionSaveRandomAccessFile(ActionEvent actionEvent) {
        this.accessFile.save(actionEvent, this.storage, this.inputOptional, this.optional, this.display);
        this.update();
    }


    public void onClickJOS(MouseEvent event) {
        this.utils.addTextFromViewList(event, this.inputOptional);
    }

    //#endregion

    //#region JBP
    public void onActionSaveJBP(ActionEvent actionEvent) {
    }

    public void onActionLoadJBP(ActionEvent actionEvent) {
    }

    public void onClickJBP(MouseEvent event) {
        this.utils.addTextFromViewList(event, this.inputJBP);
    }


    //#endregion
}
