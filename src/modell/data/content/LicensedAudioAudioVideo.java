package modell.data.content;

import modell.mediaDB.Tag;

import java.time.Duration;
import java.util.Collection;

public class LicensedAudioAudioVideo extends AudioVideo implements modell.mediaDB.LicensedAudioVideo {

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
