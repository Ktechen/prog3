package simulation;

import crud.Update;
import data.Storage;
import data.StorageAsSingelton;

public class UpdateMedia extends Thread {

    @Override
    public void run() {
        boolean check = true;

        while (check) {
            try {
                Storage storage = StorageAsSingelton.getInstance();
                Update update = new Update();

                int index = (int) (Math.random() * (storage.getMedia().size() - 1));
                update.accessCount(storage.getMedia().get(index).getAddress());

                Thread.sleep(0);
            } catch (InterruptedException e) {
                check = false;
            }
        }
    }
}
