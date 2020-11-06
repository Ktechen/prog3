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

    public void run(String address){
        HashMap<String, Long> map = this.storage.getCountOfUse();

        if(!map.containsKey(address)){
            return;
        }

        map.replace(address, map.get(address) + 1);

        this.storage.setCountOfUse(map);
    }
}
