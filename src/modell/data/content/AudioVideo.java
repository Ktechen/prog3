package modell.data.content;

import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;

public class AudioVideo extends Audio implements modell.mediaDB.AudioVideo {

    private int width;
    private int height;
    private BigDecimal size;

    /**
     * AudioVideo
     *
     * @param width        = int
     * @param height       = int
     * @param endcoding    = string
     * @param bitrate      = long
     * @param duration     = duration
     * @param tags         = Collection<tag>
     * @param uploader     = uploader
     * @param samplingRate = int
     * @ParamLength = 8
     */
    public AudioVideo(int width, int height, String endcoding, long bitrate, Duration duration, Collection<Tag> tags, Uploader uploader, int samplingRate) {
        super(bitrate, duration, tags, samplingRate, endcoding, uploader);
        this.width = width;
        this.height = height;
        this.size = caluSize(BigDecimal.valueOf(width), BigDecimal.valueOf(height));
    }

    public AudioVideo() {
        super(0, Duration.ofDays(0), Collections.singleton(Tag.News), 0, "Dummy", new Person("Dummy"));
    }

    @Override
    public BigDecimal getSize() {
        return this.size;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        return super.toString() + " AudioVideo{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
