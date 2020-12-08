package controller.gui.delegate.IO;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ActionWindow {

    private final String res = "/view/gui/IO.fxml";
    private final String title = "Save";

    public void run(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(res));
        //System.out.println(getClass().getResource("/view/gui/IO.fxml"));
        Parent p = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setTitle(title);
        stage.show();
    }
}
