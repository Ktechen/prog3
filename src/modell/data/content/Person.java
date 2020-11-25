package modell.data.content;

public class Person implements modell.mediaDB.Uploader {

    private final String name;

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

    @Override
    public String toString() {
        return "Uploader" +
                "name=" + this.name;
    }
}
