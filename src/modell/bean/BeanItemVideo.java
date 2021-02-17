package modell.bean;

import modell.mediaDB.Tag;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class BeanItemVideo implements Serializable, BeanItem {
    public String encoding;
    public String uploader;
    public Date date;
    public Collection<Tag> tags;
    public String length;
    public long bitrate;
    public long accessCount;
    public String address;
    public int width;
    public int height;
}
