package modell.bean;

import modell.mediaDB.Uploader;

import java.io.Serializable;

public class BeanItemPerson implements Serializable, BeanItem {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
