package controller.event.events.listener.add;

import controller.crud.Create;
import modell.data.content.*;
import controller.event.Event;
import controller.event.EventListener;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.util.Collection;

public class ELAddMediafiles implements EventListener {
    @Override
    public void onInputEvent(Event event) {

        final Create create = new Create();

        //Die Daten sind schon hiere type umgewandelt und machen daher kein problem beim Casten
        //TODO Je Parameter ein e.g event.samplingRate

        if (event.getType().compareTo(InteractiveVideo.class.getSimpleName()) == 0) {
            create.interactiveVideo(
                    (Integer) event.getArr()[0],
                    (Integer) event.getArr()[1],
                    (String) event.getArr()[2],
                    (Long) event.getArr()[3],
                    (Duration) event.getArr()[4],
                    (Collection<Tag>) event.getArr()[5],
                    (Person) event.getArr()[6],
                    (String) event.getArr()[7]
            );

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
                    (Integer) event.getArr()[8]
            );

        } else if (event.getType().compareTo(Audio.class.getSimpleName()) == 0) {
            create.audio(
                    (Long) event.getArr()[0],
                    (Duration) event.getArr()[1],
                    (Collection<Tag>) event.getArr()[2],
                    (Integer) event.getArr()[3],
                    (String) event.getArr()[4],
                    (Uploader) event.getArr()[5]
            );

        } else if (event.getType().compareTo(Video.class.getSimpleName()) == 0) {
            create.video(
                    (Integer) event.getArr()[0],
                    (Integer) event.getArr()[1],
                    (String) event.getArr()[2],
                    (Long) event.getArr()[3],
                    (Duration) event.getArr()[4],
                    (Collection<Tag>) event.getArr()[5],
                    (Person) event.getArr()[6]
            );

        } else if (event.getType().compareTo(LicensedAudio.class.getSimpleName()) == 0) {
            create.licensedAudio(
                    (Long) event.getArr()[0],
                    (Duration) event.getArr()[1],
                    (Collection<Tag>) event.getArr()[2],
                    (Integer) event.getArr()[3],
                    (String) event.getArr()[4],
                    (Uploader) event.getArr()[5],
                    (String) event.getArr()[6]
            );

        } else if (event.getType().compareTo(LicensedVideo.class.getSimpleName()) == 0) {
            create.licensedVideo(
                    (Integer) event.getArr()[0],
                    (Integer) event.getArr()[1],
                    (String) event.getArr()[2],
                    (Long) event.getArr()[3],
                    (Duration) event.getArr()[4],
                    (Collection<Tag>) event.getArr()[5],
                    (Person) event.getArr()[6],
                    (String) event.getArr()[7]
            );

        } else if (event.getType().compareTo(AudioVideo.class.getSimpleName()) == 0) {
            create.audioVideo(
                    (Integer) event.getArr()[0],
                    (Integer) event.getArr()[1],
                    (String) event.getArr()[2],
                    (Long) event.getArr()[3],
                    (Duration) event.getArr()[4],
                    (Collection<Tag>) event.getArr()[5],
                    (Person) event.getArr()[6],
                    (Integer) event.getArr()[7]
            );

        }

    }
}
