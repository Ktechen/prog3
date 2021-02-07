package modell.bean;

import modell.mediaDB.Tag;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class BeanItemInteractiveVideo implements Serializable, BeanItem {
    public String encoding;
    public String uploader;
    public Date date;
    public String type;
    public Collection<Tag> tags;
    public String duration;
    public long bitrate;
    public long counter;
    public String address;
    public int width;
    public int height;
}
