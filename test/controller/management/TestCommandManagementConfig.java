package controller.management;

import controller.crud.Create;
import controller.management.CommandManagementConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TestCommandManagementConfig {

    @Test
    public void testConfig() throws IOException {
        DataInputStream in = Mockito.mock(DataInputStream.class);
        DataOutputStream out = Mockito.mock(DataOutputStream.class);

        final Create create = new Create();

        final CommandManagementConfig commandServerConfig = new CommandManagementConfig(in, out);
        commandServerConfig.handleArgs("remove");

        Assertions.assertEquals(0, create.getList().size());
    }

}
