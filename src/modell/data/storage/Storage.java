package modell.data.storage;

import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Uploader;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Storage<T extends Uploadable & MediaContent, E extends Uploader> implements Serializable {

    private List<T> media;
    private Set<E> person;
    private HashMap<String, Long> countOfUse;
    private HashMap<String, Boolean> usedTags;

    private BigDecimal maxSize;
    private BigDecimal currentSize;

    public BigDecimal getCurrentSize() {
        return new BigDecimal(String.valueOf(this.currentSize));
    }

    private void setCurrentSize(BigDecimal bigDecimal) {
        this.currentSize = this.currentSize.add(bigDecimal);
    }

    /**
     * Max length of File
     * <p>
     * * Video: (2000height * 2000 weight) / 8 = 500.000 = byte
     * * 500.000 / 1024 = 488,28125 =  Kibibyte
     * * (10 x 488,28125 = 4.882,8125 10 Medias)
     * * <p>
     * * Audio: Bitrate only
     * <p>
     * e.g (2000 width * 2000 height) / 8 = 500.000byte = 488,28125kibibyte = 0,4768... mibibyte max size
     * <p>
     * @return
     */
    public BigDecimal getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(BigDecimal maxSize) {
        this.maxSize = maxSize;
    }

    private Storage() {
        this.media = new LinkedList<>();
        this.person = new HashSet<>();
        this.usedTags = new HashMap<>();
        this.countOfUse = new HashMap<>();
        this.currentSize = new BigDecimal(0);
        this.maxSize = new BigDecimal("5000000");
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
        synchronized (Storage.class) {
            if (null == Storage.instance) {
                Storage.instance = new Storage();
            }
            return Storage.instance;
        }
    }

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

    public synchronized HashSet<E> getPerson() {
        return new HashSet<>(this.person);
    }

    public synchronized void setPerson(HashSet<E> person) {
        this.person = person;
    }

    public HashMap<String, Boolean> getUsedTags() {
        return new HashMap<>(this.usedTags);
    }

    public boolean addMedia(T video) {
        synchronized (this) {

            if (video != null) {
                this.media.add(video);
                this.setCurrentSize(video.getSize());
                this.initCounter(video.getAddress());
                return true;
            }

            return false;
        }
    }

    public synchronized boolean removeAllVideo(Collection<?> o) {
        Collection<MediaContent> mediaContents = (Collection<MediaContent>) o;

        BigDecimal decimal = null;

        for (MediaContent content : mediaContents) {
            decimal = content.getSize();
        }

        //System.out.println("\nCurrent: " + this.getCurrentSize());
        //out.println("Media Value: " + decimal);
        //System.out.println("Max: " + getMaxSize());

       this.currentSize = this.currentSize.subtract(decimal);

        this.media.removeAll(o);
        return true;
    }

    /**
     * @param person
     * @return if true add to list else user is in hashset
     */
    public synchronized boolean addPerson(E person) {
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

    public synchronized boolean removeAllPerson(Collection<?> o) {
        return this.person.removeAll(o);
    }

    public synchronized void setUsedTags(HashMap<String, Boolean> usedTags) {
        this.usedTags = usedTags;
    }

    private synchronized void initCounter(String address) {
        this.countOfUse.put(address, (long) 0);
    }

    /**
     * Save clicks of Addresses
     */
    public synchronized HashMap<String, Long> getCountOfUse() {
        synchronized (this) {
            return new HashMap<>(this.countOfUse);
        }
    }

    public synchronized void setCountOfUse(HashMap<String, Long> map) {
        this.countOfUse = map;
    }

    public synchronized long getAccessCounter(String address) {
        HashMap<String, Long> map = this.getCountOfUse();

        return map.get(address);
    }

    /**
     * Clear a current memory
     */
    public synchronized void clear() {
        this.media = new LinkedList<>();
        this.person = new HashSet<>();
        this.usedTags = new HashMap<>();
        this.countOfUse = new HashMap<>();
        this.currentSize = new BigDecimal(0);
        this.maxSize = new BigDecimal("5000000");
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