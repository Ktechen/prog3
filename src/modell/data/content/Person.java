package modell.data.content;

import modell.data.storage.Storage;
import modell.data.storage.StorageAsSingelton;

public class Person implements modell.mediaDB.Uploader {

    private final String name;
    private Storage storage = StorageAsSingelton.getInstance();

    public Person(String name, Storage storage) {
        handlePerson(name);

        if (!storage.getPersonNames().contains(name)) {
            this.name = name;
            add(name, storage);
        } else {
            throw new IllegalArgumentException("Founded username");
        }
    }

    public Person(String name) {
        handlePerson(name);

        if (!storage.getPersonNames().contains(name)) {
            this.name = name;
            add(name, storage);
        } else {
            throw new IllegalArgumentException("Founded username");
        }
    }

    private void add(String name, Storage storage){
       storage.addPersonNames(name);
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
