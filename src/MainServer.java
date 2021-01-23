import controller.cli.Console;
import controller.gui.delegate.view.ActionDebug;
import controller.handleInput.InputConverter;
import net.StartServer;

public class MainServer {
    public static void main(String[] args) {

        /**
         * Set Default elemente
         */
        new ActionDebug().setupOne(null);

        while (true) {
            System.out.println(InputConverter.TCP_SERVER_TEXT);
            final Console console = new Console();
            String protocol = console.input("--------------");
            //lagerkapazität gesamt Speicher aller Dateien
            StartServer startServer = new StartServer(protocol, 5000);
            startServer.run();
        }
    }
}
