package net.server.tcp;

import net.server.tcp.serverCommands.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    public final static String IP = "Localhost";
    public final static int PORT = 8080;
    private ServerSocket serverSocket;

    public Server() {
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Source: https://www.geeksforgeeks.org/datainputstream-read-method-in-java-with-examples/
     * Source: https://github.com/Tryken/SimpleServerClient/
     */
    @Override
    public void run() {
        Socket client = null;
        System.out.println("Server is started " + this.hashCode());

        while (true) {

            try {
                client = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.handleConnection(client);

        }
    }

    private void handleConnection(Socket client) {
        new Thread(() -> {
            try (DataOutputStream out = new DataOutputStream(client.getOutputStream());
                 DataInputStream in = new DataInputStream(client.getInputStream())) {

                System.out.println("client@" + client.getInetAddress() + ":" + client.getPort() + " connected");

                while (!client.isClosed()) {
                    executeSession(in, out);
                }

            } catch (EOFException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("client@" + client.getInetAddress() + ":" + client.getPort() + " disconnected");
                try {
                    client.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }

    private void executeSession(DataInputStream in, DataOutputStream out) throws IOException {
        // System.out.println("Sender object: " + in.readObject());
        String commando = in.readUTF();

        try {
            switch (commando) {
                case ":c":
                    new CommandServerAdd(in, out).run();
                    break;
                case ":r":
                    new CommandServerShow(in, out).run();
                    break;
                case ":d":
                    new CommandServerDelete(in, out).run();
                    break;
                case ":u":
                    new CommandServerUpdate(in, out).run();
                    break;
                case ":config":
                    new CommandServerConfig(in, out).run();
                    break;
                case ":p":
                    new CommandServerPersistence(in, out).run();
                    break;
                case ":back":
                    break;
                default:
                    new CommandServerDefault(in, out).run();
                    break;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
