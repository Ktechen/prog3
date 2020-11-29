package modell.data.content;

import controller.crud.Update;
import modell.data.storage.Storage;
import modell.mediaDB.Tag;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class MediaContent implements modell.mediaDB.MediaContent {

    private long bitrate;
    private final Duration duration;
    private BigDecimal size = BigDecimal.valueOf(this.bitrate);
    private String address;
    private final Collection<Tag> tags;
    private long accessCount;

    /**
     * MediaContent
     *
     * @param bitrate  = long
     * @param duration = duration
     * @param tags     = Collection<tag>
     * @ParamLength = 3
     */
    public MediaContent(long bitrate, Duration duration, Collection<Tag> tags) {
        this.bitrate = bitrate;
        this.duration = duration;
        this.tags = tags;
        this.accessCount = 0;
        this.address = generator();
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
        return 1;//new Update().getAccessCount(this.getAddress());
    }

    BigDecimal caluSize(BigDecimal v1, BigDecimal v2) {
        final BigDecimal value = new BigDecimal(8); //byte
        return (v1.multiply(v2).divide(value));
    }

    private String generator() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(Storage.TYPE_OF_SOURCE);
        builder.append(this.bitrate);
        builder.append("-");
        builder.append(UUID.randomUUID());
        builder.append("-");
        builder.append(this.duration);
        builder.append("-");
        final String tmp = new Date().toString();
        builder.append(tmp.trim());
        builder.append("-");
        builder.append(System.currentTimeMillis());

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
