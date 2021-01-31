package modell.data.content;

import java.io.Serializable;

public class Person implements modell.mediaDB.Uploader, Serializable {

    static final long serialVersionUID = 123123L;

    private final String name;

    public Person() {
        this.name = "Dummy";
    }

    public Person(String name) {
        if (name == null) {
            throw new NullPointerException("Name is null");
        }

        if (name.isEmpty()) {
            throw new NullPointerException("Name is Empty");
        }

        this.name = name.replaceAll("\\s", "");
    }

    @Override
    public String getName() {
        return this.name;
    }

    //TODO Set für Bean
    //TODO PersistenceDelegate

    @Override
    public String toString() {
        return "Uploader" +
                "name=" + this.name;
    }
}
