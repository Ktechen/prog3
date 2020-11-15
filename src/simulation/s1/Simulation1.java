package simulation.s1;

import simulation.InputMedia;
import simulation.RemoveMedia;

public class Simulation1 {

    public Simulation1() {
        Thread removeMedia = new RemoveMedia();
        Thread inputMedia = new InputMedia();
        inputMedia.start();
        removeMedia.start();
    }
}
