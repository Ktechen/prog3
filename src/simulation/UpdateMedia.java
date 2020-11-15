package simulation;

import crud.Update;
import data.Storage;
import data.StorageAsSingelton;

public class UpdateMedia extends Thread {

    @Override
    public void run() {
        boolean check = true;
        Update update = new Update();
        Storage storage = StorageAsSingelton.getInstance();

        while (check) {
            try {
                if (storage.getMedia().size() != 0) {
                    int index = (int) (Math.random() * storage.getMedia().size()-1);
                    update.accessCount(storage.getMedia().get(index).getAddress());
                    System.out.println(index + " UpdateMedia: " + storage.getMedia().get(index).getAddress());
                }

                Thread.sleep(0);
            } catch (InterruptedException e) {
                check = false;
            }
        }
    }
}
