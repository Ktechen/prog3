package controller.gui.delegate.view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ActionMainWindow {

    public void run(Stage stage) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/view/gui/Start.fxml"));
        stage.setScene(new Scene(p));
        stage.setTitle("Medienverwalungs - Software by K. Techen");
        stage.setResizable(true);
        stage.setMinHeight(650);
        stage.setMinWidth(900);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
        });
        stage.show();
    }

}
