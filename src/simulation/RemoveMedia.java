package simulation;

import crud.Delete;
import data.StorageAsSingelton;

public class RemoveMedia extends Thread {

    @Override
    public void run() {
        Delete delete = new Delete();

        boolean check = true;

        while (check) {
            try {
                int index = StorageAsSingelton.getInstance().getMedia().size();
                if (StorageAsSingelton.getInstance().getMedia().size() != 0) {
                    index = (int) (Math.random() * (index) - 1);
                    delete.perAddress(StorageAsSingelton.getInstance().getMedia().get(index).getAddress());
                    System.out.println(this.getName() + " Index " + index + " Deleted: " + StorageAsSingelton.getInstance().getMedia().get(index).getAddress());
                }
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
                check = false;
            }


        }
    }
}
