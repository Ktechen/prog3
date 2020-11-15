package crud;

import data.Storage;
import data.content.Person;
import data.StorageAsSingelton;
import data.content.InteractionAudioVideo;
import data.content.LicensedAudioAudioVideo;
import mediaDB.MediaContent;
import mediaDB.Tag;
import mediaDB.Uploadable;

import java.time.Duration;
import java.util.Collection;

public class Create {

    private final Storage storage;
    private final Read read = new Read();

    /**
     * Create a Video or Audio
     * Storage is {@link StorageAsSingelton}
     */
    public Create() {
        this.storage = StorageAsSingelton.getInstance();
    }

    /**
     * Create a Video or Audio
     *
     * @param storage = add storage for you management
     */
    public Create(Storage storage) {
        this.storage = storage;
    }


    public void interactiveVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Person person, String type) throws InterruptedException {
        synchronized (this.storage) {

            this.storage.wait();

            InteractionAudioVideo video = new InteractionAudioVideo(width, height, encoding, bitrate, length, tag, type);

            video.setPerson(person);
            read.tagFinder(video.getTags());
            Validierung.checkSize(video.getSize());

            try {
                this.storage.addPerson(person);
                this.storage.addMedia(video);
            } catch (IllegalAccessException e) {
                e.getStackTrace();
            }

            this.storage.notify();
        }
    }

    public void licensedAudioVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Person person, String holder, int samplingRate) throws InterruptedException {
        synchronized (this.storage) {

            this.storage.wait();

            LicensedAudioAudioVideo video = new LicensedAudioAudioVideo(width, height, encoding, bitrate, length, tag, person, samplingRate, holder);

            video.setPerson(person);
            read.tagFinder(video.getTags());
            Validierung.checkSize(video.getSize());

            try {
                this.storage.addPerson(person);
                this.storage.addMedia(video);
            } catch (IllegalAccessException e) {
                e.getStackTrace();
            }

            this.storage.notify();
        }
    }

    public synchronized void person(String name) {
        try {
            storage.addPerson(new Person(name));
        } catch (IllegalAccessException e) {
            e.getStackTrace();
        }
    }

}
