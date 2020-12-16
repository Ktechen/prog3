package controller.gui.delegate.view;

import controller.crud.Update;
import controller.handle.InputConverter;
import controller.handle.create.CreateOption;
import controller.handle.delete.DeleteOption;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import view.gui.MediaAlert;

import java.util.Arrays;

public class ActionCRUD {

    public void create(ActionEvent actionEvent, Label updateDisplay) {
        String text = InputConverter.LICENSED_AUDIO_VIDEO_TEXT + "\n" + InputConverter.INTER_VIDEO_TEXT + " \n" + InputConverter.USER_TEXT;
        MediaAlert mediaAlert = new MediaAlert("Create a Media File", text, "Media/Person:");

        if (mediaAlert.getButtonType() == ButtonType.CANCEL) {
            updateDisplay.setText("Canceled create event");
        }

        if (mediaAlert.getButtonType() == ButtonType.OK) {
            CreateOption createOption = new CreateOption();

            String[] value = mediaAlert.getText().split("\\s+");
            String[] tag = value[0].split(":");
            String[] temp = Arrays.copyOfRange(value, 1, value.length);

            String msg = null;

            try {
                msg = createOption.run(temp, tag[0]);
                updateDisplay.setText(msg + " | was been created");
            } catch (NullPointerException e) {
                updateDisplay.setText(e.getMessage());
            }
        }
    }

    public void delete(ActionEvent actionEvent, Label updateDisplay) {

        MediaAlert mediaAlert = new MediaAlert("Create a Media File", "Delete via Addresse or Delete a user \n ", "Adresse/User:");

        if (mediaAlert.getButtonType() == ButtonType.CANCEL) {
            updateDisplay.setText("No element was been delete");
        }

        if (mediaAlert.getButtonType() == ButtonType.OK) {
            DeleteOption deleteOption = new DeleteOption();
            String msg = deleteOption.run(mediaAlert.getText());
            updateDisplay.setText("Element deleted: " + msg);
        }
    }

    /**
     * Source: https://o7planning.org/en/11533/opening-a-new-window-in-javafx
     *
     * @param actionEvent
     */
    public void update(ActionEvent actionEvent, Label updateDisplay) {
        MediaAlert mediaAlert = new MediaAlert("Update a Media File", "Update via Address ", "Address:");

        if (mediaAlert.getButtonType() == ButtonType.CANCEL) {
            updateDisplay.setText("No element was been updated");
        }

        if (mediaAlert.getButtonType() == ButtonType.OK) {
            Update update = new Update();
            update.accessCount(mediaAlert.getText());
            updateDisplay.setText("Update");
            System.out.println(mediaAlert.getText());
            System.out.println("Clicks: " + update.getAccessCount(mediaAlert.getText()));
        }
    }

}
