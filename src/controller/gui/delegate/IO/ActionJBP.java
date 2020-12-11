package controller.gui.delegate.IO;

import controller.stream.jbp.JBP;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modell.data.storage.Storage;

import java.util.List;

public class ActionJBP {

    /**
     * save storage
     *
     * @param actionEvent = trigger event
     * @param inputJOS    = get input
     * @param hashcode    = unique code
     * @param display     = info text
     * @param list        = add to view list
     */
    public void save(ActionEvent actionEvent, TextField inputJOS, long hashcode, Label display, List<String> list) {
        String filename = inputJOS.getText();

        if (filename.isEmpty()) {
            display.setText("Enter Version name");
        } else {
            filename = filename + "@" + hashcode + "@" + "JBP" + ".ser.xml";
            JBP jbp = new JBP(filename);
            jbp.save();
            display.setText(filename + " has been saved");
            list.add(filename);
        }
    }

    /**
     * Load storage JOS
     *
     * @param actionEvent = trigger event
     * @param inputJOS    = get input
     * @param display     = info text
     * @return storage object
     */
    public Storage load(ActionEvent actionEvent, TextField inputJOS, Label display) {
        String filename = inputJOS.getText();

        if (filename.isEmpty()) {
            display.setText("Enter a Filename");
        } else {
            JBP jbp = new JBP(filename);
            Object o = jbp.load();
            display.setText("Load Storage by JBP ...");
            return (Storage) o;
        }

        return null;
    }

}
