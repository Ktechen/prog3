import controller.gui.delegate.view.ActionMainWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import view.cli.commands.CommandMain;

public class Main extends Application {

    /**
     * Legt denn Start fest ob mit cli oder gui gestart wird
     */
    private static final int START_SEQ = 1;

    public static void main(String[] args) throws InterruptedException{
        switch (START_SEQ) {
            case 0:
                new CommandMain().run();
                break;
            case 1:
                launch(args);
                break;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        new ActionMainWindow().run(stage);
    }

}
