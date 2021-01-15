package view.cli;

import net.client.tcp.Client;

public class Cli {
    public Cli() {
        Client client = new Client();
        client.run();
    }
}
