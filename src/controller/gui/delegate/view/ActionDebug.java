package controller.gui.delegate.view;

import controller.crud.Create;
import controller.crud.Update;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.Tag;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

public class ActionDebug {

    /**
     * public void onClick(MouseEvent mouseEvent) {
     * if (mouseEvent.getClickCount() == 2) {
     * PickResult pickResult = mouseEvent.getPickResult();
     * String value = pickResult.toString();
     * <p>
     * String searchLength = "address=";
     * int start = value.indexOf("address=");
     * int end = value.indexOf("}");
     * String address = value.substring(start + searchLength.length(), end);
     * Update update = new Update();
     * update.accessCount(address);
     * <p>
     * System.out.println(address);
     * System.out.println("Clicks: " + update.getAccessCount(address));
     * }
     * }
     */

    public void clearAll(ActionEvent actionEvent, Storage storage) {
        storage.clear();
    }

    public void setupOne(ActionEvent actionEvent) {
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
