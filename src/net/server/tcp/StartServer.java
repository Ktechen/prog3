package net.server.tcp;

public class StartServer {
    public static void main(String[] args) {
        System.out.println("Server is starting...");
        Server server = new Server();
        server.run();
    }
}
