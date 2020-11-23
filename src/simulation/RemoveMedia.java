package simulation;

import controller.crud.Delete;
import modell.data.storage.Storage;
import modell.data.storage.StorageAsSingelton;

public class RemoveMedia extends Thread {

    private Storage storage = StorageAsSingelton.getInstance();

    @Override
    public void run() {
        Delete delete = new Delete();
        boolean check = true;

        while (check) {

            //Thread mit sync e.g per Storage
            synchronized (this.storage) {

                int size = StorageAsSingelton.getInstance().getMedia().size();

                if (size != 0) {

                    int indexOfDeleteElement = (int) (Math.random() * (size - 1));

                    try {

                        delete.perAddress(StorageAsSingelton.getInstance().getMedia().get(indexOfDeleteElement).getAddress());
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

