package crud;

import data.Storage;
import data.StorageAsSingelton;

import java.util.HashMap;

public class Update {

    private final Storage storage;

    public Update() {
        this.storage = StorageAsSingelton.getInstance();
    }

    public Update(Storage storage) {
        this.storage = storage;
    }

    //TODO muss geändert werden
    public long accessCounter(String address) {

        HashMap<String, Long> map = storage.getCountOfUse();

        if (!storage.getCountOfUse().containsKey(address)) {
            map.put(address,(long) 1);
            storage.setCountOfUse(map);
            return 1;
        }

        long count = map.get(address);
        count++;

        map.replace(address, count);
        storage.setCountOfUse(map);

        return count;
    }

    public boolean run(String address){
        HashMap<String, Long> map = this.storage.getCountOfUse();

        if(!map.containsKey(address)){
            return false;
        }

        map.replace(address, map.get(address) + 1);

        this.storage.setCountOfUse(map);
        
        return true;
    }
}
