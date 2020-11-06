package simulation.s1;

import data.StorageAsSingelton;
import simulation.InputMedia;
import simulation.RemoveMedia;

public class Main {

    public static void main(String[] args) {
        StorageAsSingelton.getInstance().clear();

        Thread removeMedia = new RemoveMedia();
        Thread inputMedia = new InputMedia();
        inputMedia.start();
        removeMedia.start();

    }

}
