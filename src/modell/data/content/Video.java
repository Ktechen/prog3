package modell.data.content;

import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class Video extends MediaContent implements modell.mediaDB.Video {

    private int width;
    private int height;
    private String encoding;
    private Date date;
    private Uploader uploader;
    private BigDecimal size;

    /**
     * Video
     *
     * @param width    = int
     * @param height   = int
     * @param encoding = string
     * @param bitrate  = long
     * @param length   = duration
     * @param tags     = Collection<tag>
     * @param uploader = uploader
     * @ParamLength = 7
     */
    public Video(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tags, Uploader uploader) {
        super(tags, bitrate, length);
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
                "width=" + this.width +
                ", height=" + this.height +
                ", encoding='" + this.encoding + '\'' +
                ", date=" + this.date +
                ", uploader=" + this.uploader +
                ", size=" + this.size +
                ", address=" + this.getAddress() +
                '}';
    }
}
