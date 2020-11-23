package controller.crud;

import modell.data.storage.Storage;
import modell.data.content.Person;
import modell.data.storage.StorageAsSingelton;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploadable;
import modell.mediaDB.Uploader;
import modell.mediaDB.Video;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

        LinkedList<Video> list = new LinkedList<>();

        boolean found = false;

        for (int i = 0; i < storage.getMedia().size(); i++) {

            boolean value = (storage.getPerson().get(i).getName().toLowerCase().trim().compareTo(name.toLowerCase().trim()) == 0);

            if (value) {
                list.add(storage.getMedia().get(i));
                found = true;
            }
        }

        if (found) {
            storage.removeAllVideo(list);
            clearNameOfPerson(name);
            clearPerson(name);
        }

        //Update tags
        changeTags();

        return true;
    }

    /**
     * @param address
     * @return true all is correct | false size of list is 0 or list of address is 0
     */
    public boolean perAddress(String address) throws InterruptedException {

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
                    clearNameOfPerson(list.get(0).getUploader().getName());
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

    private synchronized void clearNameOfPerson(String name) {

        for (int i = 0; i < storage.getPersonNames().size(); i++) {
            if (storage.getPersonNames().get(i).contains(name)) {
                storage.removePersonNames(i);
            }
        }

    }

    private void clearPerson(String name) {
        final Lock lock = new ReentrantLock();

        lock.lock();
        LinkedList<Uploader> list = new LinkedList<>();

        for (Uploader person : storage.getPerson()) {
            if (person.getName().compareTo(name) == 0) {
                list.add(person);
            }
        }

        storage.removeAllPerson(list);
        lock.unlock();
    }

    /**
     * Update tags
     */
    private synchronized void changeTags() {

        Read read = new Read();
        read.setDefaultValuesOfUsedTags();
        Collection<Tag> tags = null;

        for (int i = 0; i < this.storage.getMedia().size(); i++) {
            tags = this.storage.getMedia().get(i).getTags();
        }

        read.tagFinder(tags);
    }
}
