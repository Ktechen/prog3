package controller.crud;

import controller.storage.Storage;
import modell.mediaDB.*;

import java.util.*;

public class Read {

    private final Storage storage;

    public Read() {
        this.storage = Storage.getInstance();
    }

    /**
     * List of user filter by name
     *
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
    public List<String> fullList() {
        return storage.getMedia();
    }

    /**
     * Get List filter by type
     *
     * @param filter
     * @return
     */
    public List<String> getFullListOrFilterbyTyp(String filter) {

        if (filter == null) {
            return fullList();
        }

        LinkedList<MediaContent> list = new LinkedList<>();

        for (int i = 0; i < this.storage.getMedia().size(); i++) {
            if (storage.getMedia().get(i).toString().trim().indexOf(filter.trim()) == 0) {
                list.add((MediaContent) this.storage.getMedia().get(i));
            }
        }

        //prepare for gefiltert nach Typ 1-mit Abrufadresse, Upload-Datumund Anzahl der Abrufe

        LinkedList<String> result = new LinkedList<>();
        for (MediaContent content : list) {
            result.add("Abrufadresse: " + content.getAddress() + " | Anzahl der Abrufe:" + content.getAccessCount() + " | Upload-Datum:" + ((Uploadable) content).getUploadDate());
        }

        return result;
    }

    /**
     * Get used tags
     *
     * @return
     */
    public HashMap<String, Boolean> getFindedTags() {
        return new HashMap<String, Boolean>(this.storage.getUsedTags());
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

    /**
     * Get filter from Tags
     *
     * @param found true = true Tags | false = false Tags
     */
    public LinkedList<String> getTagsByFilter(boolean found) {
        synchronized (this.storage) {
            HashMap<String, Boolean> map = this.getFindedTags();

            LinkedList<String> strings = new LinkedList<>();

            for (String key : map.keySet()) {
                Boolean value = map.get(key);
                if (value.equals(found)) {
                    strings.add(key + ", ");
                }
            }

            return strings;
        }
    }

    public void setDefaultValuesOfUsedTags() {
        for (Tag t : Tag.values()) {
            HashMap<String, Boolean> me = storage.getUsedTags();
            me.put(t.toString(), false);
            storage.setUsedTags(me);
        }
    }

}
