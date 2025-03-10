package modell.data.content;

import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class Audio extends MediaContent implements modell.mediaDB.Audio, Serializable {

    private int samplingRate;
    private String endcoding;
    private Uploader uploader;
    private Date date;
    /**
     * Audio
     *
     * @param bitrate      = long
     * @param duration     = duration
     * @param tags         = Collection<tag>
     * @param samplingRate = int
     * @param encoding    = string
     * @param uploader     = uploader
     * @Paramlength = 6
     */
    public Audio(long bitrate, Duration duration, Collection<Tag> tags, int samplingRate, String encoding, Uploader uploader) {
        super(tags, bitrate, duration);

        this.samplingRate = samplingRate;
        this.endcoding = encoding;
        this.uploader = uploader;
        this.date = new Date();
    }

    public Audio() {
        super(Collections.singleton(Tag.Lifestyle), 0, Duration.ofDays(0));
    }

    @Override
    public int getSamplingRate() {
        return this.samplingRate;
    }

    @Override
    public String getEncoding() {
        return this.endcoding;
    }

    @Override
    public Uploader getUploader() {
        return this.uploader;
    }

    @Override
    public Date getUploadDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return super.toString() + " Audio{" +
                "samplingRate=" + samplingRate +
                ", endcoding='" + endcoding + '\'' +
                ", uploader=" + uploader +
                ", date=" + date +
                '}';
    }

}
