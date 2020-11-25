package modell.data.content;

import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class Video extends MediaContent implements modell.mediaDB.Video {

    private final int width;
    private final int height;
    private final String encoding;
    private final Date date;
    private final Uploader uploader;
    private final BigDecimal size;

    public Video(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tags, Uploader uploader) {
        super(bitrate, length, tags);
        this.width = width;
        this.height = height;
        this.encoding = encoding;
        this.date = new Date();
        this.uploader = uploader;
        this.size = caluSize(BigDecimal.valueOf(height), BigDecimal.valueOf(height));
    }

    @Override
    public BigDecimal getSize() {
        return this.size;
    }

    @Override
    public BigDecimal caluSize(BigDecimal v1, BigDecimal v2) {
        return super.caluSize(v1, v2);
    }

    @Override
    public String generateAddress() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(Storage.TYPE_OF_SOURCE);
        builder.append(this.height);
        builder.append("-");
        builder.append(this.height);
        builder.append("-");
        builder.append(this.encoding);
        builder.append("-");
        builder.append(getBitrate());
        builder.append("-");
        builder.append(getLength());
        builder.append("-");
        builder.append(getSize());
        builder.append("-");
        builder.append(getLength());
        builder.append("-");
        builder.append(Math.random() * 1000000);
        builder.append("-");
        builder.append(date);

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
    public String getEncoding() {
        return this.encoding;
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
        return "Video{" +
                "width=" + width +
                ", height=" + height +
                ", encoding='" + encoding + '\'' +
                ", date=" + date +
                ", uploader=" + uploader +
                '}';
    }
}
