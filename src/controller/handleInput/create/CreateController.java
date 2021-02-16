package controller.handleInput.create;

import controller.crud.Create;
import controller.event.EventHandler;
import controller.event.events.commands.add.CommandAddEventsMedia;
import controller.event.events.commands.add.CommandAddEventsUploader;
import controller.event.events.listener.add.ELAddMediafiles;
import controller.event.events.listener.add.ELAddUploader;
import controller.handleInput.CommandController;
import controller.handleInput.InputConverter;

/**
 * Stellt die Beziehung zwischen dem Input und der Verarbeiter der Daten da
 */
final class CreateController implements CommandController {

    private final Create create;
    private CommandAddEventsMedia commandAddEventsMedia;
    private CommandAddEventsUploader commandAddEventsUploader;

    public CreateController() {
        this.create = new Create();
        this.config();
    }

    @Override
    public void config() {
        EventHandler<ELAddMediafiles> handler = new EventHandler<>();
        ELAddMediafiles elAddMediafiles = new ELAddMediafiles();
        handler.add(elAddMediafiles);

        EventHandler<ELAddUploader> handleUploader = new EventHandler<>();
        ELAddUploader elAddUploader = new ELAddUploader();
        handleUploader.add(elAddUploader);

        this.commandAddEventsMedia = new CommandAddEventsMedia(new InputConverter(), handler);
        this.commandAddEventsUploader = new CommandAddEventsUploader(new InputConverter(), handleUploader);
    }

    final void person(String[] value) {
        String lic = new InputConverter().convertedUploader(value);
        this.commandAddEventsUploader.eventUser(lic);
    }

    final void licensedAudioVideo(String[] value) {
        this.commandAddEventsMedia.eventLicenseAudioVideo(value);
    }

    final void interactiveVideo(String[] value) {
        this.commandAddEventsMedia.eventInteractiveVideo(value);
    }

    final void audio(String[] value) {
        this.commandAddEventsMedia.eventAudio(value);
    }

    final void audioVideo(String[] value) {
        this.commandAddEventsMedia.eventAudioVideo(value);
    }

    final void video(String[] value) {
        this.commandAddEventsMedia.eventVideo(value);
    }

    final void licensedAudio(String[] value) {
        this.commandAddEventsMedia.eventLicenseAudio(value);
    }

    void licensedVideo(String[] value) {
        this.commandAddEventsMedia.eventLicensedVideo(value);
    }
}
