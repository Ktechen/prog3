package controller.handleInput.stream;

import controller.handleInput.InputConverter;
import controller.stream.jbp.JBP;
import controller.stream.jos.JOS;
import modell.data.storage.Storage;

public class StreamOptions {

    public final static String SAVE_JOS = "saveJOS";
    public final static String LOAD_JOS = "loadJOS";
    public final static String SAVE_JBP = "saveJBP";
    public final static String LOAD_JBP = "loadJBP";

    private final String FILENAME = "file";

    public String run(String args) {

        if (args == null) {
            throw new NullPointerException("Args is null");
        }

        if (args.isEmpty()) {
            throw new IllegalArgumentException("Args is empty");
        }

        JOS jos = new JOS(FILENAME + ".txt");
        JBP jbp = new JBP(FILENAME + ".xml");

        switch (args) {
            case SAVE_JOS:
                jos.save(Storage.getInstance());
                return InputConverter.SAVE_JOS;

            case LOAD_JOS:
                Storage storage = (Storage) jos.load();
                Storage.getInstance().setMedia(storage.getMedia());
                Storage.getInstance().setPerson(storage.getPerson());
                Storage.getInstance().setUsedTags(storage.getUsedTags());
                Storage.getInstance().setCountOfUse(storage.getCountOfUse());
                return InputConverter.LOAD_JOS;

            case SAVE_JBP:
                jbp.save();
                return InputConverter.SAVE_JBP;

            case LOAD_JBP:
                jbp.load();
                return InputConverter.LOAD_JBP;

            default:
                return "Unknown Input";
        }

    }

}
