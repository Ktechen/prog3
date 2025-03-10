package simulation.s1;

import controller.crud.Create;
import controller.storage.Storage;
import modell.data.content.Person;
import modell.mediaDB.Tag;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class InputMedia extends Thread {

    private static Person person = new Person("Peter Random");
    private static Person person1 = new Person("Rammel lopw");

    private Storage storage = Storage.getInstance();

    @Override
    public void run() {
        final Collection<Tag> t = new LinkedList<>();
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Create create = new Create();

        boolean check = true;

        while (check) {
            synchronized (this.storage){
                try {

                    int width = (int) (Math.random() * 1500);
                    int height = (int) (Math.random() * 1500);
                    long bitrate = (long) (Math.random() * 10000);
                    int time = (int) (Math.random() * 100);

                    create.interactiveVideo(width, height, "mix", bitrate, Duration.parse("PT" + time + "m"), t, person, "kp");
                    create.interactiveVideo(width, height, "mix", bitrate, Duration.parse("PT20m"), t, person1, "kp");
                    System.out.println(" ADD " + " length: " + Storage.getInstance().getMedia().size());

                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    check = false;
                }
            }
        }

    }


}
