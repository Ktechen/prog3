package modell.data.storage;

import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Uploader;
import modell.mediaDB.Video;

import java.math.BigDecimal;
import java.util.*;

public class Storage {

    private List<Video> media;
    private Set<Uploader> person;
    private HashMap<String, Long> countOfUse;
    private HashMap<String, Boolean> usedTags;

    public Storage() {
        this.media = new ArrayList<>();
        this.person = new HashSet<>();
        this.usedTags = new HashMap<>();
        this.countOfUse = new HashMap<>();
    }

    /**
     * Max length of File
     * <p>
     *
     * <p>
     * e.g (2000 width * 2000 height) / 8 = 500.000byte = 488,28125kibibyte = 0,4768... mibibyte max size
     * <p>
     * Length: 500.000 Byte
     */
    public static final BigDecimal MAX_SIZE_OF_FILE = new BigDecimal(500000);

    /**
     * Gibt denn Linkverweis an
     */
    public static final String TYPE_OF_SOURCE = "FILE:///";

    public synchronized List<Video> getMedia() {
        return new ArrayList<>(this.media);
    }

    public synchronized List<Uploadable> getMediaTypeUploadable() {
        return new ArrayList<>(this.media);
    }

    public HashSet<Uploader> getPerson() {
        return new HashSet<>(this.person);
    }

    public HashMap<String, Boolean> getUsedTags() {
        return new HashMap<>(this.usedTags);
    }

    public boolean addMedia(Video video) {
        this.media.add(video);
        return true;
    }

    public boolean removeMedia(Object o) {
        this.media.remove(o);
        return true;
    }

    public boolean removeAllVideo(Collection<?> o) {
        this.media.removeAll(o);
        return true;
    }

    /**
     * @param person
     * @return if true add to list else user is in hashset
     */
    public boolean addPerson(Uploader person) {
        if (person != null) {

            boolean check = false;

            for (Uploader uploader : this.getPerson()) {
                if (person.getName().contains(uploader.getName())) {
                    check = true;
                }
            }

            if (!check) {
                this.person.add(person);
                return true;
            }
        }

        return false;
    }

    public synchronized int personSize(String name) {

        int counter = 0;

        for (Uploader person : person) {
            if (person.getName().compareTo(name) == 0) {
                synchronized (this) {
                    counter = +1;
                }
            }
        }

        return counter;
    }

    public boolean removePerson(int index) {
        synchronized (this) {
            if (index > person.size()) {
                return false;
            }
            try {
                this.person.remove(index);
            } catch (IndexOutOfBoundsException e) {
                //System.out.println("Index: " + index + "| Size " + getMedia().size());
                e.printStackTrace();
            }

            this.notify();
        }

        return true;
    }

    public synchronized boolean removeAllPerson(Collection<?> o) {
        return this.person.removeAll(o);
    }

    public void setUsedTags(HashMap<String, Boolean> usedTags) {
        this.usedTags = usedTags;
    }

    /**
     * Save clicks of Addresses
     */
    public HashMap<String, Long> getCountOfUse() {
        return new HashMap<>(this.countOfUse);
    }

    public long getAccessCounter(String address) {
        boolean check = this.getCountOfUse().containsKey(address);
        if (!check) {
            throw new NullPointerException("Element no found");
        }
        return this.countOfUse.get(address);
    }

    public void setCountOfUse(HashMap<String, Long> countOfUse) {
        this.countOfUse = countOfUse;
    }

    /**
     * Clear a current memory
     */
    public void clear() {
        this.media = new LinkedList<>();
        this.person = new HashSet<>();
        this.usedTags = new HashMap<>();
        this.countOfUse = new HashMap<>();
    }
}