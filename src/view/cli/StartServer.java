package view.cli;

import net.server.tcp.Server;

public class StartServer {

    public StartServer() {
        System.out.println("Server is starting...");
        Server server = new Server();
        server.run();
    }
}
