package controller.management;

import java.io.IOException;

public interface Command {

    /**
     * Execute methode of option
     * @throws IOException
     */
    void run() throws IOException;

    /**
     * Handle operation
     * @param args
     * @throws IOException
     */
    void handleArgs(String args) throws IOException;
}
