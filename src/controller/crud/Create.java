package controller.crud;

import controller.observer.Observable;
import controller.observer.Observer;
import modell.data.content.*;
import modell.data.storage.Storage;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Create implements Observable {

    private final Storage storage;
    private final Read read = new Read();
    private static List<Observer> list;
    private BigDecimal capacity = BigDecimal.valueOf(0);

    public BigDecimal getCapacity() {
        return capacity;
    }

    public static List<Observer> getList() {
        synchronized (Create.class) {
            return list;
        }
    }


    /**
     * Create a Video or Audio
     * Storage is {@link Storage}
     */
    public Create() {
        this.storage = Storage.getInstance();
        if (null == list) {
            list = new LinkedList<>();
        }
    }

    //TODO Add MEDIAs

    public void interactiveVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Uploader person, String type) {
        synchronized (this.storage) {
            this.capacity = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(height));
            InteractiveVideo video = new InteractiveVideo(width, height, encoding, bitrate, length, tag, person, type);
            this.execute(video);
        }
    }

    public void licensedAudioVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Uploader person, String holder, int samplingRate) {
        synchronized (this.storage) {
            LicensedAudioVideo video = new LicensedAudioVideo(width, height, encoding, bitrate, length, tag, person, samplingRate, holder);
            this.capacity = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(height));
            this.execute(video);
        }
    }

    public void audio(long bitrate, Duration duration, Collection<Tag> tags, int samplingRate, String endcoding, Uploader uploader) {
        synchronized (this.storage) {
            Audio audio = new Audio(bitrate, duration, tags, samplingRate, endcoding, uploader);
            this.capacity = BigDecimal.valueOf(bitrate);
            this.execute(audio);
        }
    }

    public void video(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tags, Uploader uploader) {
        synchronized (this.storage) {
            Video video = new Video(width, height, encoding, bitrate, length, tags, uploader);
            this.capacity = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(height));
            this.execute(video);
        }
    }

    public void audioVideo(int width, int height, String encoding, long bitrate, Duration duration, Collection<Tag> tags, Uploader uploader, int samplingRate) {
        synchronized (this.storage) {
            AudioVideo audioVideo = new AudioVideo(width, height, encoding, bitrate, duration, tags, uploader, samplingRate);
            this.capacity = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(height));
            this.execute(audioVideo);
        }
    }

    public void licensedAudio(long bitrate, Duration duration, Collection<Tag> tags, int samplingRate, String endcoding, Uploader uploader, String holder) {
        synchronized (this.storage) {
            LicensedAudio licensedAudio = new LicensedAudio(bitrate, duration, tags, samplingRate, endcoding, uploader, holder);
            this.capacity = BigDecimal.valueOf(bitrate);
            this.execute(licensedAudio);
        }
    }

    public void licensedVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tags, Uploader uploader, String holder) {
        synchronized (this.storage) {
            LicensedVideo licensedVideo = new LicensedVideo(width, height, encoding, bitrate, length, tags, uploader, holder);
            this.capacity = BigDecimal.valueOf(width).multiply(BigDecimal.valueOf(height));
            this.execute(licensedVideo);
        }
    }

    public void person(String name) {
        synchronized (this.storage) {
            this.storage.addPerson(new Person(name));
        }
    }

    private <T extends Uploadable & MediaContent> void execute(T t) {
        this.infoParameter(t);
        this.person(t.getUploader().getName());
        this.storage.addMedia(t);
        this.executeParameter();
    }

    private <T extends Uploadable & MediaContent> void infoParameter(T t) {
        read.tagFinder(t.getTags());
        Validierung.checkSize(t.getSize());
    }

    private void executeParameter() {
        this.message();
        this.capacity = BigDecimal.valueOf(0);
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
