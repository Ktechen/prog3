package modell.data.content;

import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class Audio extends MediaContent implements modell.mediaDB.Audio {

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
     * @param endcoding    = string
     * @param uploader     = uploader
     * @Paramlength = 6
     */
    public Audio(long bitrate, Duration duration, Collection<Tag> tags, int samplingRate, String endcoding, Uploader uploader) {
        super(tags, bitrate, duration);

        if (uploader == null) {
            throw new NullPointerException("uploader is null");
        }

        this.samplingRate = samplingRate;
        this.endcoding = endcoding;
        this.uploader = uploader;
        this.date = new Date();
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
