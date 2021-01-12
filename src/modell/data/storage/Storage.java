package modell.data.storage;

import modell.mediaDB.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Storage<T extends Uploadable & MediaContent, E extends Uploader> implements Serializable {

    private List<T> media;
    private Set<E> person;
    private HashMap<String, Long> countOfUse;
    private HashMap<String, Boolean> usedTags;

    private Storage() {
        this.media = new ArrayList<>();
        this.person = new HashSet<>();
        this.usedTags = new HashMap<>();
        this.countOfUse = new HashMap<>();
    }

    private volatile static Storage instance;

    /**
     * Singelton option
     * https://de.wikibooks.org/wiki/Muster:_Java:_Singleton
     * https://stackoverflow.com/questions/1300655/whats-alternative-to-singleton
     *
     * @return memory
     */
    public static Storage getInstance() {
        synchronized(Storage.class){
            if (null == Storage.instance) {
                Storage.instance = new Storage();
            }
            return Storage.instance;
        }
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
    public static final transient String TYPE_OF_SOURCE = "FILE:///";

    public synchronized List<T> getMedia() {
        return new LinkedList<>(this.media);
    }

    public synchronized void setMedia(List<T> media) {
        this.media = media;
    }

    public HashSet<E> getPerson() {
        return new HashSet<>(this.person);
    }

    public synchronized void setPerson(HashSet<E> person) {
        this.person = person;
    }

    public HashMap<String, Boolean> getUsedTags() {
        return new HashMap<>(this.usedTags);
    }

    public synchronized boolean addMedia(T video) {
        this.media.add(video);
        this.initCounter(video.getAddress());
        System.out.println(video.getAddress());
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
    public boolean addPerson(E person) {
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

    private void initCounter(String address) {
        this.countOfUse.put(address, (long) 0);
    }

    /**
     * Save clicks of Addresses
     */
    public HashMap<String, Long> getCountOfUse() {
        synchronized (this) {
            return new HashMap<>(this.countOfUse);
        }
    }


    public void setCountOfUse(HashMap<String, Long> map) {
        this.countOfUse = map;
    }

    public synchronized long getAccessCounter(String address) {
        HashMap<String, Long> map = this.getCountOfUse();

        return map.get(address);
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

    @Override
    public String toString() {
        return "Storage{" +
                "media=" + media +
                ", person=" + person +
                ", countOfUse=" + countOfUse +
                ", usedTags=" + usedTags +
                '}';
    }
}