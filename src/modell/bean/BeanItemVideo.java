package modell.bean;

import modell.mediaDB.Tag;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class BeanItemVideo implements Serializable, BeanItem {
    public String encoding;
    public String uploader;
    public Date date;
    public Collection<Tag> tags;
    public String duration;
    public long bitrate;
    public BigDecimal size;
    public long counter;
    public String address;
    public int width;
    public int height;
}
