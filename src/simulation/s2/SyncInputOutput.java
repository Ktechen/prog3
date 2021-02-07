package simulation.s2;

import controller.crud.Create;
import controller.crud.Delete;
import modell.data.content.Person;
import modell.mediaDB.Tag;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * https://www.baeldung.com/java-wait-notify
 */
public class SyncInputOutput {

    private boolean transfer = true;
    private Create create;
    private Delete delete;

    public synchronized void delete(String address) {
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }
        }
        transfer = false;

        this.delete = new Delete();
        this.delete.perAddress(address);
        notifyAll();
    }

    public synchronized void add() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        transfer = false;
        this.create = new Create();

        Collection<Tag> tagCollection = new ArrayList<>();
        tagCollection.add(Tag.News);
        tagCollection.add(Tag.Lifestyle);

        this.create.interactiveVideo(
                1500, 500,
                "mix", 9323,
                Duration.parse("20m"), tagCollection,
                new Person("Kevin"), "Typ"
        );

        notifyAll();
    }
}
