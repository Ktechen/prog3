import controller.gui.delegate.main.ActionMainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        new ActionMainWindow().run(stage);
    }

}
