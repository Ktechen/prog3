package modell.data.content;

import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

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

    @Override
    public BigDecimal getSize() {
        return this.size;
    }

    @Override
    public String getAddress() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(Storage.TYPE_OF_SOURCE);
        builder.append(this.getBitrate());
        builder.append("-");
        builder.append(UUID.randomUUID());
        builder.append("-");
        builder.append(this.getLength());
        builder.append("-");
        final String tmp = new Date().toString();
        builder.append(tmp.trim());
        builder.append("-");
        builder.append(System.currentTimeMillis());

        return builder.toString();
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
