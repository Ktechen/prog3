package modell.bean;

import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class BeanItemLicensedAudio implements Serializable, BeanItem {
    public String encoding;
    public Uploader uploader;
    public Date date;
    public int samplingRate;
    public Collection<Tag> tags;
    public Duration duration;
    public long bitrate;
    public BigDecimal size;
    public long counter;
    public String address;
    public String holder;
}
