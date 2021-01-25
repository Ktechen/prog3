import controller.cli.commands.CommandMain;
import controller.gui.delegate.view.ActionMainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Source: https://stackoverflow.com/questions/2016083/what-is-the-easiest-way-to-parallelize-a-task-in-java
 */
public class MainGUIAndCLI extends Application {
    public static void main(String[] args) throws InterruptedException {
        ParallelTasks tasks = new ParallelTasks();
        final Runnable cli = () -> {
            try {
                new CommandMain().run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        final Runnable gui = () -> {
            launch(args);
        };

        tasks.add(cli);
        tasks.add(gui);

        final long start = System.currentTimeMillis();
        tasks.go();
        System.err.println(System.currentTimeMillis() - start);
    }

    @Override
    public void start(Stage stage) throws Exception {
        new ActionMainWindow().run(stage);
    }

    static class ParallelTasks {
        private final Collection<Runnable> tasks = new ArrayList<>();

        public void add(final Runnable task) {
            tasks.add(task);
        }

        public void go() throws InterruptedException {
            final ExecutorService threads = Executors.newFixedThreadPool(Runtime.getRuntime()
                    .availableProcessors());
            try {
                final CountDownLatch latch = new CountDownLatch(tasks.size());
                for (final Runnable task : tasks)
                    threads.execute(() -> {
                        try {
                            task.run();
                        } finally {
                            latch.countDown();
                        }
                    });
                latch.await();
            } finally {
                threads.shutdown();
            }
        }
    }

}

