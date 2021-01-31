package controller.management;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface CommandExecute {

    /**
     * Management from execute Streams
     * @param in = DataInputStream
     * @param out = DataOutputStream
     * @throws IOException
     */
    void executeSession(DataInputStream in, DataOutputStream out) throws IOException;

}
