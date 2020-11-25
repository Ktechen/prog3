package modell.data.content;

import modell.data.storage.Storage;
import modell.mediaDB.Tag;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class MediaContent implements modell.mediaDB.MediaContent, tools {

    private long bitrate;
    private final Duration duration;
    private BigDecimal size = BigDecimal.valueOf(this.bitrate);
    private String address = generateAddress();
    private final Collection<Tag> tags;
    private final long accessCount;

    public MediaContent(long bitrate, Duration duration, Collection<Tag> tags) {
        this.bitrate = bitrate;
        this.duration = duration;
        this.tags = tags;
        this.accessCount = 0;
    }

    @Override
    public long getBitrate() {
        return bitrate;
    }

    @Override
    public Duration getLength() {
        return duration;
    }

    @Override
    public BigDecimal getSize() {
        return this.size;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public Collection<Tag> getTags() {
        return this.tags;
    }

    @Override
    public long getAccessCount() {
        return this.accessCount;
    }

    @Override
    public BigDecimal caluSize(BigDecimal v1, BigDecimal v2) {
        final BigDecimal value = new BigDecimal(8); //byte
        return (v1.multiply(v2).divide(value));
    }

    @Override
    public String generateAddress() {

        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(Storage.TYPE_OF_SOURCE);
        builder.append(bitrate);
        builder.append("-");
        builder.append(size);
        builder.append("-");
        builder.append(accessCount);
        builder.append("-");
        builder.append(duration);
        builder.append("-");
        builder.append(Math.random()*1000000);
        builder.append("-");
        builder.append(new Date().toString());

        return builder.toString();
    }

    @Override
    public String toString() {
        return "MediaContent{" +
                "bitrate=" + bitrate +
                ", duration=" + duration +
                ", size=" + size +
                ", address='" + address + '\'' +
                ", tags=" + tags +
                ", accessCount=" + accessCount +
                '}';
    }
}
