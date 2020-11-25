package controller.crud;

import modell.data.storage.Storage;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.*;

import java.util.*;

public class Delete {

    private final Storage storage;

    public Delete() {
        this.storage = StorageAsSingelton.getInstance();
    }

    public Delete(Storage storage) {
        this.storage = storage;
    }

    /**
     * Delete AudioVideo and person
     *
     * @param name = name of user
     * @return boolean
     */
    public boolean perUser(String name) {
        synchronized (this.storage) {

            for (Uploader person : this.storage.getPerson()) {
                if (person.getName().toLowerCase().compareTo(name.toLowerCase()) == 0) {
                    Set<Uploader> uploaders = new HashSet<>();
                    uploaders.add(person);
                    this.storage.removeAllPerson(uploaders);
                }
            }

            return true;
        }
    }

    /**
     * @param address
     * @return true all is correct | false size of list is 0 or list of address is 0
     */
    public boolean perAddress(String address) {

        synchronized (this.storage) {

            int size = this.storage.getMedia().size();
            if (size == 0) {
                return false;
            }


            List<Uploadable> list = new LinkedList<>();
            int indexValue = -1;
            int index = 0;

            List<Video> contents = storage.getMedia();

            for (Video video : contents) {
                if (video.getAddress().compareTo(address) == 0) {
                    list.add(video);
                    indexValue = index;
                }
                index++;
            }

            if (list.size() == 0) {
                return false;

            } else {
                this.storage.removeAllVideo(list);

                //Nur einmal vorhanden
                if (storage.personSize(list.get(0).getUploader().getName()) == 1) {
                    clearPerson(list.get(0).getUploader().getName());
                } else {
                    storage.removePerson(indexValue);
                }

                //Update tags
                changeTags();
            }

            return true;
        }
    }

    private void clearPerson(String name) {
        synchronized (this.storage) {
            LinkedList<Uploader> list = new LinkedList<>();

            for (Uploader person : storage.getPerson()) {
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

            for (MediaContent mediaContent: this.storage.getMedia()) {
                values = mediaContent.getTags();
            }

            if(values != null){
                read.tagFinder(values);
            }
        }
    }
}
