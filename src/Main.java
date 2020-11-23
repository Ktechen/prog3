import controller.crud.Create;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modell.data.content.Person;
import modell.mediaDB.Tag;
import view.cli.commands.CommandMain;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class Main extends Application {

    /**
     * Legt denn Start fest ob mit cli oder gui gestart wird
     */
    private static final int START_SEQ = 1;

    public static void main(String[] args) throws InterruptedException {
        load();
        switch (START_SEQ) {
            case 0:
                startCli();
                break;
            case 1:
                launch(args);
                break;
        }
    }

    private static void startCli() {
        try {
            new CommandMain().run();
        } catch (Exception e) {
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

    @Override
    public void start(Stage stage) throws Exception {
        Parent p = FXMLLoader.load(getClass().getResource("view/gui/Start.fxml"));
        stage.setScene(new Scene(p));
        stage.setTitle("Medienverwalungs - Software by K. Techen");
        stage.setResizable(true);
        stage.setMinHeight(650);
        stage.setMinWidth(900);
        stage.show();
    }

}
