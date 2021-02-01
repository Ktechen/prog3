package net.server.tcp;

import controller.management.CommandManagementAdd;
import controller.management.CommandManagementDelete;
import modell.data.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TestCommandManagementTCPDelete {

    @Test
    public void handleArgsDeleteUser() throws IOException {
        Storage.getInstance().clear();

        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        DataInputStream in = Mockito.mock(DataInputStream.class);

        final String name = "name: Kevin";

        CommandManagementAdd commandServerAdd = new CommandManagementAdd(in, out);
        commandServerAdd.handleArgs(name);

        CommandManagementDelete commandServerDelete = new CommandManagementDelete(in, out);
        commandServerDelete.handleArgs("Kevin");

        Assertions.assertEquals(0, Storage.getInstance().getPerson().size());
    }

    @Test
    public void HandleArgsDeleteMedia() throws IOException {
        Storage.getInstance().clear();

        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        DataInputStream in = Mockito.mock(DataInputStream.class);

        final String name = "iv: Kevin";

        CommandManagementAdd commandServerAdd = new CommandManagementAdd(in, out);
        commandServerAdd.handleArgs(name);

        CommandManagementDelete commandServerDelete = new CommandManagementDelete(in, out);
        commandServerDelete.handleArgs("Kevin");

        Assertions.assertEquals(0, Storage.getInstance().getPerson().size());
    }
}
