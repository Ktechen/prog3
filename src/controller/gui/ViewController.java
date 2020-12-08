package controller.gui;

import controller.gui.delegate.IO.ActionWindow;
import controller.gui.delegate.view.ActionCRUD;
import controller.gui.delegate.view.ActionDebug;
import controller.gui.delegate.view.ActionSort;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import modell.data.storage.Storage;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Uploader;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    private Storage storage;

    @FXML
    private ListView<Uploadable> listViewMedia;

    @FXML
    private ListView<Uploader> ListViewUser;

    @FXML
    private Label updateDisplay;

    private final ActionCRUD actionCRUD;
    private final ActionSort actionSort;
    private final ActionWindow actionWindow;
    private final ActionDebug actionDebug;

    public ViewController() {
        this.actionCRUD = new ActionCRUD();
        this.actionSort = new ActionSort();
        this.actionWindow = new ActionWindow();
        this.actionDebug = new ActionDebug();
        this.storage = StorageAsSingelton.getInstance();
    }

    /**
     * init
     *
     * @param location
     * @param resources
     */
    public void initialize(URL location, ResourceBundle resources) {
        this.updateAllLists();
        this.updateDisplay.setText("initialized");
    }

    /**
     * Update ListView of media data and person
     */
    private void updateAllLists() {
        //TODO Property elemente
        this.listViewMedia.setItems(FXCollections.observableArrayList(this.storage.getMedia()));
        this.ListViewUser.setItems(FXCollections.observableArrayList(this.storage.getPerson()));
    }

    /**
     * Update ListView of media data and person
     *
     * @param uploadable
     * @param uploader
     */
    private <T extends Uploadable> void updateAllLists(List<T> uploadable, HashSet<Uploader> uploader) {
        this.listViewMedia.setItems(FXCollections.observableArrayList(uploadable));
        this.ListViewUser.setItems(FXCollections.observableArrayList(uploader));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Start                                                                                                              //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //#region start
    public void createOnAction(ActionEvent actionEvent) {
        this.actionCRUD.create(actionEvent, this.updateDisplay);
        this.updateAllLists();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        this.actionCRUD.delete(actionEvent, this.updateDisplay);
        this.updateAllLists();
    }

    /**
     * Source: https://o7planning.org/en/11533/opening-a-new-window-in-javafx
     *
     * @param actionEvent
     */
    public void updateOnAction(ActionEvent actionEvent) {
        this.actionCRUD.update(actionEvent, this.updateDisplay);
        this.updateAllLists();
    }

    public void configOnAction(ActionEvent actionEvent) {

    }

    public void PersistenzmodusOnAction(ActionEvent actionEvent) throws IOException {
        this.actionWindow.run(actionEvent);
    }

    //#endregion

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Drag & Drop                                                                                                        //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void dragAddress(MouseEvent mouseEvent) {
        //System.out.println("Drag event");
        //TODO ADD DRAG AND DROP
    }

    public void dropAddress(DragEvent dragEvent) {
        //System.out.println("Drop event");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Sort                                                                                                               //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //#region sort

    public void sortAdressOnAction(ActionEvent actionEvent) {
        this.updateAllLists(this.actionSort.address(actionEvent, this.storage, this.updateDisplay), this.storage.getPerson());
    }

    public void sortAnzahlOnAction(ActionEvent actionEvent) {
        this.updateAllLists(this.actionSort.clicks(actionEvent, this.storage, this.updateDisplay), this.storage.getPerson());
    }

    public void sortProduzentOnAction(ActionEvent actionEvent) {
        this.updateAllLists(this.actionSort.user(actionEvent, this.storage, this.updateDisplay), this.storage.getPerson());
    }

    //#endregion

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DEBUG                                                                                                             ///
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void mediafileOnClick(MouseEvent mouseEvent) {
        this.actionDebug.onClick(mouseEvent);
        this.updateAllLists();
    }

    public void onActionClearAll(ActionEvent actionEvent) {
        this.actionDebug.clearAll(actionEvent, this.storage);
        this.updateAllLists();
    }

    public void onActionSetupOne(ActionEvent actionEvent) {
        this.actionDebug.setupOne(actionEvent);
        this.updateAllLists();
    }

    public void onActionUpdateAll(ActionEvent actionEvent) {
        this.updateAllLists();
    }
}
