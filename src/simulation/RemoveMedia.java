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
                int index = StorageAsSingelton.getInstance().getVideo().size();
                if (StorageAsSingelton.getInstance().getVideo().size() != 0) {
                    index = (int) (Math.random() * (index) - 1);
                    delete.perAddress(StorageAsSingelton.getInstance().getVideo().get(index).getAddress());
                    System.out.println("Index " + index + " Deleted: " + StorageAsSingelton.getInstance().getVideo().get(index).getAddress());
                }
                System.out.println("-----------------------------------------------------------------");
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
                check = false;
            }


        }
    }
}
