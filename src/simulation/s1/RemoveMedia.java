package simulation.s1;

import controller.crud.Delete;
import controller.storage.Storage;
import modell.mediaDB.MediaContent;

import java.util.List;

public class RemoveMedia extends Thread {

    private final Storage storage = Storage.getInstance();

    @Override
    public void run() {
        Delete delete = new Delete();
        boolean check = true;

        while (check) {

            //Thread mit sync e.g per Storage
            synchronized (this.storage) {

                int size = Storage.getInstance().getMedia().size();

                if (size != 0) {

                    int indexOfDeleteElement = (int) (Math.random() * (size - 1));

                    try {
                        List<MediaContent> list = this.storage.getMedia();
                        delete.perAddress(list.get(indexOfDeleteElement).getAddress());
                        System.out.println("Index: " + indexOfDeleteElement + " Deleted by " + this.getName());

                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        check = false;
                    }
                }
            }
        }

    }
}

