package controller.gui.delegate;

import controller.stream.Const;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.List;

public class Utils {

    public void addTextFromViewList(MouseEvent event, TextField textField) {
        String value = event.getPickResult().toString();
        String startValue = "[text=";

        if (value.contains(startValue)) {
            int start = value.indexOf(startValue);
            String input = value.substring(start + startValue.length() + 1, value.indexOf(",") - 1);
            textField.setText(input);
        }
    }

    /**
     * Source: https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java?page=1&tab=votes#tab-top
     */
    public void loadDir(List<String> optional) {
        File folder = new File(Const.path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                optional.add(file.getName());
            }
        }
    }

}
