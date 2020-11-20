package simulation.s2;

import modell.data.storage.StorageAsSingelton;
import simulation.InputMedia;
import simulation.RemoveMedia;
import simulation.UpdateMedia;

public class Simulation2 {

    public Simulation2() {
        StorageAsSingelton.getInstance().clear();
        InputMedia inputMedia = new InputMedia();
        RemoveMedia removeMedia = new RemoveMedia();
        UpdateMedia updateMedia = new UpdateMedia();

        inputMedia.start();
        updateMedia.start();
        removeMedia.start();
    }
}
