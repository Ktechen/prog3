package data;

import mediaDB.Video;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class Storage {

    /**
     * Max length of File
     * <p>
     *
     * <p>
     *  e.g (2000 width * 2000 height) / 8 = 500.000byte = 488,28125kibibyte = 0,4768... mibibyte max size
     * <p>
     * Length: 500.000 Byte
     */
    public static final BigDecimal MAX_SIZE_OF_FILE = new BigDecimal(500000);

    /**
     * Gibt denn Linkverweis an
     */
    public static final String TYPE_OF_SOURCE = "FILE:///";

    public Storage() {
        this.video = new LinkedList<>();
        this.personNames = new LinkedList<>();
        this.person = new LinkedList<>();
        this.usedTags = new HashMap<>();
        this.countOfUse = new HashMap<>();
    }

    /**
     * Save InteractiveVideo
     * <p>
     * get Storage from InteractiveVideo
     */
    private LinkedList<Video> video;

    public LinkedList<Video> getVideo() {
        return new LinkedList<>(this.video);
    }

    public boolean addVideo(Video video) {
        if(video != null){
            this.video.add(video);
            return true;
        }

        return false;
    }

    public boolean removeVideo(int index){
        this.video.remove(index);
        return true;
    }

    public boolean removeAllVideo(Collection<?> o){
        return this.video.removeAll(o);
    }

    public boolean setVideo(int index, Video video){
        this.video.set(index, video);
        return true;
    }

    /**
     * List of all Users
     */
    private LinkedList<String> personNames;

    public boolean addPersonNames(String personNames) {
        if(personNames != null){
            this.personNames.add(personNames);
            return true;
        }

        return false;
    }

    public LinkedList<String> getPersonNames() {
        return new LinkedList<>(this.personNames);
    }

    public boolean removePersonNames(int index){
        this.personNames.remove(index);
        return true;
    }
    /**
     * Save Uploader
     */
    private LinkedList<Person> person;

    public LinkedList<Person> getPerson() {
        return new LinkedList<>(this.person);
    }

    public void addPerson(Person person) {
        if(person != null) {
            this.person.add(person);
        }
    }

    public int personSize(String name){

        int counter = 0;

        for (Person person: person) {
            if(person.getName().compareTo(name) == 0){
                counter++;
            }
        }

        return counter;
    }

    public boolean removePerson(int index){
        this.person.remove(index);
        return true;
    }

    public boolean removeAllPerson(Collection<?> o){
        return this.person.removeAll(o);
    }

    /**
     * Save if Tag used
     */
    private HashMap<String, Boolean> usedTags;

    public HashMap<String, Boolean> getUsedTags() {
        return new HashMap<>(this.usedTags);
    }

    public void setUsedTags(HashMap<String, Boolean> usedTags) {
        this.usedTags = usedTags;
    }

    /**
     * Save clicks of Addresses
     */
    private HashMap<String, Long> countOfUse;

    public HashMap<String, Long> getCountOfUse() {
        return new HashMap<>(this.countOfUse);
    }

    public void setCountOfUse(HashMap<String, Long> countOfUse) {
        this.countOfUse = countOfUse;
    }

    public void clear(){
        this.video = new LinkedList<>();
        this.personNames = new LinkedList<>();
        this.person = new LinkedList<>();
        this.usedTags = new HashMap<>();
        this.countOfUse = new HashMap<>();
    }

}
