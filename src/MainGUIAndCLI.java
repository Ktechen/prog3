import controller.ParallelTasks;
import controller.cli.CommandMain;
import controller.crud.Create;
import controller.gui.delegate.main.ActionMainWindow;
import controller.observer.observers.ObserverConsoleSize;
import controller.observer.observers.ObserverConsoleTags;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class MainGUIAndCLI extends Application {
    public static void main(String[] args) throws InterruptedException {
        ParallelTasks tasks = new ParallelTasks();

        /**
         * Source: https://stackoverflow.com/questions/2016083/what-is-the-easiest-way-to-parallelize-a-task-in-java
         */
        final Runnable cli = () -> {
            try {
                final Create create = new Create();
                new ObserverConsoleSize(create);
                new ObserverConsoleTags(create);
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

