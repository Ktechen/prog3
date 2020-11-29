package modell.data.content.interaction;

import modell.data.content.Video;
import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.util.Collection;

public class InteractiveVideo extends Video implements modell.mediaDB.InteractiveVideo {

    private final String type;

    /**
     * InteractiveVideo
     *
     * @param width    = int
     * @param height   = int
     * @param encoding = string
     * @param bitrate  = long
     * @param length   = duration
     * @param tags     = Collection<tag>
     * @param uploader = uploader
     * @param type     = string
     * @ParamLength = 8
     */
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
        return "InteractiveVideo{ " +
                "width=" + this.getWidth() +
                ", height=" + this.getHeight() +
                ", encoding='" + this.getEncoding() + '\'' +
                ", bitrate=" + this.getBitrate() +
                ", length=" + this.getLength() +
                ", tag=" + this.getTags() +
                ", uploader=" + this.getUploader().getName() +
                ", updateDate=" + this.getUploadDate() +
                ", type=" + this.type +
                ", clicks=" + this.getAccessCount() +
                ", address=" + this.getAddress() + " }";
    }
}

