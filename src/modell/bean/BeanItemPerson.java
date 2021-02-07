package modell.bean;

import modell.mediaDB.Uploader;

import java.io.Serializable;

class BeanItemPerson implements Serializable, BeanItem, Uploader {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }
}
