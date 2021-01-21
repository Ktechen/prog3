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

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        byte[] bytes = new byte[1024];
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));

        String name = "name: KevinTechen";
        out.writeUTF(name);
        //before out
        CommandServerAdd commandServerAdd = new CommandServerAdd(in, out);


        commandServerAdd.run();

        in.readUTF();

        //after in

        //Cli ohne netzwerk sollte observer funktion da sein

        Assertions.assertEquals(1, Storage.getInstance().getPerson().size());
        Assertions.assertEquals("KevinTechen", Storage.getInstance().getPerson().iterator().next());
    }
}
