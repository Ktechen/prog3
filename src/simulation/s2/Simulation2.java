package simulation.s2;

import data.StorageAsSingelton;
import simulation.InputMedia;
import simulation.UpdateMedia;

public class Simulation2 {

    public Simulation2() {
        StorageAsSingelton.getInstance().clear();
        InputMedia inputMedia = new InputMedia();
        UpdateMedia updateMedia = new UpdateMedia();


        inputMedia.start();
        updateMedia.start();
    }
}
