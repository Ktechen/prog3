package controller.gui;

import controller.crud.Update;
import controller.handle.Const;
import controller.handle.create.CreateOption;
import controller.handle.delete.DeleteOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modell.data.storage.Storage;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.*;
import view.gui.MediaAlert;

import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewController implements Initializable {

    private final Storage storage;

    @FXML
    private ListView<Uploadable> listViewMedia;

    @FXML
    private ListView<Uploader> ListViewUser;

    @FXML
    private Label updateDisplay;

    public ViewController() {
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

    public void createOnAction(ActionEvent actionEvent) {
        String text = Const.LICENSED_AUDIO_VIDEO_TEXT + "\n" + Const.INTER_VIDEO_TEXT + " \n" + Const.USER_TEXT;
        MediaAlert mediaAlert = new MediaAlert("Create a Media File", text, "Media/Person:");

        if (mediaAlert.getButtonType() == ButtonType.CANCEL) {
            this.updateDisplay.setText("Canceled create event");
        }

        if (mediaAlert.getButtonType() == ButtonType.OK) {
            CreateOption createOption = new CreateOption();

            String[] value = mediaAlert.getText().split("\\s+");
            String[] tag = value[0].split(":");
            String[] temp = Arrays.copyOfRange(value, 1, value.length);

            String msg = null;

            try {
                msg = createOption.run(temp, tag[0]);
                this.updateDisplay.setText(msg + " | was been created");
            } catch (NullPointerException e) {
                this.updateDisplay.setText(e.getMessage());
            }
        }
        this.updateAllLists();
    }

    public void deleteOnAction(ActionEvent actionEvent) {

        MediaAlert mediaAlert = new MediaAlert("Create a Media File", "Delete via Addresse or Delete a user \n ", "Adresse/User:");

        if (mediaAlert.getButtonType() == ButtonType.CANCEL) {
            this.updateDisplay.setText("No element was been delete");
        }

        if (mediaAlert.getButtonType() == ButtonType.OK) {
            DeleteOption deleteOption = new DeleteOption();
            String msg = deleteOption.run(mediaAlert.getText());
            this.updateDisplay.setText("Element deleted: " + msg);
        }
        this.updateAllLists();
    }

    /**
     * Source: https://o7planning.org/en/11533/opening-a-new-window-in-javafx
     *
     * @param actionEvent
     */
    public void updateOnAction(ActionEvent actionEvent) {

    }

    public void configOnAction(ActionEvent actionEvent) {

    }

    public void PersistenzmodusOnAction(ActionEvent actionEvent) {
    }

    public void sortAdressOnAction(ActionEvent actionEvent) {
        List<Video> video = this.storage.getMedia();
        video.sort(Comparator.comparing(Content::getAddress));
        this.updateAllLists(video, this.storage.getPerson());
        this.updateDisplay.setText("Sorted by Abrufaddresse");
    }

    public void sortAnzahlOnAction(ActionEvent actionEvent) {
        List<Video> video = this.storage.getMedia();
        video.sort(Comparator.comparing(Content::getAccessCount));
        this.updateAllLists(video, this.storage.getPerson());
        this.updateDisplay.setText("Sorted by AccessCount");
    }

    public void sortProduzentOnAction(ActionEvent actionEvent) {
        List<Uploadable> video = this.storage.getMediaTypeUploadable();
        video.sort(Comparator.comparing(o -> o.getUploader().getName()));
        this.updateAllLists(video, this.storage.getPerson());
        this.updateDisplay.setText("Sorted by Uploader");
    }

    public void mediafileOnClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            PickResult pickResult = mouseEvent.getPickResult();
            String value = pickResult.toString();

            String searchLength = "address=";
            int start = value.indexOf("address=");
            int end = value.indexOf("}");
            String address = value.substring(start + searchLength.length(), end);
            Update update = new Update();
            update.accessCount(address);

            System.out.println("Clicks: " +  update.getAccessCount(address));
        }
    }
}
