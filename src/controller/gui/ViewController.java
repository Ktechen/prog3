package controller.gui;

import controller.InputConverter;
import controller.crud.Create;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import modell.data.storage.Storage;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.*;
import view.gui.MediaAlert;

import java.net.URL;
import java.util.*;

public class ViewController implements Initializable {

    private final Storage storage;

    @FXML
    private ListView<Uploadable> listViewMedia;

    @FXML
    private ListView<Uploader> ListViewUser;

    private final InputConverter inputConverter;

    public ViewController() {
        this.storage = StorageAsSingelton.getInstance();
        this.inputConverter = new InputConverter();
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.updateAllLists(this.storage.getMedia(), this.storage.getPerson());
    }

    /**
     * Create Event
     *
     * @param mouseEvent
     */
    public void onClickedCreate(MouseEvent mouseEvent) {
        MediaAlert mediaAlert = new MediaAlert("Create a Media File", InputConverter.INTER_VIDEO_TEXT + " \n" + InputConverter.USER_TEXT, "Media/Person:");

        if (mediaAlert.getButtonType() == ButtonType.OK) {
            String input = mediaAlert.getText();
            Object[] value = input.split("\\s+");

            Create create = new Create();


            System.out.println(input);
        }
    }

    /**
     * Delete Event
     *
     * @param mouseEvent
     */
    public void onDeleteOnClick(MouseEvent mouseEvent) {
        MediaAlert mediaAlert = new MediaAlert("Delete a Media file", "Delete per Adresse and User", "Address/User:");
    }

    /**
     * Update Event
     *
     * @param mouseEvent
     */
    public void onUpdateClick(MouseEvent mouseEvent) {
        MediaAlert mediaAlert = new MediaAlert("Update a Media file", "", "Addresse: ");
    }

    /**
     * Config Event
     *
     * @param mouseEvent
     */
    public void onConfigClick(MouseEvent mouseEvent) {
        MediaAlert mediaAlert = new MediaAlert("Config a Media file", "", "Config");
    }

    /**
     * Persistenzmodus Event
     *
     * @param mouseEvent
     */
    public void onPersistenzmodusClick(MouseEvent mouseEvent) {
        MediaAlert mediaAlert = new MediaAlert("Persistenzmodus a Media file", "", "Persistenzmodus");
    }


    /**
     * Update ListView of media data and person
     * @param uploadable
     * @param uploader
     */
    private void updateAllLists(List<Video> uploadable, LinkedList<Uploader> uploader) {
        this.listViewMedia.setItems(FXCollections.observableArrayList(uploadable));
        this.ListViewUser.setItems(FXCollections.observableArrayList(uploader));
    }

    /**
     * Sort by Address
     * @param mouseEvent
     */
    public void btnSortAbrufClick(MouseEvent mouseEvent) {
        List<Video> video = this.storage.getMedia();
        video.sort(Comparator.comparing(Content::getAddress));
        this.updateAllLists(video, this.storage.getPerson());
    }

    /**
     * Sort by count of clicks
     * @param mouseEvent
     */
    public void btnSortAnzahlClick(MouseEvent mouseEvent) {
        //TODO Buggi
        List<Video> video = this.storage.getMedia();
        video.sort(Comparator.comparing(Content::getAccessCount));
        this.updateAllLists(video, this.storage.getPerson());
    }

    /**
     * Sort by name of director
     * @param mouseEvent
     */
    public void btnSortProduzentClick(MouseEvent mouseEvent) {

    }
}
