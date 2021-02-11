package controller.management;

import controller.crud.Create;
import controller.handleInput.InputConverter;
import controller.stream.jbp.JBP;
import controller.stream.jos.JOS;
import modell.bean.BeanStorage;
import modell.data.storage.Storage;

import java.io.*;

public class CommandManagementPersistence extends CommandManagement implements Command {

    public static final String SEND_MSG = InputConverter.PERSISTENCE_TEXT;
    public static final String SAVE_JOS = "File was save by JOS";
    public static final String LOAD_JOS = "File was loaded by JOS";
    public static final String SAVE_JBP = "File was save by JBP";
    public static final String LOAD_JBP = "File was loaded by JBP";
    public static final String SAVE_RANDOM_ACCESS = "File was save by Random Access";
    public static final String LOAD_RANDOM_ACCESS = "File was Load by Random Access";

    public CommandManagementPersistence(DataInputStream in, DataOutputStream out) {
        super(in, out);
    }

    /**
     * Without Network Data Stream
     */
    public CommandManagementPersistence() {
        super(null, null);
    }

    @Override
    public void run() throws IOException {
        this.sendMessage(InputConverter.PERSISTENCE_TEXT);
        this.handleArgs(this.getMessage());
    }

    @Override
    public void handleArgs(String args) throws IOException {

        String[] value = args.split(" ");
        String filename = "file";
        JOS jos = new JOS(filename + ".txt");
        JBP jbp = new JBP(filename + ".xml");


        if (value[1] == null) {
            switch (args) {
                case "saveJOS":
                    jos.save(Storage.getInstance());
                    this.sendMessage(SAVE_JOS);
                    break;

                case "loadJOS":
                    Storage storage = (Storage) jos.load();
                    Storage.getInstance().setMedia(storage.getMedia());
                    Storage.getInstance().setPerson(storage.getPerson());
                    Storage.getInstance().setUsedTags(storage.getUsedTags());
                    Storage.getInstance().setCountOfUse(storage.getCountOfUse());
                    this.sendMessage(LOAD_JOS);
                    break;

                case "saveJBP":
                    jbp.save();
                    this.sendMessage(SAVE_JBP);
                    break;

                case "loadJBP":
                    jbp.load();
                    this.sendMessage(LOAD_JBP);
                    break;
                default:
                    this.sendMessage("Option not found");
                    break;
            }
        } else {
            switch (value[0]) {
                case "save":
                    this.sendMessage(SAVE_RANDOM_ACCESS);
                    break;

                case "load":
                    this.sendMessage(LOAD_RANDOM_ACCESS);
                    break;
                default:
                    this.sendMessage("Option not found");
                    break;
            }
        }


    }
}
