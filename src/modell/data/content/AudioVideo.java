package modell.data.content;

import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class AudioVideo extends Audio implements modell.mediaDB.AudioVideo {

    private int width;
    private int height;
    private BigDecimal size;

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
        builder.append("-");
        builder.append(this.height);
        builder.append("-");
        builder.append(this.width);
        builder.append("-");
        builder.append(super.getBitrate());
        builder.append("-");
        builder.append(super.getLength());
        builder.append("-");
        builder.append(super.getSize());
        builder.append("-");
        builder.append(super.getAccessCount());
        builder.append("-");
        builder.append(new Date().toString());

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
