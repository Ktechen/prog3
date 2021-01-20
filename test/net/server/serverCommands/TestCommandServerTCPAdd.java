package net.server.serverCommands;

import modell.data.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class TestCommandServerTCPAdd {


    @Test
    public void testAddUser() throws IOException {
        Storage.getInstance().clear();

        //Kann man auch ohne mockito machen

        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baus = new ByteArrayOutputStream(1024);
        DataOutputStream out = new DataOutputStream(baus);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        DataInputStream in = new DataInputStream(byteArrayInputStream);

        //before out
        CommandServerAdd commandServerAdd = new CommandServerAdd(in, out);

        String name = "name: KevinTechen";
        out.writeUTF(name);

        commandServerAdd.run();

        in.readUTF();

        //after in

        //Cli ohne netzwerk sollte observer funktion da sein

        Assertions.assertEquals(1, Storage.getInstance().getPerson().size());
        Assertions.assertEquals("KevinTechen", Storage.getInstance().getPerson().iterator().next());
    }
}
