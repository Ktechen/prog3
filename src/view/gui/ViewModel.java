package view.gui;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import modell.data.storage.Storage;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploader;

public class ViewModel {

    //TODO Property

    private SimpleListProperty<MediaContent> mediaContents;
    private SimpleListProperty<Uploader> uploaders;

    public ViewModel() {
        this.mediaContents = new SimpleListProperty<>();
        this.uploaders = new SimpleListProperty<>();
    }

    private void updateProperties(){

    }
}
