package data.content;

import data.Person;
import mediaDB.Tag;

import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class LicensedAudioAudioVideo extends AudioVideo implements mediaDB.LicensedAudioVideo {

    private final String holder;

    public LicensedAudioAudioVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Person person, int sampleRate, String holder) {
        super(width, height, encoding, bitrate, length, tag, person, sampleRate);
        this.holder = holder;
    }

    @Override
    public String getHolder() {
        return holder;
    }

    @Override
    public String toString() {
        return "LicensedAudioAudioVideo{" +
                super.toString() +
                "holder='" + holder + '\'' +
                '}';
    }
}
