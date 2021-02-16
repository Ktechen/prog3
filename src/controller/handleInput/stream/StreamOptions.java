package controller.handleInput.stream;

import controller.handleInput.InputConverter;
import controller.stream.jbp.JBP;
import controller.stream.jos.JOS;
import modell.data.storage.Storage;

public class StreamOptions {

    public String run(String args) {

        if (args == null) {
            throw new NullPointerException("Args is null");
        }

        if (args.isEmpty()) {
            throw new IllegalArgumentException("Args is empty");
        }

        String[] value = args.split(" ");
        String filename = "file";
        JOS jos = new JOS(filename + ".txt");
        JBP jbp = new JBP(filename + ".xml");

        if (value[1] == null) {
            switch (args) {
                case "saveJOS":
                    jos.save(Storage.getInstance());
                    return InputConverter.SAVE_JOS;

                case "loadJOS":
                    Storage storage = (Storage) jos.load();
                    Storage.getInstance().setMedia(storage.getMedia());
                    Storage.getInstance().setPerson(storage.getPerson());
                    Storage.getInstance().setUsedTags(storage.getUsedTags());
                    Storage.getInstance().setCountOfUse(storage.getCountOfUse());
                    return InputConverter.LOAD_JOS;

                case "saveJBP":
                    jbp.save();
                    return InputConverter.SAVE_JBP;

                case "loadJBP":
                    jbp.load();
                    return InputConverter.LOAD_JBP;

                default:
                    return "Unknown Input";
            }
        } else {
            switch (value[0]) {
                case "save":
                    return InputConverter.SAVE_RANDOM_ACCESS;


                case "load":

                    return InputConverter.LOAD_RANDOM_ACCESS;

                default:
                    return "Unknown Input";
            }
        }
    }

}
