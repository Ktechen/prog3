package controller.crud;

import modell.data.storage.Storage;
import modell.mediaDB.*;

import java.util.*;

public class Read {

    private final Storage storage;

    public Read() {
        this.storage = Storage.getInstance();
    }

    public Read(Storage storage) {
        this.storage = storage;
    }

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
     *
     * @return
     */
    public  List<MediaContent> fullList() {
        return storage.getMedia();
    }

    /**
     * Get List filter by type
     *
     * @param video
     * @return
     */
    public List<MediaContent> getFullListOrFilterbyTyp(String video) {

        if (video == null) {
            return fullList();
        }

        LinkedList<MediaContent> list = new LinkedList<>();

        for (int i = 0; i < this.storage.getMedia().size(); i++) {
            if (storage.getMedia().get(i).toString().trim().indexOf(video.trim()) == 0) {
                list.add((MediaContent) this.storage.getMedia().get(i));
            }
        }

        return list;
    }

    /**
     * Get used tags
     *
     * @return
     */
    public HashMap<String, Boolean> getFindedTags() {
        return new HashMap<>(this.storage.getUsedTags());
    }

    /**
     * Load used tags
     *
     * @param tags
     */
    public void tagFinder(Collection<Tag> tags) {
        synchronized (this.storage) {
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
     *
     * @param name
     * @return true = found | false = not found
     */
    public boolean isPersonCreated(String name) {
        return this.storage.getPerson().contains(name);
    }

    public Uploader foundPerson(String name) {

        Uploader person = null;
        Set<Uploader> uploaderSet = this.storage.getPerson();

        for (Uploader uploader : uploaderSet) {
            if (name.compareTo(uploader.getName()) == 0) {
                person = uploader;
            }
        }

        return person;
    }

}
