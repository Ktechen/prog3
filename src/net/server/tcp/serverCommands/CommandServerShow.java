package net.server.tcp.serverCommands;

import controller.handleInput.InputConverter;

import java.io.*;

public class CommandServerShow extends CommandServer {


    public CommandServerShow(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    public void run() throws IOException {
        this.sendMessage(InputConverter.SHOW_ALL + InputConverter.SHOW_PER_INDEX + InputConverter.SHOW_TAGS);
        this.handleArgs(this.getMessage().toString());
    }

    private void handleArgs(String args) throws IOException {
        switch (args) {
            case "1":
                this.sendMessage("Pls Enter a Filter or Press Enter");
                System.out.println(this.getMessage().toString());
                break;
            case "2":
                break;
            case "3":
                break;
            default:
                break;
        }
    }

    private String showAll() {
        return "";
    }
}
