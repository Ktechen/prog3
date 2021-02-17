package controller.gui.delegate.view;

import controller.handleInput.InputConverter;
import controller.management.CommandManagementAdd;
import controller.management.CommandManagementDelete;
import controller.management.CommandManagementUpdate;
import controller.observer.Observable;
import controller.observer.Observer;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import view.gui.MediaAlert;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ActionCRUD implements Observable {

    private CommandManagementAdd commandManagementAdd;
    private CommandManagementUpdate commandManagementUpdate;
    private CommandManagementDelete commandManagementDelete;
    private List<Observer> list = new LinkedList<>();

    public ActionCRUD() {
        this.commandManagementAdd = new CommandManagementAdd(null, null);
        this.commandManagementAdd.setOffline(true);

        this.commandManagementDelete = new CommandManagementDelete(null, null);
        this.commandManagementDelete.setOffline(true);

        this.commandManagementUpdate = new CommandManagementUpdate(null, null);
        this.commandManagementUpdate.setOffline(true);
    }

    public void create(ActionEvent actionEvent, Label updateDisplay) {
        String text = InputConverter.MEDIA_TEXT;
        MediaAlert mediaAlert = new MediaAlert("Create a Media File", text, "Media/Person:");

        if (mediaAlert.getButtonType() == ButtonType.CANCEL) {
            updateDisplay.setText("Canceled create event");
        }

        if (mediaAlert.getButtonType() == ButtonType.OK) {
            try {
                this.commandManagementAdd.handleArgs(mediaAlert.getText());
                this.message();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(ActionEvent actionEvent, Label updateDisplay) {

        MediaAlert mediaAlert = new MediaAlert("Create a Media File", "Delete via Addresse or Delete a user \n ", "Adresse/User:");

        if (mediaAlert.getButtonType() == ButtonType.CANCEL) {
            updateDisplay.setText("No element was been delete");
        }

        if (mediaAlert.getButtonType() == ButtonType.OK) {
            try {
                this.commandManagementDelete.handleArgs(mediaAlert.getText());
                updateDisplay.setText("Element was been deleted");
                this.message();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            try {
                this.commandManagementUpdate.handleArgs(mediaAlert.getText());
                this.message();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void join(Observer observer) {
        list.add(observer);
    }

    @Override
    public void leave(Observer observer) {
        list.remove(observer);
    }

    //TODO make private
    private void message() {
        for (Observer o : list) {
            o.update();
        }
    }
}
