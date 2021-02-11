package modell.data.content;

import modell.data.content.Person;
import modell.data.content.Video;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class InteractiveVideo extends Video implements modell.mediaDB.InteractiveVideo, Serializable {

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

    public InteractiveVideo(String type) {
        super(0, 0, "dummy", 0, Duration.ofDays(0), Collections.singleton(Tag.Lifestyle), new Person("Dummy"));
        this.type = type;
    }

    public InteractiveVideo() {
        super(0, 0, "dummy", 0, Duration.ofDays(0), Collections.singleton(Tag.Lifestyle), new Person("Dummy"));
        this.type = "dummy";
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

