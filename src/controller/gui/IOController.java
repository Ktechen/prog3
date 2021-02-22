package controller.gui;

import controller.handleInput.stream.StreamOptions;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import modell.data.storage.Storage;
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

    private final List<String> listRandomAccess;
    private final List<String> listJos;
    private final List<String> listJbp;
    private final StreamOptions streamOptions;

    @FXML
    private TextField inputOptional;

    @FXML
    private TextField inputJBP;

    @FXML
    private Label display;

    private Storage storage;

    public IOController() {
        this.storage = Storage.getInstance();
        this.streamOptions = new StreamOptions();
        this.listJbp = new LinkedList<>();
        this.listJos = new LinkedList<>();
        this.listRandomAccess = new LinkedList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Old
        //this.utils.loadDir(this.listRandomAccess, "@optional");
        //this.utils.loadDir(this.listJos, "@JOS");
     //

        this.update();
        this.display.setText("initialized");
    }

    private void update() {
        this.viewListOptional.setItems(FXCollections.observableList(listRandomAccess));
        this.listOfViewJOS.setItems(FXCollections.observableList(listJos));
        this.viewListJBP.setItems(FXCollections.observableList(listJbp));
    }

    //#region JOS

    public void OnActionSaveJOS(ActionEvent actionEvent) {
        this.streamOptions.run(StreamOptions.SAVE_JOS);
        this.update();
        this.display.setText("Save via JOS");
    }

    public void onActionLoadJOS(ActionEvent actionEvent) {
        this.streamOptions.run(StreamOptions.LOAD_JOS);
        this.update();
        this.display.setText("Load via JOS");
    }

    //#region JBP
    public void onActionSaveJBP(ActionEvent actionEvent) {
        this.streamOptions.run(StreamOptions.SAVE_JBP);
        this.update();
        this.display.setText("Save via JBP");
    }

    public void onActionLoadJBP(ActionEvent actionEvent) {
        this.streamOptions.run(StreamOptions.LOAD_JBP);
        this.update();
        this.display.setText("Load via JBP");
    }

    public void onClickJBP(MouseEvent event) {
        //this.utils.addTextFromViewList(event, this.inputJBP);
    }

    //#endregion

    //#endregion

    //#region OptionalSaving

    public void onActionLoadRandomAccessFile(ActionEvent actionEvent) {
        this.update();
    }

    public void onActionSaveRandomAccessFile(ActionEvent actionEvent) {
        this.update();
    }

    public void onClickJOS(MouseEvent event) {
        //this.utils.addTextFromViewList(event, this.inputJOS);
    }

    public void onClickOptionalSaving(MouseEvent event) {
    }

    //#endregion

}
