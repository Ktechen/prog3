package simulation.s2;

import controller.storage.Storage;
import simulation.s1.InputMedia;
import simulation.s1.RemoveMedia;
import simulation.s1.UpdateMedia;

public class Simulation2 {

    public Simulation2() {
        Storage.getInstance().clear();
        InputMedia inputMedia = new InputMedia();
        RemoveMedia removeMedia = new RemoveMedia();
        UpdateMedia updateMedia = new UpdateMedia();

        inputMedia.start();
        updateMedia.start();
        removeMedia.start();

        //TODO CommandManagementAdd Simulation 2
    }
}
