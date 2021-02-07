package simulation.s1;

public class Simulation1 {

    public Simulation1() {
        Thread removeMedia = new RemoveMedia();
        Thread inputMedia = new InputMedia();
        inputMedia.start();
        removeMedia.start();
    }
}
