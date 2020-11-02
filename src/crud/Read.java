package crud;

import data.Storage;
import data.Person;
import data.StorageAsSingelton;
import mediaDB.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

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

        counter += countList(storage.getVideo(), name);

        hashMap.put(name, counter);

        return hashMap;
    }

    private int countList(LinkedList<? extends Video> v, String name) {

        int counter = 0;

        for (Video video : v) {
            if (video.getUploader().getName().compareTo(name) == 0) {
                counter++;
            }
        }

        return counter;
    }

    public LinkedList<Video> fullList() {
        return storage.getVideo();
    }

    public LinkedList<Video> getFullListOrFilterbyTyp(String video) {

        if (video == null) {
            return fullList();
        }

        LinkedList<Video> list = new LinkedList<>();

        for (int i = 0; i < storage.getVideo().size(); i++) {
            if(storage.getVideo().get(i).toString().trim().indexOf(video.trim()) == 0){
                list.add(storage.getVideo().get(i));
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

        Object[] tag = tags.toArray();

        for (Object o : tag) {
            HashMap<String, Boolean> me = storage.getUsedTags();
            me.replace(o.toString(), true);
            storage.setUsedTags(me);
        }
    }

    private void setDefaultValuesOfUsedTags() {
        for (Tag t : Tag.values()) {
            HashMap<String, Boolean> me = storage.getUsedTags();
            me.put(t.toString(), false);
            storage.setUsedTags(me);
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean isPersonCreated(String name){
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
