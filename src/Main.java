import view.cli.commands.CommandMain;
import controller.crud.Create;
import modell.data.content.Person;
import modell.mediaDB.Tag;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        startCli();
    }

    private static void startCli() {
        try {
            load();
            loadMainCli();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadMainCli() throws IllegalMonitorStateException, InterruptedException {
        new CommandMain().run();
    }

    private static void load() throws InterruptedException {
        final Create create = new Create();
        final Collection<Tag> t = new LinkedList<>();
        t.add(Tag.Lifestyle);
        t.add(Tag.Animal);
        final Duration d = Duration.ofSeconds(2000);
        final Person person = new Person("Höchen Flug");

        create.interactiveVideo(100, 400, "vhj", 9174, d, t, new Person("Tim Porsche"), "Tdas");
        create.interactiveVideo(200, 400, "mix", 9174, d, t, new Person("Reiner fall"), "Tdas");
        create.interactiveVideo(300, 400, "de", 9174, d, t, person, "Tdas");

        create.licensedAudioVideo(300, 599, "edcs", 9174, d, t, person, "Tim", 233);
        create.licensedAudioVideo(3221, 400, "gjtzu", 9174, d, t, person, "Tim", 233);
    }
}
