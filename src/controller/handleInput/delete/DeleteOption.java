package controller.handleInput.delete;

import controller.crud.Delete;
import modell.data.storage.Storage;

public class DeleteOption {

    public String run(String value) {
        Delete delete = new Delete();
        if (value.contains(Storage.TYPE_OF_SOURCE)) {
            boolean check = delete.perAddress(value);
            return "Mediafile Status: " + check;
        } else {
            boolean check = delete.perUser(value);
            return "User Status: " + check;
        }
    }
}
