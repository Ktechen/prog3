package modell.bean;

import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

class BeanItemLicensedVideo implements Serializable, BeanItem {
    public String encoding;
    public Uploader uploader;
    public Date date;
    public Collection<Tag> tags;
    public Duration duration;
    public long bitrate;
    public BigDecimal size;
    public long counter;
    public String address;
    public int width;
    public int height;
    public String holder;
}
