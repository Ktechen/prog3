package simulation;

import crud.Create;
import data.Storage;
import data.StorageAsSingelton;
import data.content.Person;
import mediaDB.Tag;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class InputMedia extends Thread {

    private static Person person = new Person("Peter Random");
    private static Person person1 = new Person("Rammel lopw");

    @Override
    public void run() {
        final Collection<Tag> t = new LinkedList<>();
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);

        Create create = new Create();

        boolean check = true;

        while (check) {
            try {

                //System.out.println("Add" + StorageAsSingelton.getInstance().getMedia().size());

                int width = (int) (Math.random() * 1500);
                int height = (int) (Math.random() * 1500);
                long bitrate = (long) (Math.random() * 10000);
                int time = (int) (Math.random() * 100);

                create.interactiveVideo(width, height, "mix", bitrate, Duration.parse("PT" + time + "m"), t, person, "kp");
                //create.interactiveVideo(width, height, "mix", bitrate, Duration.parse("PT20m"), t, person1, "kp");
                //System.out.println(this.getName() + " ADD " + this.getId());
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
                check = false;
            }
        }
    }

    
}
