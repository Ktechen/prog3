import controller.cli.commands.CommandMain;
import controller.crud.Create;
import controller.observer.observers.ObserverConsoleSize;

import java.io.IOException;

public class MainCLI {
    public static void main(String[] args) {
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
    }
}
