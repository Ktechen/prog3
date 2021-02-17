package modell.data.content;

import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.util.Collection;

public class LicensedAudio extends Audio implements modell.mediaDB.LicensedAudio {

    private String holder;

    public LicensedAudio(long bitrate, Duration duration, Collection<Tag> tags, int samplingRate, String endcoding, Uploader uploader, String holder) {
        super(bitrate, duration, tags, samplingRate, endcoding, uploader);
        this.holder = holder;
    }

    @Override
    public String getHolder() {
        return this.holder;
    }

    @Override
    public String toString() {
        return "LicensedAudio{" +
                ", encoding='" + this.getEncoding() + '\'' +
                ", bitrate=" + this.getBitrate() +
                ", length=" + this.getLength() +
                ", tag=" + this.getTags() +
                ", uploader=" + this.getUploader().getName() +
                ", updateDate=" + this.getUploadDate() +
                ", holder=" + this.getHolder() +
                ", clicks=" + this.getAccessCount() +
                ", address=" + this.getAddress() +
                "}";
    }
}
