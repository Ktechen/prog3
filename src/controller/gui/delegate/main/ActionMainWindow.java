package controller.gui.delegate.main;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ActionMainWindow {

    private final String res = "/view/gui/Start.fxml";
    private final String title = "Medienverwalungs - Software by K. Techen";
    private final boolean resizable = true;
    private final int minHeight = 650;
    private final int minWidth = 950;

    public void run(Stage stage) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource(res));
        stage.setScene(new Scene(p));
        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.setMinHeight(minHeight);
        stage.setMinWidth(minWidth);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
        });
        stage.show();
    }

}
