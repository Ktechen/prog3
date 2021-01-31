package controller.handleInput.create;

import controller.crud.Create;
import controller.event.EventHandler;
import controller.event.events.commands.CommandAddEvents;
import controller.handleInput.InputConverter;

/**
 * Stellt die Beziehung zwischen dem Input und der Verarbeiter der Daten da
 */
final class CreateController {

    private final Create create;
    private final CommandAddEvents commandAddEvents;

    public CreateController() {
        this.create = new Create();

        //TODO EL_EVENTS

        this.commandAddEvents = new CommandAddEvents(new InputConverter(), new EventHandler<>());
    }

    public final void person(String[] value) {
        String lic = new InputConverter().convertedUploader(value);
        this.commandAddEvents.eventUser(lic);
    }

    public final void licensedAudioVideo(String[] value) {
        //Object[] lic = new InputConverter().convertLicensedVideo(value);
        this.commandAddEvents.eventLicenseVideo(value);
    }

    public final void interactiveVideo(String[] value) {
        //Object[] inter = new InputConverter().convertInteractionVideo(value);
        this.commandAddEvents.eventInteractiveVideo(value);
    }

    //TODO ADD ALL MEDIA
}
