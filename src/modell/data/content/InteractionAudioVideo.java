package modell.data.content;

import modell.mediaDB.Tag;

import java.time.Duration;
import java.util.Collection;

public class InteractionAudioVideo extends AudioVideo implements modell.mediaDB.InteractiveVideo {

    private final String type;

    public InteractionAudioVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, String type) {
        super(width, height, encoding, bitrate, length, tag);
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "InteractionAudioVideo{" +
                super.toString() +
                " type='" + type + '\'' +
                '}';
    }
}

