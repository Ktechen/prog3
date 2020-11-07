package simulation.s1;

import data.StorageAsSingelton;
import simulation.InputMedia;
import simulation.RemoveMedia;

public class Simulation1 {

    public Simulation1() {
        StorageAsSingelton.getInstance().clear();
        Thread removeMedia = new RemoveMedia();
        Thread inputMedia = new InputMedia();
        inputMedia.start();
        removeMedia.start();
    }
}
