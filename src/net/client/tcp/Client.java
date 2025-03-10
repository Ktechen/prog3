package net.client.tcp;

import controller.cli.Console;
import controller.handleInput.InputConverter;
import net.server.tcp.ServerTCP;

import java.io.*;
import java.net.Socket;

public class Client {

    public Client() {
        System.out.println(toString());

        this.run();
    }

    private void run() {
        //TODO split in and out
        try (Socket socket = new Socket(ServerTCP.IP, ServerTCP.PORT);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            while (true) {
                //socket.setSoTimeout(60000);
                this.executeSession(in, out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeSession(DataInputStream in, DataOutputStream out) throws IOException {
        Console console = new Console();
        String input = console.input("-------------");
        out.writeUTF(input);
        String da = in.readUTF();
        System.out.println(da);
    }

    public String toString() {
        return InputConverter.MAIN_TEXT;
    }
}
