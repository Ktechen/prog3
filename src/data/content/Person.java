package data.content;

import data.Storage;
import data.StorageAsSingelton;

public class Person implements mediaDB.Uploader {

    private final String name;
    private final Storage storage;

    public Person(String name, Storage storage) {
        this.storage = storage;

        handlePerson(name);

        if (!storage.getPersonNames().contains(name)) {
            this.name = name;
            storage.addPersonNames(name);
        } else {
            throw new IllegalArgumentException("Founded username");
        }
    }

    public Person(String name) {
        this.storage = StorageAsSingelton.getInstance();

        handlePerson(name);

        if (!storage.getPersonNames().contains(name)) {
            this.name = name;
            storage.addPersonNames(name);
        } else {
            throw new IllegalArgumentException("Founded username");
        }
    }

    private void handlePerson(String name){
        if (name == null) {
            throw new NullPointerException("Name is null");
        }

        if (name.isEmpty()) {
            throw new NullPointerException("Name is Empty");
        }
    }

    @Override
    public String getName() {
        return this.name.replaceAll("\\s", "");
    }

    @Override
    public String toString() {
        return "Uploader" +
                "name=" + this.name;
    }
}
