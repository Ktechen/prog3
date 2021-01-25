package view.gui;

import javafx.beans.property.*;
import modell.data.storage.Storage;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploader;

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
