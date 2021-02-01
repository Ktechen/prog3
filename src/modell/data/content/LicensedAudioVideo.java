package modell.data.content;

import modell.data.content.AudioVideo;
import modell.data.content.Person;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;

public class LicensedAudioVideo extends AudioVideo implements modell.mediaDB.LicensedAudioVideo, Serializable {

    private final String holder;

    /**
     * LicensedAudioVideo
     *
     * @param width      = int
     * @param height     = int
     * @param encoding   = encoding
     * @param bitrate    = long
     * @param length     = duration
     * @param tag        = Collection<tag>
     * @param person     = uploader
     * @param sampleRate = int
     * @param holder     = string
     * @ParamLength = 9
     */
    public LicensedAudioVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Uploader person, int sampleRate, String holder) {
        super(width, height, encoding, bitrate, length, tag, person, sampleRate);
        this.holder = holder;
    }

    public LicensedAudioVideo() {
        super(0, 0, "dummy", 0, Duration.ofDays(0), Collections.singleton(Tag.Lifestyle), new Person("Dummy"), 0);
        this.holder = "dummy";
    }

    @Override
    public String getHolder() {
        return holder;
    }

    @Override
    public String toString() {
        return "LicensedAudioVideo{" +
                "width=" + this.getWidth() +
                ", height=" + this.getHeight() +
                ", encoding='" + this.getEncoding() + '\'' +
                ", bitrate=" + this.getBitrate() +
                ", length=" + this.getLength() +
                ", tag=" + this.getTags() +
                ", uploader=" + this.getUploader().getName() +
                ", updateDate=" + this.getUploadDate() +
                ", holder=" + this.holder +
                ", clicks=" + this.getAccessCount() +
                ", address=" + this.getAddress() + "}";
    }
}
