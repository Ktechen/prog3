package modell.data.content.interaction;

import modell.data.content.Video;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.util.Collection;

public class InteractiveVideo extends Video implements modell.mediaDB.InteractiveVideo {

    private final String type;

    public InteractiveVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tags, Uploader uploader, String type) {
        super(width, height, encoding, bitrate, length, tags, uploader);
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return
                ", width=" + super.getWidth() +
                        ", height=" + super.getHeight() +
                        ", encoding='" + super.getEncoding() + '\'' +
                        ", bitrate=" + super.getBitrate() +
                        ", length=" + super.getLength() +
                        ", tag=" + super.getTags() +
                        ", uploader=" + super.getUploader().getName() +
                        ", updateDate=" + super.getUploadDate();
    }
}

