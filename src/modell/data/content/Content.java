package modell.data.content;

import modell.data.storage.Storage;
import modell.mediaDB.Tag;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class Content implements modell.mediaDB.Content, Serializable {

    static final long serialVersionUID = 123123L;

    private String address;
    private Collection<Tag> tags;
    private long counter;


    public Content(Collection<Tag> tags) {
        this.tags = tags;
        this.address = generator();
    }

    private String generator() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append("-");
        builder.append(Storage.TYPE_OF_SOURCE);
        builder.append("-");
        builder.append(UUID.randomUUID());
        builder.append("-");
        final String tmp = new Date().toString();
        builder.append(tmp.trim());
        builder.append("-");
        builder.append(System.currentTimeMillis());

        return builder.toString();
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public Collection<Tag> getTags() {
        return this.tags;
    }

    long getCounter() {
        return counter;
    }

    @Override
    public long getAccessCount() {
        this.counter = (long) Storage.getInstance().getCountOfUse().get(this.address);
        return this.counter;
    }
}
