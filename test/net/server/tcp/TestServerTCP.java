package net.server.tcp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServerTCP {

    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = Mockito.mock(ServerSocket.class);
        Socket socket = Mockito.mock(Socket.class);
        ServerTCP serverTCP = new ServerTCP(serverSocket);

        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        DataInputStream in = Mockito.mock(DataInputStream.class);

        out.writeUTF(":c");

        serverTCP.handleConnection(socket);
        serverTCP.executeSession(in, out);
        serverTCP.run();
    }

}
