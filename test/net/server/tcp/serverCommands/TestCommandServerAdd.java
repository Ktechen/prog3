package net.server.tcp.serverCommands;

import modell.data.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TestCommandServerAdd {


    @Test
    public void testAddUser() throws IOException {
        Storage.getInstance().clear();

        DataInputStream in = Mockito.mock(DataInputStream.class);
        DataOutputStream out = Mockito.mock(DataOutputStream.class);

        CommandServerAdd commandServerAdd = new CommandServerAdd(in, out);
        commandServerAdd.run();
        String a = in.readUTF();



        Assertions.assertEquals("KevinTechen", Storage.getInstance().getPerson().iterator().next());
    }
}
