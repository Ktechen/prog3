package controller.management;

import controller.handleInput.InputConverter;
import controller.handleInput.delete.DeleteOption;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandManagementDelete extends CommandManagement implements Command {

    public static final String SEND_MSG = InputConverter.DELETE_USER + "\n" + InputConverter.DELETE_ADDRESSE;

    public CommandManagementDelete(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    /**
     * Without Network Data Stream
     */
    public CommandManagementDelete() {
        super(null, null);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(SEND_MSG);
        this.handleArgs(this.getMessage());
    }

    @Override
    public void handleArgs(String args) throws IOException {

        try {
            DeleteOption deleteOption = new DeleteOption();
            if (!isOffline()) {
                this.sendMessage(deleteOption.run(args));
            } else {
                System.out.println(deleteOption.run(args));
            }
        } catch (NullPointerException e) {
            if (!isOffline()) {
                this.sendMessage(e.getMessage());
            } else {
                System.out.println(e.getMessage());
            }
        }

    }
}
