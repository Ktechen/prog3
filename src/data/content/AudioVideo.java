package data.content;

import crud.Update;
import data.Storage;
import mediaDB.Tag;
import mediaDB.Uploader;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class AudioVideo implements mediaDB.AudioVideo {

    //Add per User
    private final int width;
    private final int height;
    private final String encoding;
    private final long bitrate;
    private final Duration length;
    private final Collection<Tag> tag;
    private final Date updateDate;
    private Person person;
    private int sampleRate = 0;

    public AudioVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag) {
        this.width = width;
        this.height = height;
        this.encoding = encoding;
        this.bitrate = bitrate;
        this.length = length;
        this.tag = tag;
        this.updateDate = new Date();
    }

    public AudioVideo(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Person person, int sampleRate) {
        this.width = width;
        this.height = height;
        this.encoding = encoding;
        this.bitrate = bitrate;
        this.length = length;
        this.tag = tag;
        this.person = person;
        this.sampleRate = sampleRate;
        this.updateDate = new Date();
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    /**
     * calculation of size of file
     *
     * @return bytes of File
     */
    private BigDecimal sizeOfFile() {
        final BigDecimal value = new BigDecimal(8); //byte
        return (BigDecimal.valueOf(height).multiply(BigDecimal.valueOf(width)).divide(value));
    }

    /**
     * Generiert eine Addresse
     *
     * @param add belieben String hinzufügen
     * @return string of link
     */
    private String generateAddress(String add) {

        StringBuilder builder = new StringBuilder();
        builder.append(Storage.TYPE_OF_SOURCE);
        builder.append(bitrate);
        builder.append("-");
        builder.append(add);
        builder.append("-");
        builder.append(encoding);
        builder.append("-");
        builder.append(width);
        builder.append("-");
        builder.append(height);
        builder.append("-");
        builder.append(length);
        builder.append("-");
        builder.append(getUploadDate().toString().replaceAll("\\s+", ""));
        builder.append("-");
        builder.append(person.getName().replaceAll("\\s+", ""));

        return builder.toString();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getSamplingRate() {
        return sampleRate;
    }

    @Override
    public String getEncoding() {
        return encoding;
    }

    @Override
    public long getBitrate() {
        return bitrate;
    }

    @Override
    public Duration getLength() {
        return length;
    }

    @Override
    public BigDecimal getSize() {
        return sizeOfFile();
    }

    @Override
    public String getAddress() {
        return generateAddress(sizeOfFile().toString());
    }

    @Override
    public Collection<Tag> getTags() {
        return tag;
    }

    @Override
    public long getAccessCount() {
        return new Update().storage.getAccessCounter(getAddress());
    }

    @Override
    public Uploader getUploader() {
        return person;
    }

    @Override
    public Date getUploadDate() {
        return updateDate;
    }

    @Override
    public String toString() {

        if(sampleRate != 0){
            return "address=" + getAddress() +
                    ", width=" + width +
                    ", height=" + height +
                    ", encoding='" + encoding + '\'' +
                    ", bitrate=" + bitrate +
                    ", length=" + length +
                    ", tag=" + tag +
                    ", uploader=" + person.getName() +
                    ", updateDate=" + updateDate +
                    ", sampleRate=" + sampleRate;
        }

        return "address=" + getAddress() +
                ", width=" + width +
                ", height=" + height +
                ", encoding='" + encoding + '\'' +
                ", bitrate=" + bitrate +
                ", length=" + length +
                ", tag=" + tag +
                ", uploader=" + person.getName() +
                ", updateDate=" + updateDate;
    }
}
