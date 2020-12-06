package controller.gui;

import controller.crud.Create;
import controller.crud.Update;
import controller.handle.Const;
import controller.handle.create.CreateOption;
import controller.handle.delete.DeleteOption;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.stage.Stage;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.*;
import view.gui.MediaAlert;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class ViewController implements Initializable {

    private Storage storage;

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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Start                                                                                                              //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //#region start
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
        MediaAlert mediaAlert = new MediaAlert("Update a Media File", "Update via Address ", "Address:");

        if (mediaAlert.getButtonType() == ButtonType.CANCEL) {
            this.updateDisplay.setText("No element was been updated");
        }

        if (mediaAlert.getButtonType() == ButtonType.OK) {
            Update update = new Update();
            update.accessCount(mediaAlert.getText());
            this.updateDisplay.setText("Update");
            System.out.println(mediaAlert.getText());
            System.out.println("Clicks: " + update.getAccessCount(mediaAlert.getText()));
        }
        this.updateAllLists();
    }

    public void configOnAction(ActionEvent actionEvent) {

    }

    public void PersistenzmodusOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/gui/IO.fxml"));
        System.out.println(getClass().getResource("/view/gui/IO.fxml"));
        Parent p = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setTitle("Save");
        stage.show();
    }

    //#endregion

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Drag & Drop                                                                                                        //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void dragAddress(MouseEvent mouseEvent) {
        //System.out.println("Drag event");
    }

    public void dropAddress(DragEvent dragEvent) {
        //System.out.println("Drop event");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Sort                                                                                                               //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //#region sort

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
        List<Uploadable> video = this.storage.getMedia();
        video.sort(Comparator.comparing(o -> o.getUploader().getName()));
        this.updateAllLists(video, this.storage.getPerson());
        this.updateDisplay.setText("Sorted by Uploader");
    }

    //#endregion

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DEBUG                                                                                                             ///
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

            System.out.println(address);
            System.out.println("Clicks: " + update.getAccessCount(address));
        }
        this.updateAllLists();
    }

    public void onActionClearAll(ActionEvent actionEvent) {
        this.storage.clear();
        this.updateAllLists();
    }

    public void onActionSetupOne(ActionEvent actionEvent) {
        final Create create = new Create();
        final Collection<Tag> t = new LinkedList<>();
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);
        final Duration d = Duration.ofSeconds(2000);
        final Person person = new Person("Höchen Flug");

        create.interactiveVideo(100, 400, "vhj", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "mix", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "de", 9174, d, t, person, "Tdas");

        create.licensedAudioVideo(300, 599, "edcs", 9174, d, t, person, "Tim", 233);
        create.licensedAudioVideo(3221, 400, "gjtzu", 9174, d, t, person, "Tim", 233);

        this.updateAllLists();
    }
}
