package net.server.serverCommands;

import modell.data.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TestCommandServerTCPDelete {

    @Test
    public void handleArgsDeleteUser() throws IOException {
        Storage.getInstance().clear();

        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        DataInputStream in = Mockito.mock(DataInputStream.class);

        final String name = "name: Kevin";

        CommandServerAdd commandServerAdd = new CommandServerAdd(in, out);
        commandServerAdd.handleArgs(name);

        CommandServerDelete commandServerDelete = new CommandServerDelete(in, out);
        commandServerDelete.handleArgs("Kevin");

        Assertions.assertEquals(0, Storage.getInstance().getPerson().size());
    }

    @Test
    public void HandleArgsDeleteMedia() throws IOException {
        Storage.getInstance().clear();

        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        DataInputStream in = Mockito.mock(DataInputStream.class);

        final String name = "iv: Kevin";

        CommandServerAdd commandServerAdd = new CommandServerAdd(in, out);
        commandServerAdd.handleArgs(name);

        CommandServerDelete commandServerDelete = new CommandServerDelete(in, out);
        commandServerDelete.handleArgs("Kevin");

        Assertions.assertEquals(0, Storage.getInstance().getPerson().size());
    }
}
