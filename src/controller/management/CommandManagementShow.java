package controller.management;

import controller.event.EventHandler;
import controller.event.events.commands.show.CommandShowEvents;
import controller.handleInput.InputConverter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandManagementShow extends CommandManagement implements Command {

    public static final String SEND_MSG = InputConverter.SHOW_ALL + InputConverter.SHOW_PER_INDEX + InputConverter.SHOW_TAGS;

    public CommandManagementShow(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    /**
     * Without Network Data Stream
     */
    public CommandManagementShow() {
        super(null, null);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(SEND_MSG);
        this.handleArgs(this.getMessage());
    }

    @Override
    public void handleArgs(String args) throws IOException {
        switch (args) {
            case "1":
                //Show all
                this.sendMessage(InputConverter.SHOW_ALL_TEXT_VIEW);
                StringBuffer buffer1;
                String msg = this.getMessage();
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
