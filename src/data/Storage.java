package data;

import data.content.Person;
import mediaDB.Video;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {

    private List<Video> media;
    private LinkedList<String> personNames;
    private LinkedList<Person> person;
    private HashMap<String, Long> countOfUse;
    private HashMap<String, Boolean> usedTags;

    private final Lock lock = new ReentrantLock();

    public Storage() {
        this.media = new LinkedList<>();
        this.personNames = new LinkedList<>();
        this.person = new LinkedList<>();
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
        return new LinkedList<>(this.media);
    }

    public LinkedList<String> getPersonNames() {
        return new LinkedList<>(this.personNames);
    }

    public LinkedList<Person> getPerson() {
        return new LinkedList<>(this.person);
    }

    public HashMap<String, Boolean> getUsedTags() {
        return new HashMap<>(this.usedTags);
    }


    public boolean addMedia(Video video) {
        this.lock.lock();
        this.media.add(video);
        this.lock.unlock();
        return true;
    }

    public boolean removeVideo(int index) {
        this.media.remove(index);
        return true;
    }

    public boolean removeAllVideo(Collection<?> o) {
        this.lock.lock();
        this.media.removeAll(o);
        this.lock.unlock();
        return true;
    }

    public boolean setVideo(int index, Video video) {
        this.media.set(index, video);
        return true;
    }

    public boolean addPersonNames(String personNames) {
        if (personNames != null) {
            personNames.replaceAll("\\s+", "");
            this.personNames.add(personNames);
            return true;
        }

        return false;
    }

    public boolean removePersonNames(int index) {
        this.personNames.remove(index);
        return true;
    }

    public void addPerson(Person person) throws IllegalAccessException {
        if (person != null) {
            this.person.add(person);
        }
    }

    public synchronized int personSize(String name) {

        int counter = 0;

        for (Person person : person) {
            if (person.getName().compareTo(name) == 0) {
                synchronized (this){
                    counter = +1;
                }
            }
        }

        return counter;
    }

    public boolean removePerson(int index) {
        this.person.remove(index);
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
        return countOfUse.get(address);
    }

    public void setCountOfUse(HashMap<String, Long> countOfUse) {
        this.countOfUse = countOfUse;
    }

    /**
     * Clear a current memory
     */
    public void clear() {
        this.media = new LinkedList<>();
        this.personNames = new LinkedList<>();
        this.person = new LinkedList<>();
        this.usedTags = new HashMap<>();
        this.countOfUse = new HashMap<>();
    }

}