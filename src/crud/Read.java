package crud;

import data.Storage;
import data.content.Person;
import data.StorageAsSingelton;
import mediaDB.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Read {

    private final Storage storage;

    public Read() {
        this.storage = StorageAsSingelton.getInstance();
    }

    public Read(Storage storage) {
        this.storage = storage;
    }

    public HashMap<String, Integer> listAllUsernamePerIndexValue(String name) {

        HashMap<String, Integer> hashMap = new HashMap<>();

        int counter = 0;

        counter += countList(this.storage.getMedia(), name);

        hashMap.put(name, counter);

        return hashMap;
    }

    private int countList(List<? extends Uploadable> v, String name) {

        int counter = 0;

        for (Uploadable video : v) {
            if (video.getUploader().getName().compareTo(name) == 0) {
                counter++;
            }
        }

        return counter;
    }

    public List<Video> fullList() {
        return storage.getMedia();
    }

    public List<Video> getFullListOrFilterbyTyp(String video) {

        if (video == null) {
            return fullList();
        }

        LinkedList<Video> list = new LinkedList<>();

        for (int i = 0; i < storage.getMedia().size(); i++) {
            if (storage.getMedia().get(i).toString().trim().indexOf(video.trim()) == 0) {
                list.add(storage.getMedia().get(i));
            }
        }

        return list;
    }

    public HashMap<String, Boolean> getFindedTags() {
        return new HashMap<>(this.storage.getUsedTags());
    }

    public void tagFinder(Collection<Tag> tags) {

        if (storage.getUsedTags().isEmpty()) {
            setDefaultValuesOfUsedTags();
        }

        Object[] tag = null;
        tag = tags.toArray();

        for (Object o : tag) {
            HashMap<String, Boolean> me = storage.getUsedTags();
            me.replace(o.toString(), true);
            storage.setUsedTags(me);
        }

    }

    public void setDefaultValuesOfUsedTags() {
        for (Tag t : Tag.values()) {
            HashMap<String, Boolean> me = storage.getUsedTags();
            me.put(t.toString(), false);
            storage.setUsedTags(me);
        }
    }

    /**
     * @param name
     * @return
     */
    public boolean isPersonCreated(String name) {
        return storage.getPersonNames().contains(name);
    }

    public Person foundPerson(String name) {

        Person person = null;

        for (int i = 0; i < storage.getPerson().size(); i++) {
            if (name.compareTo(storage.getPerson().get(i).getName()) == 0) {
                person = storage.getPerson().get(i);
            }
        }

        return person;
    }

}
