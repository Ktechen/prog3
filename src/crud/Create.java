package crud;

import data.Storage;
import data.content.Person;
import data.StorageAsSingelton;
import data.content.InteractionAudioVideo;
import data.content.LicensedAudioAudioVideo;
import mediaDB.Tag;

import java.time.Duration;
import java.util.Collection;

public class Create {

    private final Storage storage;

    public Create() {
        this.storage = StorageAsSingelton.getInstance();
    }

    public Create(Storage storage) {
        this.storage = storage;
    }

    public void interactiveVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Person person, String type) {
        InteractionAudioVideo video = new InteractionAudioVideo(width, height, encoding, bitrate, length, tag, type);

        video.setPerson(person);
        new Read().tagFinder(video.getTags());
        Validierung.checkSize(video.getSize());

        storage.addPerson(person);
        storage.addVideo(video);
    }

    public void licensedAudioVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Person person, String holder, int samplingRate) {
        LicensedAudioAudioVideo video = new LicensedAudioAudioVideo(width, height, encoding, bitrate, length, tag, person, samplingRate, holder);

        video.setPerson(person);
        new Read().tagFinder(video.getTags());
        try {
            Validierung.checkSize(video.getSize());
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }


        storage.addPerson(person);
        storage.addVideo(video);
    }

    public void person(String name) {
        storage.addPerson(new Person(name));
    }

}
