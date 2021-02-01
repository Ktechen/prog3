package modell.data.content;

import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.util.Collection;

public class LicensedVideo extends Video implements modell.mediaDB.LicensedVideo {

    private String holder;

    public LicensedVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tags, Uploader uploader, String holder) {
        super(width, height, encoding, bitrate, length, tags, uploader);
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
                ", holder=" + this.holder +
                ", clicks=" + this.getAccessCount() +
                ", address=" + this.getAddress() +
                "}";
    }
}
