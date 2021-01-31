package controller.management;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;

public class TestCommandManagementTCPShow {

    @BeforeEach
    public void setup() {
        DataInputStream in = Mockito.mock(DataInputStream.class);
        DataOutputStream out = Mockito.mock(DataOutputStream.class);

        final String name = "lav: 300 400 Mix 9382 20m News Kevin Paul 2035";
        final String next = "lav: 1000 1500 Power 320 30m News Kevin Paul 2035";

        CommandManagementAdd commandServerAdd = new CommandManagementAdd(in, out);
        try {
            commandServerAdd.handleArgs(name);
            commandServerAdd.handleArgs(next);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void handleArgsShowAll() throws IOException {
        // DataOutputStream out = new DataOutputStream(new BufferedOutputStream());
        // DataInputStream in = new DataInputStream(new BufferedInputStream());


        // CommandManagementShow commandServerShow = new CommandManagementShow(in, out);
        // commandServerShow.handleArgs("1");


    }

}
