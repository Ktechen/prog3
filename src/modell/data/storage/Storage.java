package modell.data.storage;

import modell.data.content.Person;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Uploader;
import modell.mediaDB.Video;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Storage {

    private List<Video> media;
    private List<String> personNames;
    private List<Uploader> person;
    private HashMap<String, Long> countOfUse;
    private HashMap<String, Boolean> usedTags;

    public Storage() {
        this.media = new LinkedList<>();
        this.personNames = new LinkedList<>(); //Set unique
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

    public LinkedList<Uploader> getPerson() {
        return new LinkedList<>(this.person);
    }

    public HashMap<String, Boolean> getUsedTags() {
        return new HashMap<>(this.usedTags);
    }

    public boolean addMedia(Video video) {
        this.media.add(video);
        return true;
    }

    public boolean removeVideo(int index) {
        this.media.remove(index);
        return true;
    }

    public boolean removeAllVideo(Collection<?> o) {
        this.media.removeAll(o);
        return true;
    }

    public boolean setVideo(int index, Video video) {
        this.media.set(index, video);
        return true;
    }

    public boolean addPersonNames(String personNames) {
        if (personNames != null) {
            this.personNames.add(personNames);
            return true;
        }

        return false;
    }

    public boolean removePersonNames(int index) {
        this.personNames.remove(index);
        return true;
    }

    public void addPerson(Uploader person) throws IllegalAccessException {
        if (person != null) {
            this.person.add(person);
        }
    }

    public synchronized int personSize(String name) {

        int counter = 0;

        for (Uploader person : person) {
            if (person.getName().compareTo(name) == 0) {
                synchronized (this){
                    counter = +1;
                }
            }
        }

        return counter;
    }

    public boolean removePerson(int index) {
        synchronized (this){
            if (index > person.size()){
                return false;
            }
            try {
                this.person.remove(index);
            }catch (IndexOutOfBoundsException e){
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
        return countOfUse.get(address);
    }

    public void setCountOfUse(HashMap<String, Long> countOfUse) {
        this.countOfUse = countOfUse;
    }

    public String MediaToString(){
        StringBuffer stringBuffer = new StringBuffer();

        for (Uploadable uploadable: getMedia()) {
            stringBuffer.append(uploadable);
        }

        return stringBuffer.toString();
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