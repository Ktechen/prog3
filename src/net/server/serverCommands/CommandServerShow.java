package net.server.serverCommands;

import controller.event.EventHandler;
import controller.event.events.commands.CommandShowEvents;
import controller.handleInput.InputConverter;
import modell.data.content.interaction.InteractiveVideo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandServerShow extends CommandServer implements Command {



    public CommandServerShow(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(InputConverter.SHOW_ALL + InputConverter.SHOW_PER_INDEX + InputConverter.SHOW_TAGS);
        this.handleArgs(this.getMessage().toString());
    }

    @Override
    public void handleArgs(String args) throws IOException {
        switch (args) {
            case "1":
                //Show all
                this.sendMessage(InputConverter.SHOW_ALL_TEXT_VIEW);
                StringBuffer buffer1;
                String msg = this.getMessage().toString();
                buffer1 = new CommandShowEvents(new InputConverter(), new EventHandler<>()).eventShowAll(msg);
                this.sendMessage(buffer1.toString());
                break;
            case "2":
                //Uploader
                this.sendMessage(InputConverter.SHOW_Uploader_TEXT_VIEW);
                StringBuffer stringBuffer2 = new CommandShowEvents(new InputConverter(), new EventHandler<>()).eventUsernamePerIndexValue(this.getMessage().toString());
                this.sendMessage(stringBuffer2.toString());
                break;
            case "3":
                //ShowUsedTags
                StringBuffer buffer3 = new CommandShowEvents(new InputConverter(), new EventHandler<>()).eventShowUsedTags();
                this.sendMessage(buffer3.toString());
                break;
            default:
                this.sendMessage(InputConverter.OPTION_NOT_VALID);
                break;
        }
    }

}
