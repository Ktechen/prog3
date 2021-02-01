package modell.data.content;

import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

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
}
