package controller.cli;

import controller.handleInput.InputConverter;
import controller.management.*;

import java.io.IOException;


public class CommandMain implements Command {

    private Console console;

    private final CommandManagementAdd commandManagementAdd;
    private final CommandManagementShow commandManagementShow;
    private final CommandManagementDelete commandManagementDelete;
    private final CommandManagementUpdate commandManagementUpdate;
    private final CommandManagementConfig commandManagementConfig;
    private final CommandManagementPersistence commandManagementPersistence;
    private final CommandManagementDefault commandManagementDefault;

    public CommandMain() {
        this.console = new Console();

        this.commandManagementAdd = new CommandManagementAdd();
        this.commandManagementAdd.setOffline(true);

        this.commandManagementDelete = new CommandManagementDelete();
        this.commandManagementDelete.setOffline(true);

        this.commandManagementUpdate = new CommandManagementUpdate();
        this.commandManagementUpdate.setOffline(true);

        this.commandManagementConfig = new CommandManagementConfig();
        this.commandManagementConfig.setOffline(true);

        this.commandManagementPersistence = new CommandManagementPersistence();
        this.commandManagementPersistence.setOffline(true);

        this.commandManagementDefault = new CommandManagementDefault();
        this.commandManagementDefault.setOffline(true);

        this.commandManagementShow = new CommandManagementShow();
        this.commandManagementShow.setOffline(true);
    }

    @Override
    public void run() throws IOException {
        while (true) {
            System.out.println(toString());
            this.handleArgs(this.console.input("-------------"));
        }
    }

    @Override
    public void handleArgs(String args) throws IOException {
        switch (args) {
            case ":c":
                System.out.println(CommandManagementAdd.SEND_MSG);
                this.commandManagementAdd.handleArgs(this.console.input("----------"));
                break;
            case ":r":
                System.out.println(CommandManagementShow.SEND_MSG);
                this.commandManagementShow.handleArgs(this.console.input("----------"));
                break;
            case ":d":
                System.out.println(CommandManagementDelete.SEND_MSG);
                this.commandManagementDelete.handleArgs(this.console.input("----------"));
                break;
            case ":u":
                System.out.println(CommandManagementUpdate.SEND_MSG);
                this.commandManagementUpdate.handleArgs(this.console.input("----------"));
                break;
            case ":config":
                System.out.println(CommandManagementConfig.SEND_MSG);
                this.commandManagementConfig.handleArgs(this.console.input("----------"));
                break;
            case ":p":
                System.out.println(CommandManagementPersistence.SEND_MSG);
                this.commandManagementPersistence.handleArgs(this.console.input("----------"));
                break;
            case ":back":
                System.out.println(InputConverter.MAIN_TEXT);
                break;
            default:
                System.out.println(CommandManagementDefault.SEND_MSG);
                this.commandManagementDefault.handleArgs(this.console.input("----------"));
                break;
        }
    }

    public String toString() {
        return InputConverter.MAIN_TEXT;
    }

}
