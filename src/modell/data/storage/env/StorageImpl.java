package modell.data.storage.env;

import modell.mediaDB.MediaContent;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Uploader;

import java.math.BigDecimal;
import java.util.*;

public class StorageImpl<T extends Uploadable & MediaContent> implements IStorage, IStorageUploader, IStorageMedia<T> {

    private List<T> media;
    private Set<Uploader> person;
    private HashMap<String, Long> countOfUse;
    private HashMap<String, Boolean> usedTags;

    public StorageImpl() {
        this.media = new LinkedList<>();
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

    @Override
    public boolean addMedia(T t) {
        synchronized (this) {

            if (t == null) {
                return false;
            }

            this.media.add(t);

            return true;
        }
    }

    @Override
    public boolean removeMedia(Object o) {
        synchronized (this) {
            if (o == null) {
                return false;
            }

            this.media.remove(o);

            return true;
        }
    }

    @Override
    public boolean removeAllMedia(Collection<T> t) {
        synchronized (this) {

            if (t == null) {
                return false;
            }

            this.media.addAll(t);

            return true;
        }
    }

    @Override
    public synchronized List<T> getMedia() {
        return new LinkedList<>(this.media);
    }

    @Override
    public boolean addPerson(Uploader uploader) {
        synchronized (this) {

            if (uploader == null) {
                return false;
            }

            this.person.remove(uploader);

            return true;
        }
    }

    @Override
    public boolean remove(Uploader uploader) {
        synchronized (this) {
            this.person.remove(uploader);
            return false;
        }
    }

    @Override
    public synchronized Set<Uploader> getPerson() {
        return new HashSet<>(this.person);
    }

    @Override
    public synchronized void clear() {
        this.media = new LinkedList<>();
        this.person = new HashSet<>();
        this.countOfUse = new HashMap<>();
        this.usedTags = new HashMap<>();
    }
}
