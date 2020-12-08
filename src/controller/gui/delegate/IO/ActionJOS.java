package controller.gui.delegate.IO;

import controller.stream.jos.JOS;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modell.data.storage.Storage;

import java.util.List;


public class ActionJOS {


    /**
     * save storage
     *
     * @param actionEvent = trigger event
     * @param inputJOS    = get input
     * @param hashcode    = unique code
     * @param storage     = savable object
     * @param display     = info text
     * @param list        = add to view list
     */
    public void save(ActionEvent actionEvent, TextField inputJOS, long hashcode, Object storage, Label display, List<String> list) {
        String filename = inputJOS.getText();

        if (filename.isEmpty()) {
            display.setText("Enter Version name");
        } else {
            filename = filename + "@" + hashcode + "@" + "JOS" + ".ser";
            JOS jos = new JOS(filename);
            jos.save(storage);
            display.setText(filename + " has been saved");
            list.add(filename);
        }
    }

    /**
     * Load storage JOS
     *
     * @param actionEvent
     * @param inputJOS
     * @param display
     * @return
     */
    public Storage load(ActionEvent actionEvent, TextField inputJOS, Label display) {
        String filename = inputJOS.getText();
        JOS jos = new JOS(filename);
        display.setText("Load Storage...");
        return (Storage) jos.load();
    }

}
