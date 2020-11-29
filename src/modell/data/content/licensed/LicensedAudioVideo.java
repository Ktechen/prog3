package modell.data.content.licensed;

import modell.data.content.AudioVideo;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.util.Collection;

public class LicensedAudioVideo extends AudioVideo implements modell.mediaDB.LicensedAudioVideo {

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
                ", updateDate=" + this.holder +
                ", clicks=" + this.getAccessCount() +
                ", address=" + this.getAddress() + "}";
    }
}
