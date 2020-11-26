package controller.gui;

import controller.InputConverter;
import controller.crud.Create;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import modell.data.storage.Storage;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.*;
import view.gui.MediaAlert;

import java.net.URL;
import java.time.Duration;
import java.util.*;

public class ViewController implements Initializable {

    private final static String ERROR_MESSAGE_INCORRECT_INPUT = "Input was incorrect";
    private final Storage storage;

    @FXML
    private ListView<Uploadable> listViewMedia;

    @FXML
    private ListView<Uploader> ListViewUser;

    @FXML
    private Label updateDisplay;

    private final InputConverter inputConverter;

    public ViewController() {
        this.storage = StorageAsSingelton.getInstance();
        this.inputConverter = new InputConverter();
    }

    /**
     * init
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
        this.listViewMedia.setItems(FXCollections.observableArrayList(this.storage.getMedia()));
        this.ListViewUser.setItems(FXCollections.observableArrayList(this.storage.getPerson()));
    }

    /**
     * Update ListView of media data and person
     *
     * @param uploadable
     * @param uploader
     */
    private void updateAllLists(List<Video> uploadable, LinkedList<Uploader> uploader) {
        this.listViewMedia.setItems(FXCollections.observableArrayList(uploadable));
        this.ListViewUser.setItems(FXCollections.observableArrayList(uploader));
    }

    /**
     * Create Event
     *
     * @param mouseEvent
     */
    public void onClickedCreate(MouseEvent mouseEvent) throws InterruptedException {
        MediaAlert mediaAlert = new MediaAlert("Create a Media File", InputConverter.INTER_VIDEO_TEXT + " \n" + InputConverter.USER_TEXT, "Media/Person:");

        if (mediaAlert.getButtonType() == ButtonType.OK) {
            String input = mediaAlert.getText();

            if (input.length() > 0) {

                String[] value = input.split("\\s+");

                //Name convert to one string
                if (value.length == 2) {
                    value[0] = value[0] + value[1];
                    value[1] = null;
                }

                Create create = new Create();

                switch (value.length) {
                    case 1:
                    case 2:
                        create.person(value[0]);
                        this.updateDisplay.setText("You has been added a new Person file");
                        break;

                    case 8:
                        Object[] inter = new InputConverter().convertToArr(value);
                        create.interactiveVideo((Integer) inter[0], (Integer) inter[1], (String) inter[2], (Long) inter[3],
                                (Duration) inter[4], (Collection<Tag>) inter[5], (Uploader) inter[6], (String) inter[7]);
                        this.updateDisplay.setText("You has been added a new Interactive Video file");
                        break;
                    case 9:
                        Object[] lic = new InputConverter().convertToArrLicVideo(value);
                        create.licensedAudioVideo((Integer) lic[0], (Integer) lic[1], (String) lic[2], (Long) lic[3], (Duration) lic[4],
                                (Collection<Tag>) lic[5], (Uploader) lic[6], (String) lic[7], (Integer) lic[8]);
                        this.updateDisplay.setText("You has been added a new licensed Audio Video file");
                        break;
                    default:
                        this.updateDisplay.setText(ERROR_MESSAGE_INCORRECT_INPUT);
                        break;
                }
            }
            this.updateDisplay.setText(ERROR_MESSAGE_INCORRECT_INPUT);
        }
        this.updateAllLists();
    }

    /**
     * Delete Event
     *
     * @param mouseEvent
     */
    public void onDeleteOnClick(MouseEvent mouseEvent) {
        MediaAlert mediaAlert = new MediaAlert("Delete a Media file", "Delete per Address or User", "Address/User:");
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
     * Sort by Address
     *
     * @param mouseEvent
     */
    public void btnSortAbrufClick(MouseEvent mouseEvent) {
        List<Video> video = this.storage.getMedia();
        video.sort(Comparator.comparing(Content::getAddress));
        this.updateAllLists(video, this.storage.getPerson());
        this.updateDisplay.setText("Sorted by Abrufaddresse");
    }

    /**
     * Sort by count of clicks
     *
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
     *
     * @param mouseEvent
     */
    public void btnSortProduzentClick(MouseEvent mouseEvent) {

    }
}
