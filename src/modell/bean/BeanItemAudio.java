package modell.bean;

import modell.mediaDB.Tag;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class BeanItemAudio implements Serializable, BeanItem {
    public String encoding;
    public String uploader;
    public Date date;
    public int samplingRate;
    public Collection<Tag> tags;
    public String length;
    public long bitrate;
    public BigDecimal size;
    public long accessCount;
    public String address;
}
