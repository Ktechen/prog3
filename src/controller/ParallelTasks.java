package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelTasks {
    private final Collection<Runnable> tasks = new ArrayList<>();

    public void add(final Runnable task) {
        tasks.add(task);
    }

    /**
     * Source: https://stackoverflow.com/questions/2016083/what-is-the-easiest-way-to-parallelize-a-task-in-java
     */
    public void go() throws InterruptedException {
        final ExecutorService threads = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

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
