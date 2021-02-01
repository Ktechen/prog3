package controller.handleInput.create;

import controller.crud.Create;
import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.commands.add.CommandAddEventsMedia;
import controller.event.events.commands.add.CommandAddEventsUploader;
import controller.event.events.listener.add.ELAddMediafiles;
import controller.event.events.listener.add.ELAddUploader;
import controller.handleInput.InputConverter;

/**
 * Stellt die Beziehung zwischen dem Input und der Verarbeiter der Daten da
 */
final class CreateController {

    private final Create create;
    private CommandAddEventsMedia commandAddEventsMedia;
    private CommandAddEventsUploader commandAddEventsUploader;

    public CreateController() {
        this.create = new Create();
        this.config();
    }

    public final void person(String[] value) {
        String lic = new InputConverter().convertedUploader(value);
        this.commandAddEventsUploader.eventUser(lic);
    }

    public final void licensedAudioVideo(String[] value) {
        //Object[] lic = new InputConverter().convertLicensedVideo(value);
        this.commandAddEventsMedia.eventLicenseVideo(value);
    }

    public final void interactiveVideo(String[] value) {
        //Object[] inter = new InputConverter().convertInteractionVideo(value);
        this.commandAddEventsMedia.eventInteractiveVideo(value);
    }

    private void config(){
        EventHandler<ELAddMediafiles> handler = new EventHandler();
        ELAddMediafiles elAddMediafiles = new ELAddMediafiles();
        handler.add(elAddMediafiles);

        EventHandler<ELAddUploader> handleUploader = new EventHandler<>();
        ELAddUploader elAddUploader = new ELAddUploader();
        handleUploader.add(elAddUploader);

        this.commandAddEventsMedia = new CommandAddEventsMedia(new InputConverter(), handler);
        this.commandAddEventsUploader = new CommandAddEventsUploader(new InputConverter(), handleUploader);
    }

    //TODO ADD ALL MEDIA
}
