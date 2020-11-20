import view.cli.commands.CommandMain;
import controller.crud.Create;
import modell.data.content.Person;
import modell.mediaDB.Tag;
import view.gui.StartGUI;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class Main {

    /**
     * Legt denn Start fest ob mit cli oder gui gestart wird
     */
    private static final int START_SEQ = 0;

    public static void main(String[] args) {
        switch (START_SEQ) {
            case 0:
                startCli();
                break;
            case 1:
                StartGUI.main(args);
                break;
        }
    }

    private static void startCli() {
        try {
            load();
            new CommandMain().run();
        }catch (Exception e){
            e.printStackTrace();
        }
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
