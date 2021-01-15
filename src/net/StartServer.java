package net;

import net.server.tcp.Server;

import java.io.IOException;
import java.net.ServerSocket;

public class StartServer {

    public StartServer() {
        System.out.println("Server is starting...");
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
