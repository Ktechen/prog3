package net.server.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public final static String IP = "Localhost";
    public final static int PORT = 9000;

    void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            try (Socket socket = serverSocket.accept();
                 DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                System.out.println("client: " + socket.getInetAddress() + ":" + socket.getPort());
                while (true) {
                    out.writeInt(-in.readInt());
                }
            } catch (EOFException e) {
                System.out.println("client disconnect");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
