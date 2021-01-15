package controller.net;

import net.server.tcp.Server;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TestServer {

    @Test
    public void server() throws IOException {
        Server server = Mockito.mock(Server.class);
        Socket socket = Mockito.mock(Socket.class);
        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        DataInputStream in = Mockito.mock(DataInputStream.class);

        out.writeUTF(":c");

        server.handleConnection(socket);
        server.executeSession(in, out);
        server.run();
    }

}
