package net;

import modell.data.storage.Storage;
import net.server.tcp.ServerTCP;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.ServerSocket;

/**
 * TCP ServerTCP = mehrere Clients sind verfügbar
 */
public class StartServer {

    private boolean debug = false;
    private String protocol;
    public final static String TCP_START_TEXT = "ServerTCP TCP is starting...";
    public final static String UDP_START_TEXT = "ServerTCP UDP is starting...";

    public StartServer(String protocol, BigDecimal capacity) {
        this.protocol = protocol;
        Storage.getInstance().setMaxSize(capacity);
    }

    public void run() {
        String validate = this.protocol.toLowerCase();
        switch (validate) {
            case "tcp":
                this.tcp();
                break;
            case "udp":
                System.out.println("ServerTCP UDP is starting...");
                break;
            default:
                System.out.println("Protocol Unknown");
                //TODO Start Default console
                break;
        }
    }

    private void tcp() {
        System.out.println(TCP_START_TEXT);
        ServerTCP serverTCP = null;
        try {
            ServerSocket serverSocket = new ServerSocket(ServerTCP.PORT);
            serverTCP = new ServerTCP(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverTCP.run();
    }

    private void udp() {
        System.out.println(UDP_START_TEXT);
    }
}
