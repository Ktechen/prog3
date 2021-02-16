import controller.ParallelTasks;
import controller.cli.CommandMain;
import controller.gui.delegate.view.ActionMainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class MainGUIAndCLI extends Application {
    public static void main(String[] args) throws InterruptedException {
        ParallelTasks tasks = new ParallelTasks();
        final Runnable cli = () -> {
            try {
                new CommandMain().run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        final Runnable gui = () -> {
            launch(args);
        };

        tasks.add(cli);
        tasks.add(gui);

        tasks.go();
    }

    @Override
    public void start(Stage stage) throws Exception {
        new ActionMainWindow().run(stage);
    }
}

