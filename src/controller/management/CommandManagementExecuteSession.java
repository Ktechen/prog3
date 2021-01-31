package controller.management;

import controller.handleInput.InputConverter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandManagementExecuteSession implements CommandExecute {

    @Override
    public void executeSession(DataInputStream in, DataOutputStream out) throws IOException {
        // System.out.println("Sender object: " + in.readObject());

        String commando = null;

        commando = in.readUTF();

        switch (commando) {
            case ":c":
                new CommandManagementAdd(in, out).run();
                break;
            case ":r":
                new CommandManagementShow(in, out).run();
                break;
            case ":d":
                new CommandManagementDelete(in, out).run();
                break;
            case ":u":
                new CommandManagementUpdate(in, out).run();
                break;
            case ":config":
                new CommandManagementConfig(in, out).run();
                break;
            case ":p":
                new CommandManagementPersistence(in, out).run();
                break;
            case ":back":
                out.writeUTF(InputConverter.MAIN_TEXT);
                break;
            default:
                new CommandManagementDefault(in, out).run();
                break;
        }
    }
}
