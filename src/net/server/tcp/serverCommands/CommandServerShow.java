package net.server.tcp.serverCommands;

import controller.event.EventHandler;
import controller.event.events.commands.CommandShowEvents;
import controller.handleInput.InputConverter;
import modell.data.content.interaction.InteractiveVideo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
                this.sendMessage("Please enter a filter like: " + InteractiveVideo.class.getSimpleName() + " or No Filter enter 'No'");

                StringBuffer buffer1;
                String msg = this.getMessage().toString();
                if (msg.toLowerCase().equals("no")) {
                    buffer1 = new CommandShowEvents(new EventHandler<>()).eventShowAll(null);
                } else {
                    buffer1 = new CommandShowEvents(new EventHandler<>()).eventShowAll(msg);
                }

                this.sendMessage(buffer1.toString());
                break;
            case "2":
                this.sendMessage("Please enter your name");
                StringBuffer stringBuffer2 = new CommandShowEvents(new EventHandler<>()).eventUsernamePerIndexValue(this.getMessage().toString());
                this.sendMessage(stringBuffer2.toString());
                break;
            case "3":
                StringBuffer buffer3 = new CommandShowEvents(new EventHandler<>()).eventShowUsedTags();
                this.sendMessage(buffer3.toString());
                break;
            default:
                this.sendMessage("The option is not valid");
                break;
        }
    }

}
