package net.server.tcp;

import controller.management.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP implements CommandExecute{

    public final static String IP = "Localhost";
    public final static int PORT = 8080;
    private ServerSocket serverSocket;

    public ServerTCP(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Source: https://www.geeksforgeeks.org/datainputstream-read-method-in-java-with-examples/
     * Source: https://github.com/Tryken/SimpleServerClient/
     */
    public void run() {
        System.out.println("ServerTCP is started " + this.hashCode());
        Socket socket = null;
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.handleConnection(socket);
        }
    }

    public void handleConnection(Socket socket) {
        new Thread(() -> {
            try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                 DataInputStream in = new DataInputStream(socket.getInputStream())) {

                System.out.println("client@" + socket.getInetAddress() + ":" + socket.getPort() + " connected");

                while (!socket.isClosed()) {
                    executeSession(in, out);
                }

            } catch (EOFException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("client@" + socket.getInetAddress() + ":" + socket.getPort() + " disconnected");
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }

    @Override
    public void executeSession(DataInputStream in, DataOutputStream out) throws IOException {
        new CommandManagementExecuteSession().executeSession(in, out);
    }

}
