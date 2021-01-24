package controller.gui;

import controller.gui.delegate.IO.ActionJBP;
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
import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploadable;

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

    @FXML
    private ListView<Uploadable> listViewMedia;

    private List<String> optional = new LinkedList<>();
    private List<String> listJos = new LinkedList<>();
    private List<String> listJbp = new LinkedList<>();

    @FXML
    private TextField inputOptional;

    @FXML
    private TextField inputJBP;

    @FXML
    private Label display;

    private Storage storage;
    private final Utils utils;
    private final ActionJOS actionJOS;
    private final ActionJBP actionJBP;
    private final ActionRandomAccessFile accessFile;

    public IOController() {
        this.utils = new Utils();
        this.actionJOS = new ActionJOS();
        this.accessFile = new ActionRandomAccessFile();
        this.actionJBP = new ActionJBP();
        this.storage = Storage.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.utils.loadDir(this.optional, "@optional");
        this.utils.loadDir(this.listJos, "@JOS");
        this.utils.loadDir(this.listJbp, "@JBP");
        this.update();
        this.display.setText("initialized");
    }

    private void update() {
        this.viewListOptional.setItems(FXCollections.observableList(optional));
        this.listOfViewJOS.setItems(FXCollections.observableList(listJos));
        this.viewListJBP.setItems(FXCollections.observableList(listJbp));
    }

    //#region JOS

    public void OnActionSaveJOS(ActionEvent actionEvent) {
        this.actionJOS.save(actionEvent, this.inputJOS, this.storage.hashCode(), this.storage, this.display, this.listJos);
        this.update();
    }

    public void onActionLoadJOS(ActionEvent actionEvent) {
        Storage temp = this.actionJOS.load(actionEvent, this.inputJOS, this.display);

        if (null != temp) {
            this.storage.setMedia(temp.getMedia());
            this.storage.setPerson(temp.getPerson());
            this.storage.setCountOfUse(temp.getCountOfUse());
            this.storage.setUsedTags(temp.getUsedTags());
        }

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
        this.utils.addTextFromViewList(event, this.inputJOS);
    }

    //#endregion

    //#region JBP
    public void onActionSaveJBP(ActionEvent actionEvent) {
        this.actionJBP.save(actionEvent, this.inputJBP, this.storage.hashCode(), this.display, this.listJbp);
        this.update();
    }

    public void onActionLoadJBP(ActionEvent actionEvent) {
        //LinkedList<MediaContent> contents = (LinkedList<MediaContent>) this.actionJBP.load(actionEvent, this.inputJBP, this.display);
        Storage temp = (Storage) this.actionJBP.load(actionEvent, this.inputJBP, this.display);

        if (null != temp) {
            this.storage.setMedia(temp.getMedia());
            this.storage.setPerson(temp.getPerson());
            this.storage.setCountOfUse(temp.getCountOfUse());
            this.storage.setUsedTags(temp.getUsedTags());
        }

        this.update();
    }

    public void onClickJBP(MouseEvent event) {
        this.utils.addTextFromViewList(event, this.inputJBP);
    }

    public void onClickOptionalSaving(MouseEvent event) {
    }


    //#endregion
}
