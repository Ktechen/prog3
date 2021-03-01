import controller.cli.CommandMain;
import controller.crud.Create;
import controller.gui.delegate.main.ActionDebug;
import controller.observer.observers.ObserverConsoleSize;
import controller.observer.observers.ObserverConsoleTags;

import java.io.IOException;

public class MainCLI {


    public static void main(String[] args) {
        /*
         * Set Default elemente

         */
        //new ActionDebug().setupOne(null);

        try {
            config();
            new CommandMain().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void config() {
        final Create create = new Create();
        new ObserverConsoleSize(create);
        new ObserverConsoleTags(create);
    }
}
