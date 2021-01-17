package controller.event.events.listener.add;

import controller.crud.Create;
import modell.data.content.Person;
import modell.data.content.interaction.InteractiveVideo;
import modell.data.content.licensed.LicensedAudioVideo;
import controller.event.Event;
import controller.event.EventListener;
import modell.mediaDB.Tag;

import java.time.Duration;
import java.util.Collection;

public class ELAddMediafiles implements EventListener {
    @Override
    public void onInputEvent(Event event) {

        Create create = new Create();

        if (event.getType().compareTo(InteractiveVideo.class.getSimpleName()) == 0) {
            create.interactiveVideo(
                    (Integer) event.getArr()[0],
                    (Integer) event.getArr()[1],
                    (String) event.getArr()[2],
                    (Long) event.getArr()[3],
                    (Duration) event.getArr()[4],
                    (Collection<Tag>) event.getArr()[5],
                    (Person) event.getArr()[6],
                    (String) event.getArr()[7]);


        } else if (event.getType().compareTo(LicensedAudioVideo.class.getSimpleName()) == 0) {
            create.licensedAudioVideo(
                    (Integer) event.getArr()[0],
                    (Integer) event.getArr()[1],
                    (String) event.getArr()[2],
                    (Long) event.getArr()[3],
                    (Duration) event.getArr()[4],
                    (Collection<Tag>) event.getArr()[5],
                    (Person) event.getArr()[6],
                    (String) event.getArr()[7],
                    (Integer) event.getArr()[8]);
        }

    }
}
