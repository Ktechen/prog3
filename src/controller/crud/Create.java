package controller.crud;

import modell.data.content.interaction.InteractiveVideo;
import modell.data.content.licensed.LicensedAudioVideo;
import modell.data.storage.Storage;
import modell.data.content.Person;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.Tag;
import controller.observer.Observable;
import controller.observer.Observer;
import modell.mediaDB.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Create implements Observable {

    private final Storage storage;
    private final Read read = new Read();
    private final List<Observer> list = new LinkedList<>();
    private BigDecimal capacity;

    public BigDecimal getCapacity() {
        return capacity;
    }

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


    public void interactiveVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Uploader person, String type)  {
        synchronized (this.storage) {

            this.capacity = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(height));
            //TODO validierung ob 0 or null und wenn nötige zurücksetzen

            InteractiveVideo video = new InteractiveVideo(width, height, encoding, bitrate, length, tag, person, type);

            read.tagFinder(video.getTags());
            Validierung.checkSize(video.getSize());

            this.storage.addPerson(person);
            this.storage.addMedia(video);

            //TODO Anpassen auf Fehlerüberprüfung || e.g listener

            this.message();
        }
    }

    public void licensedAudioVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Uploader person, String holder, int samplingRate) {
        synchronized (this.storage) {

            this.capacity = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(height));

            LicensedAudioVideo video = new LicensedAudioVideo(width, height, encoding, bitrate, length, tag, person, samplingRate, holder);

            read.tagFinder(video.getTags());
            Validierung.checkSize(video.getSize());

            this.storage.addPerson(person);
            this.storage.addMedia(video);

            this.message();
        }
    }

    public void person(String name) {
        synchronized (this.storage) {
            storage.addPerson(new Person(name));
        }
    }

    @Override
    public void join(Observer observer) {
        list.add(observer);
    }

    @Override
    public void leave(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void message() {
        for (Observer o : list) {
            o.update();
        }
    }
}
