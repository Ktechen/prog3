import controller.crud.Create;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modell.data.content.Person;
import modell.mediaDB.Tag;
import view.cli.commands.CommandMain;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class Main extends Application {

    /**
     * Legt denn Start fest ob mit cli oder gui gestart wird
     */
    private static final int START_SEQ = 1;

    public static void main(String[] args) throws InterruptedException {
        switch (START_SEQ) {
            case 0:
                startCli();
                break;
            case 1:
                launch(args);
                break;
        }
    }

    private static void startCli() {
        try {
            new CommandMain().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent p = FXMLLoader.load(getClass().getResource("view/gui/Start.fxml"));
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
