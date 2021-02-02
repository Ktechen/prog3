package controller.management;

import controller.event.EventHandler;
import controller.event.events.commands.show.CommandShowEvents;
import controller.handleInput.InputConverter;
import controller.handleInput.show.ShowOption;
import modell.mediaDB.MediaContent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

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

        ShowOption showOption = new ShowOption();

        switch (args) {
            case "1":
                //Show all
                this.sendMessage(InputConverter.SHOW_ALL_TEXT_VIEW);
                String msg = this.getMessage();
                StringBuffer buffer1 = showOption.run("1", msg);
                this.sendMessage(buffer1.toString());
                break;
            case "2":
                //Uploader
                this.sendMessage(InputConverter.SHOW_Uploader_TEXT_VIEW);
                String msgUser = this.getMessage();
                StringBuffer stringBuffer2 = showOption.run("2", msgUser);
                this.sendMessage(stringBuffer2.toString());
                break;
            case "3":
                //ShowUsedTags
                //TODO add e and i options
                StringBuffer buffer3 = showOption.run("3", "");
                this.sendMessage(buffer3.toString());
                break;
            default:
                this.sendMessage(InputConverter.OPTION_NOT_VALID);
                break;
        }
    }

}
