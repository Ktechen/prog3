package simulation.s1;

import controller.crud.Update;
import modell.data.storage.Storage;
import modell.mediaDB.MediaContent;

import java.util.List;

public class UpdateMedia extends Thread {

    @Override
    public void run() {
        boolean check = true;
        Update update = new Update();
        Storage storage = Storage.getInstance();

        while (check) {
            try {
                if (storage.getMedia().size() != 0) {
                    int index = (int) (Math.random() * storage.getMedia().size()-1);

                    List<MediaContent> list = storage.getMedia();
                    update.accessCount(list.get(index).getAddress());
                    System.out.println(index + " UpdateMedia: " + list.get(index).getAddress());
                }

                Thread.sleep(0);
            } catch (InterruptedException e) {
                check = false;
            }
        }
    }
}
