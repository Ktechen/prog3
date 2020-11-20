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

            int size = StorageAsSingelton.getInstance().getMedia().size();

            if (size != 0) {

                int indexOfDeleteElement = (int) (Math.random() * (size - 1));

                try {

                    try {
                        delete.perAddress(StorageAsSingelton.getInstance().getMedia().get(indexOfDeleteElement).getAddress());
                        System.out.println("Index: " + indexOfDeleteElement + " Deleted by " + this.getName());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    check = false;
                }
            }
        }

    }
}

