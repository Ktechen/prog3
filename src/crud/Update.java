package crud;

import data.Storage;
import data.StorageAsSingelton;

import java.util.HashMap;

public class Update {

    public final Storage storage;

    public Update() {
        this.storage = StorageAsSingelton.getInstance();
    }

    public Update(Storage storage) {
        this.storage = storage;
    }

    public void accessCount(String address) {
        HashMap<String, Long> map = this.storage.getCountOfUse();

        if (!(map.containsKey(address))) {
            map.put(address, (long) 1);
        } else {
            map.replace(address, map.get(address) + 1);
        }
        this.storage.setCountOfUse(map);

    }
}
