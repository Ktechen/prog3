package modell.data.content;

import modell.mediaDB.Tag;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;

public class MediaContent extends Content implements modell.mediaDB.MediaContent {

    private long bitrate;
    private final Duration duration;
    private BigDecimal size = BigDecimal.valueOf(this.bitrate);

    public MediaContent(Collection<Tag> tags, long bitrate, Duration duration) {
        super(tags);
        this.bitrate = bitrate;
        this.duration = duration;
    }

    public MediaContent() {
        super(Collections.singleton(Tag.News));
        this.duration = Duration.ofDays(0);
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

    BigDecimal caluSize(BigDecimal v1, BigDecimal v2) {
        final BigDecimal value = new BigDecimal(8); //byte
        return (v1.multiply(v2).divide(value));
    }

    /*
    private String generator() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append("-");
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
    */


    @Override
    public String toString() {
        return "MediaContent{" +
                "bitrate=" + bitrate +
                ", duration=" + duration +
                ", size=" + size +
                ", address='" + this.getAddress() + '\'' +
                ", tags=" + this.getTags() +
                ", accessCount=" + this.getAccessCount() +
                '}';
    }
}
