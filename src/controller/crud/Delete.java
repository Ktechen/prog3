package controller.crud;

import modell.data.storage.Storage;
import modell.mediaDB.*;

import java.util.*;

public class Delete {

    private final Storage storage;

    public Delete() {
        this.storage = Storage.getInstance();
    }

    /**
     * Delete AudioVideo and person
     *
     * @param name = name of user
     * @return boolean
     */
    public boolean perUser(String name) {
        synchronized (this.storage) {

            Set<Uploader> set = this.storage.getPerson();
            for (Uploader person : set) {
                if (person.getName().toLowerCase().compareTo(name.toLowerCase()) == 0) {
                    Set<Uploader> uploaders = new HashSet<>();
                    uploaders.add(person);
                    this.storage.removeAllPerson(uploaders);
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * @param address
     * @return true all is correct | false size of list is 0 or list of address is 0
     */
    public boolean perAddress(String address) {

        synchronized (this.storage) {

            final int size = this.storage.getMedia().size();
            if (size == 0) {
                return false;
            }

            List<MediaContent> list = new LinkedList<>();
            List<MediaContent> contents = storage.getMedia();

            for (MediaContent video : contents) {
                if (video.getAddress().compareTo(address) == 0) {
                    list.add(video);
                    break;
                }
            }

            if (list.size() == 0) {
                return false;

            } else {
                this.storage.removeAllVideo(list);
                //Update tags
                changeTags();
            }

            return true;
        }
    }

    @Deprecated
    private void clearPerson(String name) {
        synchronized (this.storage) {
            Set<Uploader> list = this.storage.getPerson();

            for (Uploader person : list) {
                if (person.getName().compareTo(name) == 0) {
                    list.add(person);
                }
            }

            this.storage.removeAllPerson(list);
        }
    }

    /**
     * Update tags
     */
    private void changeTags() {
        synchronized (this.storage) {
            Read read = new Read();
            read.setDefaultValuesOfUsedTags();
            Collection<Tag> values = null;

            List<MediaContent> mediaContents = this.storage.getMedia();

            for (MediaContent mediaContent : mediaContents) {
                values = mediaContent.getTags();
            }

            if (values != null) {
                read.tagFinder(values);
            }
        }
    }
}
