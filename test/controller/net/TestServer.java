package controller.net;

import net.server.tcp.Server;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestServer {

    @Test
    public void server(){
        Server server = Mockito.mock(Server.class);

        server.run();
    }

}
