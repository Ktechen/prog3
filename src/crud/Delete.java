package crud;

import data.Storage;
import data.content.Person;
import data.StorageAsSingelton;
import mediaDB.Tag;
import mediaDB.Video;

import java.util.Collection;
import java.util.LinkedList;

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

        for (int i = 0; i < storage.getVideo().size(); i++) {

            boolean value = (storage.getPerson().get(i).getName().toLowerCase().trim().compareTo(name.toLowerCase().trim()) == 0);

            if (value) {
                list.add(storage.getVideo().get(i));
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
     * Delete AudioVideo
     *
     * @param address
     */
    public boolean perAddress(String address) {

        LinkedList<Video> list = new LinkedList<>();

        int index = -1;

        for (int i = 0; i < storage.getVideo().size(); i++) {
            if (storage.getVideo().get(i).getAddress().compareTo(address) == 0) {
                list.add(storage.getVideo().get(i));
                i = storage.getVideo().size() - 1;
                index = i;
            }
        }

        if (list.size() == 0) {
            return false;
        }

        storage.removeAllVideo(list);

        //Nur einmal vorhanden
        if (storage.personSize(list.getFirst().getUploader().getName()) == 1) {
            clearNameOfPerson(list.getFirst().getUploader().getName());
            clearPerson(list.getFirst().getUploader().getName());
        } else {
            storage.removePerson(index);
        }

        //Update tags
       changeTags();

        return true;
    }

    private void clearNameOfPerson(String name) {

        for (int i = 0; i < storage.getPersonNames().size(); i++) {
            if (storage.getPersonNames().get(i).contains(name)) {
                storage.removePersonNames(i);
            }
        }

    }

    private void clearPerson(String name) {

        LinkedList<Person> list = new LinkedList<>();

        for (int i = 0; i < storage.getPerson().size(); i++) {
            if (storage.getPerson().get(i).getName().compareTo(name) == 0) {
                list.add(storage.getPerson().get(i));
            }
        }

        storage.removeAllPerson(list);

    }

    /**
     * Update tags
     */
    private void changeTags() {

        Read read = new Read();
        read.setDefaultValuesOfUsedTags();
        Collection<Tag> tags = null;

        for (int i = 0; i < this.storage.getVideo().size(); i++) {
            tags = this.storage.getVideo().get(i).getTags();
        }

        read.tagFinder(tags);
    }
}
