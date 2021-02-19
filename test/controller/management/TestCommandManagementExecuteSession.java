package controller.management;

import controller.handleInput.stream.StreamOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestCommandManagementExecuteSession {

    @Test
    public void testCommandoDefault() throws IOException {

        String name = "Trash: Power";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(name);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));

        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        byte[] bytes = baos.toByteArray();

        String s = new String(bytes);
        String exc = "The option is not valid";

        Assertions.assertEquals(exc, s.replace("\0", "").replace("\u0017", ""));
    }

}
