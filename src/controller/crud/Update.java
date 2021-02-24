package controller.crud;

import controller.storage.Storage;
import modell.mediaDB.MediaContent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Update {

    private final Storage storage;

    public Update() {
        this.storage = Storage.getInstance();
    }

    /**
     * Update per Address
     *
     * @param address = address of media file
     * @return
     * @throws IllegalArgumentException
     */
    public boolean accessCount(String address) throws IllegalArgumentException {
        synchronized (this.storage) {
            HashMap<String, Long> map = this.storage.getCountOfUse();

            LinkedList<String> list = this.getListOfAllAddresses();


            boolean addressFound = false;

            for (String add : list) {
                if (add.equals(address)) {
                    addressFound = true;
                }
            }

            if (!addressFound) {
                throw new IllegalArgumentException("Address not found");
            }

            if (!(map.containsKey(address))) {
                map.put(address, (long) 1);
            } else {
                map.replace(address, map.get(address) + 1);
            }

            this.storage.setCountOfUse(map);

            return false;
        }
    }

    /**
     * Get Address click of Media files
     *
     * @param address
     * @return
     */
    public long getAccessCount(String address) {
        return (long) this.storage.getCountOfUse().get(address);
    }

    private LinkedList<String> getListOfAllAddresses() {
        LinkedList<String> list = new LinkedList<>();
        List<MediaContent> mediaContents = this.storage.getMedia();

        for (MediaContent content : mediaContents) {
            list.add(content.getAddress());
        }

        return new LinkedList<>(list);
    }
}
