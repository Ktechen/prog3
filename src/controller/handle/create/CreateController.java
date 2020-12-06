package controller.handle.create;

import controller.crud.Create;
import controller.handle.InputConverter;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.util.Collection;

/**
 * Stellt die Beziehung zwischen dem Input und der Verarbeiter der Daten da
 */
final class CreateController {

    private final Create create;

    public CreateController() {
        create = new Create();
    }

    public final void person(String[] value) {
        String lic = new InputConverter().convertedUploader(value);
        create.person(lic);
    }

    public final void licensedAudioVideo(String[] value) {
        Object[] lic = new InputConverter().convertLicensedVideo(value);
        create.licensedAudioVideo((Integer) lic[0], (Integer) lic[1], (String) lic[2], (Long) lic[3], (Duration) lic[4],
                (Collection<Tag>) lic[5], (Uploader) lic[6], (String) lic[7], (Integer) lic[8]);
    }

    public final void interactiveVideo(String[] value) {
        Object[] inter = new InputConverter().convertInteractionVideo(value);
        create.interactiveVideo((Integer) inter[0], (Integer) inter[1], (String) inter[2], (Long) inter[3],
                (Duration) inter[4], (Collection<Tag>) inter[5], (Uploader) inter[6], (String) inter[7]);
    }

    //TODO ADD ALL MEDIA
}
