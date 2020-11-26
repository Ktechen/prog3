package modell.data.content.licensed;

import modell.data.content.AudioVideo;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.util.Collection;

public class LicensedAudioVideo extends AudioVideo implements modell.mediaDB.LicensedAudioVideo {

    private final String holder;

    public LicensedAudioVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Uploader person, int sampleRate, String holder) {
        super(width, height, encoding, bitrate, length, tag, person, sampleRate);
        this.holder = holder;
    }

    @Override
    public String getHolder() {
        return holder;
    }

    @Override
    public String toString() {
        return "LicensedAudioVideo{" +
                "width=" + super.getWidth() +
                ", height=" + super.getHeight() +
                ", encoding='" + super.getEncoding() + '\'' +
                ", bitrate=" + super.getBitrate() +
                ", length=" + super.getLength() +
                ", tag=" + super.getTags() +
                ", uploader=" + super.getUploader().getName() +
                ", updateDate=" + super.getUploadDate() +
                ", updateDate=" + this.holder + "}";
    }
}
