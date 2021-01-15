import controller.cli.Console;
import net.StartServer;

public class MainServer {
    public static void main(String[] args) {
        do {
            System.out.println("Der Server wird mit 2 Argumenten gestartet: Protokoll und Lagerkapazität.");
            final Console console = new Console();
            String protocol = console.input("--------------");
            StartServer startServer = new StartServer(protocol, 5000);
            startServer.run();
        } while (true);

    }
}
