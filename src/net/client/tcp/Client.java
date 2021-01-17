package net.client.tcp;

import controller.cli.Console;
import controller.handleInput.InputConverter;
import net.server.tcp.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public Client() {
        System.out.println(toString());
    }

    public void run() {
        //TODO split in and out
        try (Socket socket = new Socket(Server.IP, Server.PORT);
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

    private void executeSession(DataInputStream in, DataOutputStream out) throws IOException, EOFException {
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
