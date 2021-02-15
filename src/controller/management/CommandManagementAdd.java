package controller.management;

import controller.handleInput.InputConverter;
import controller.handleInput.create.CreateOption;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class CommandManagementAdd extends CommandManagement implements Command {

    public static final String SEND_MSG = InputConverter.USER_TEXT + "\n" + InputConverter.INTER_VIDEO_TEXT + "\n" + InputConverter.LICENSED_AUDIO_VIDEO_TEXT;

    public CommandManagementAdd(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    /**
     * Without Network Data Stream
     */
    public CommandManagementAdd() {
        super(null, null);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(SEND_MSG);
        this.handleArgs(this.getMessage());
    }

    @Override
    public void handleArgs(String args) throws IOException {
        CreateOption createOption = new CreateOption();

        String[] value = args.split("\\s+");
        String[] tag = value[0].split(":");
        String[] temp = Arrays.copyOfRange(value, 1, value.length);

        String msg = null;

        try {
            msg = createOption.run(temp, tag[0]);

        } catch (NullPointerException |
                ArrayIndexOutOfBoundsException |
                DateTimeParseException |
                IllegalArgumentException e) {

            if (!this.isOffline()) {
                this.sendMessage(e.getMessage());
            } else {
                e.printStackTrace();
            }

            return;
        }
        if (!this.isOffline()) {
            this.sendMessage("Add " + msg);
        } else {
            System.out.println("Add " + msg);
        }

    }

}
