package controller.handleInput.delete;

import modell.data.storage.Storage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteOption {

    private DeleteController deleteController;

    public DeleteOption() {
        this.deleteController = new DeleteController();
    }

    public String run(String value) throws NullPointerException {

        if (null == value || value.length() == 0) {
            throw new NullPointerException("value is null or empty");
        }

        Pattern pattern = Pattern.compile(Storage.TYPE_OF_SOURCE);
        Matcher matcher = pattern.matcher(value);

        if (matcher.find()) {
            this.deleteController.deleteViaAddress(value);
            return "Delete via Address";
        } else {
            this.deleteController.deleteViaUser(value);
            return "Delete via User";
        }
    }
}
