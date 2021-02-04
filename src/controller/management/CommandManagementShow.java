package controller.management;

import controller.cli.Console;
import controller.handleInput.InputConverter;
import controller.handleInput.show.ShowOption;

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

        ShowOption showOption = new ShowOption();

        switch (args) {
            case "1":
                //Show all

                if (!this.isOffline()) {
                    try {
                        this.sendMessage(InputConverter.SHOW_ALL_TEXT_VIEW);
                        String msg = this.getMessage();
                        StringBuffer buffer1 = showOption.run("1", msg);
                        this.sendMessage(buffer1.toString());
                    } catch (NullPointerException e) {
                        this.sendMessage(e.getMessage());
                    }
                } else {
                    try {
                        System.out.println(InputConverter.SHOW_ALL_TEXT_VIEW);
                        String msg = new Console().input("------------");
                        StringBuffer buffer1 = showOption.run("1", msg);
                        System.out.println(buffer1.toString());
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
            case "2":
                //Uploader
                if (!this.isOffline()) {
                    try {
                        this.sendMessage(InputConverter.SHOW_Uploader_TEXT_VIEW);
                        String msgUser = this.getMessage();
                        StringBuffer stringBuffer2 = showOption.run("2", msgUser);
                        this.sendMessage(stringBuffer2.toString());
                    } catch (NullPointerException e) {
                        this.sendMessage(e.getMessage());
                    }

                } else {
                    try {
                        System.out.println(InputConverter.SHOW_Uploader_TEXT_VIEW);
                        String msg = new Console().input("------------");
                        StringBuffer buffer1 = showOption.run("2", msg);
                        System.out.println(buffer1.toString());
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
            case "3":
                //ShowUsedTags
                if (!this.isOffline()) {
                    try {
                        //TODO add e and i options
                        this.sendMessage(InputConverter.SHOW_TAG);
                        StringBuffer buffer3 = showOption.run("3", this.getMessage());
                        this.sendMessage(buffer3.toString());
                    } catch (NullPointerException e) {
                        this.sendMessage(e.getMessage());
                    }
                } else {
                    try {
                        System.out.println(InputConverter.SHOW_TAG);
                        String msg = new Console().input("------------");
                        StringBuffer buffer1 = showOption.run("3", msg);
                        System.out.println(buffer1.toString());
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                }

                break;
            default:
                if (!this.isOffline()) {
                    this.sendMessage(InputConverter.OPTION_NOT_VALID);
                } else {
                    System.out.println(InputConverter.OPTION_NOT_VALID);
                }
                break;
        }
    }

}
