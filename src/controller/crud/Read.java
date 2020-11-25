package controller.crud;

import modell.data.storage.Storage;
import modell.data.content.Person;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.*;

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

    @Deprecated
    /**
     *
     * List of user filter by name
     * @param name
     * @return hashMap
     */
    public HashMap<String, Integer> listAllUsernamePerIndexValue(String name) {

        HashMap<String, Integer> hashMap = new HashMap<>();

        int counter = 0;

        counter += countList(this.storage.getMedia(), name);

        hashMap.put(name, counter);

        return hashMap;
    }

    @Deprecated
    private int countList(List<? extends Uploadable> v, String name) {

        int counter = 0;

        for (Uploadable video : v) {
            if (video.getUploader().getName().compareTo(name) == 0) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * List of media
     * @return
     */
    public List<Video> fullList() {
        return storage.getMedia();
    }

    /**
     * Get List filter by type
     * @param video
     * @return
     */
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

    /**
     * Get used tags
     * @return
     */
    public HashMap<String, Boolean> getFindedTags() {
        return new HashMap<>(this.storage.getUsedTags());
    }

    /**
     * Load used tags
     * @param tags
     */
    public void tagFinder(Collection<Tag> tags) {
        synchronized (this.storage){
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
    }

    public void setDefaultValuesOfUsedTags() {
        for (Tag t : Tag.values()) {
            HashMap<String, Boolean> me = storage.getUsedTags();
            me.put(t.toString(), false);
            storage.setUsedTags(me);
        }
    }


    /**
     * Is user created
     * @param name
     * @return true = found | false = not found
     */
    public boolean isPersonCreated(String name) {
        return this.storage.getPerson().contains(name);
    }

    public Uploader foundPerson(String name) {

        Uploader person = null;

        for (int i = 0; i < storage.getPerson().size(); i++) {
            if (name.compareTo(storage.getPerson().get(i).getName()) == 0) {
                person = storage.getPerson().get(i);
            }
        }

        return person;
    }

}
