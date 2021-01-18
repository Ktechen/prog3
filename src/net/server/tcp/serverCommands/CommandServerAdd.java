package net.server.tcp.serverCommands;

import controller.handleInput.InputConverter;
import controller.handleInput.create.CreateOption;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class CommandServerAdd extends CommandServer implements Command {


    public CommandServerAdd(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(InputConverter.USER_TEXT + "\n" + InputConverter.INTER_VIDEO_TEXT + "\n" + InputConverter.LICENSED_AUDIO_VIDEO_TEXT);
        this.handleArgs(this.getMessage().toString());
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

            this.sendMessage(e.getMessage());
            return;
        }

        this.sendMessage("Add " + msg);
    }

}
