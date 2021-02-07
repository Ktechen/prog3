package net.server.tcp;

import controller.management.CommandExecute;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerUDP implements CommandExecute {

    private DatagramSocket socket;
    private byte[] buffer;

    public ServerUDP() {
        this.buffer = new byte[1024];
        try {
            this.socket = new DatagramSocket(ServerTCP.PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        try {
            while (!socket.isClosed()) {
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                packet = new DatagramPacket(buffer, buffer.length, address, port);

                socket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

    }

    @Override
    public void executeSession(DataInputStream in, DataOutputStream out) throws IOException {

    }
}
