package net;

import net.server.tcp.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class StartServer {

    private boolean debug = false;
    private String protocol;
    private int Lagerkapazität;

    public StartServer(String protocol, int Lagerkapazität) {
        this.protocol = protocol;
        this.Lagerkapazität = Lagerkapazität;
    }

    public void run() {
        String validate = protocol.toLowerCase();
        switch (validate) {
            case "tcp":
                this.tcp();
                break;
            case "udp":
                System.out.println("Server UDP is starting...");
                break;
            default:
                System.out.println("Protocol Unknown");
                break;
        }
    }

    private void tcp() {
        System.out.println("Server TCP is starting...");
        Server server = null;
        try {
            ServerSocket serverSocket = new ServerSocket(Server.PORT);
            server = new Server(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.run();
    }
}
