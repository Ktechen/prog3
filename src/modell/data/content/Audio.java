package modell.data.content;

import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class Audio extends MediaContent implements modell.mediaDB.Audio {

    private int samplingRate;
    private String endcoding;
    private Uploader uploader;
    private Date date;

    public Audio(long bitrate, Duration duration, Collection<Tag> tags, int samplingRate, String endcoding, Uploader uploader) {
        super(bitrate, duration, tags);

        if(uploader == null){
            throw new NullPointerException("uploader is null");
        }

        this.samplingRate = samplingRate;
        this.endcoding = endcoding;
        this.uploader = uploader;
        this.date = new Date();
    }

    @Override
    public String getAddress() {
        return generateAddress();
    }

    @Override
    public String generateAddress() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append(Storage.TYPE_OF_SOURCE);
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
