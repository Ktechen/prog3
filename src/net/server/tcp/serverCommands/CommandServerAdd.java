package net.server.tcp.serverCommands;

import controller.handle.InputConverter;
import controller.handle.create.CreateOption;

import java.io.*;
import java.util.Arrays;

public class CommandServerAdd extends CommandServer {


    public CommandServerAdd(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    public void run() throws IOException {
        System.out.println("Send Message");
        this.sendMessage(InputConverter.USER_TEXT + "\n" + InputConverter.INTER_VIDEO_TEXT + "\n" + InputConverter.LICENSED_AUDIO_VIDEO_TEXT);
        this.handleArgs(this.getMessage().toString());
    }

    private void handleArgs(String args) throws IOException {
        CreateOption createOption = new CreateOption();

        String[] value = args.split("\\s+");
        String[] tag = value[0].split(":");
        String[] temp = Arrays.copyOfRange(value, 1, value.length);
        String msg = createOption.run(temp, tag[0]);

        this.sendMessage("Add " + msg);
    }

    private void handleTags(){
        //switch ()
    }
}
