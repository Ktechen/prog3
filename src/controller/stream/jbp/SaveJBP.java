package controller.stream.jbp;


import modell.data.content.MediaContent;
import modell.data.storage.Storage;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Uploader;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

class SaveJBP {

    private final String filename;

    public SaveJBP(String filename) {

        if (null == filename) {
            throw new NullPointerException("filename is null");
        }

        this.filename = filename;
        this.save();
    }

    /**
     * Source: http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_17_011.htm
     */
    void save() {

        XMLEncoder xmlEncoder = null;

        try {
            xmlEncoder = new XMLEncoder(new FileOutputStream(filename));

            xmlEncoder.writeObject(Storage.getInstance().getPersonAsUploader());
            xmlEncoder.flush();

            xmlEncoder.writeObject(Storage.getInstance().getUsedTags());
            xmlEncoder.flush();

            xmlEncoder.writeObject(Storage.getInstance().getCountOfUse());
            xmlEncoder.flush();

        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            if (xmlEncoder != null) {
                xmlEncoder.close();
            }
        }
    }

    private class ToJBP implements modell.mediaDB.MediaContent, Uploadable {

        private long bitrate;
        private Duration length;
        private BigDecimal size;
        private String address;
        private Collection<Tag> tags;
        private long count;
        private Uploader uploader;
        private Date date;

        public ToJBP() {
        }

        public ToJBP(long bitrate, Duration length, BigDecimal size, String address, Collection<Tag> tags, long count, Uploader uploader, Date date) {
            this.bitrate = bitrate;
            this.length = length;
            this.size = size;
            this.address = address;
            this.tags = tags;
            this.count = count;
            this.uploader = uploader;
            this.date = date;
        }

        @Override
        public long getBitrate() {
            return this.bitrate;
        }

        @Override
        public Duration getLength() {
            return this.length;
        }

        @Override
        public BigDecimal getSize() {
            return this.size;
        }

        @Override
        public String getAddress() {
            return this.address;
        }

        @Override
        public Collection<Tag> getTags() {
            return this.tags;
        }

        @Override
        public long getAccessCount() {
            return this.count;
        }

        @Override
        public Uploader getUploader() {
            return this.uploader;
        }

        @Override
        public Date getUploadDate() {
            return this.date;
        }
    }
}
