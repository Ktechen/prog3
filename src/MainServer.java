import controller.cli.Console;
import controller.gui.delegate.view.ActionDebug;
import net.StartServer;

public class MainServer {
    public static void main(String[] args) {

        /**
         * Set Default elemente
         */
        new ActionDebug().setupOne(null);

        while (true) {
            System.out.println("Der ServerTCP wird mit 2 Argumenten gestartet: Protokoll und Lagerkapazität.");
            final Console console = new Console();
            String protocol = console.input("--------------");
            StartServer startServer = new StartServer(protocol, 5000);
            startServer.run();
        }
    }
}
