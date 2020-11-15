package simulation;

import crud.Delete;
import data.StorageAsSingelton;

public class RemoveMedia extends Thread {

    @Override
    public void run() {
        Delete delete = new Delete();
        boolean check = true;

        while (check) {
            System.out.println("Remove " + StorageAsSingelton.getInstance().getMedia().size());
            try {

                int indexOfDeleteElement = (int) (Math.random() * (StorageAsSingelton.getInstance().getMedia().size() - 1));

                System.out.println(this.getName() + " Remove " + this.getId() + " size " + indexOfDeleteElement);
                delete.perAddress(StorageAsSingelton.getInstance().getMedia().get(indexOfDeleteElement).getAddress());

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

