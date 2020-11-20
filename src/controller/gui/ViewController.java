package controller.gui;

import controller.InputConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Uploader;
import view.gui.MediaAlert;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    private ObservableList<Uploadable> list;
    private ObservableList<Uploader> uploaders;

    @FXML
    private ListView<Uploadable> listViewMedia;

    @FXML
    private ListView<Uploader> ListViewUser;

    private final InputConverter inputConverter;

    public ViewController() {
        inputConverter = new InputConverter();
    }


    public void initialize(URL location, ResourceBundle resources) {
        this.list = FXCollections.observableArrayList(StorageAsSingelton.getInstance().getMedia());
        this.listViewMedia.setItems(list);

        this.uploaders = FXCollections.observableArrayList(StorageAsSingelton.getInstance().getPerson());
        this.ListViewUser.setItems(uploaders);
    }

    @FXML
    public void OnClickedCreate(MouseEvent mouseEvent) {
        MediaAlert mediaAlert = new MediaAlert("Create a Media File", InputConverter.INTER_VIDEO_TEXT + " \n" + InputConverter.USER_TEXT, "Media/Person");

        if (mediaAlert.getButtonType() == ButtonType.OK) {
            String input = mediaAlert.getText();
            Object[] value = input.split("\\s+");

            System.out.println("BTN IS OK");
        }
    }

    @FXML
    public void OnMouseClick(MouseEvent mouseEvent) {

    }
}
