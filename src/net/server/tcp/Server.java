package net.server.tcp;

import controller.handleInput.InputConverter;
import modell.data.storage.Storage;
import net.server.tcp.serverCommands.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public final static String IP = "Localhost";
    public final static int PORT = 8080;
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Source: https://www.geeksforgeeks.org/datainputstream-read-method-in-java-with-examples/
     * Source: https://github.com/Tryken/SimpleServerClient/
     */
    public void run() {
        System.out.println("Server is started " + this.hashCode());
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

    public void executeSession(DataInputStream in, DataOutputStream out) throws IOException {
        // System.out.println("Sender object: " + in.readObject());
        String commando = in.readUTF();

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
                out.writeUTF(InputConverter.MAIN_TEXT);
                break;
            default:
                new CommandServerDefault(in, out).run();
                break;
        }

        System.out.println("Length of User: " + Storage.getInstance().getPerson().size());
        System.out.println("Length of Media: " + Storage.getInstance().getMedia().size());
    }

}
