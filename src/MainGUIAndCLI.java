import controller.ParallelTasks;
import controller.cli.commands.CommandMain;
import controller.gui.delegate.view.ActionMainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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

