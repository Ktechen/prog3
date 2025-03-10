package controller.gui;

import javafx.beans.property.*;
import controller.storage.Storage;

public class ViewModel {

    //TODO Property

    private IntegerProperty size = new SimpleIntegerProperty();

    public void setSize(int size) {
        this.size.set(size);
    }

    public int getSize() {
        return size.get();
    }

    public IntegerProperty getSizeProperty() {
        return this.size;
    }

    public ViewModel() {
        this.updateProperties();
    }

    public void updateProperties() {
        this.size.set(Storage.getInstance().getMedia().size());
    }
}
