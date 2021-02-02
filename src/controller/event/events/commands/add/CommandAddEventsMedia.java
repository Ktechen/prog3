package controller.event.events.commands.add;

import controller.event.EventHandler;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.add.EventAddMediaFiles;
import controller.event.events.listener.add.ELAddMediafiles;
import controller.handleInput.InputConverter;
import modell.data.content.InteractiveVideo;
import modell.data.content.LicensedAudioVideo;

import java.time.format.DateTimeParseException;

public class CommandAddEventsMedia extends CommandEvent {

    public CommandAddEventsMedia(InputConverter converter, EventHandler eventHandler) {
        super(converter, eventHandler);
    }

    public void eventInteractiveVideo(String[] arr) throws ArrayIndexOutOfBoundsException, DateTimeParseException, IllegalArgumentException {
        Object[] convertArray = converter.convertInteractionVideo(arr);
        EventAddMediaFiles eventInterVideo = new EventAddMediaFiles(this, convertArray, InteractiveVideo.class.getSimpleName());
        eventHandler.handle(eventInterVideo);
    }

    public void eventLicenseVideo(String[] arr) throws ArrayIndexOutOfBoundsException, DateTimeParseException, IllegalArgumentException {
        Object[] convertArray = converter.convertLicensedVideo(arr);
        EventAddMediaFiles eventLicVideo = new EventAddMediaFiles(this, convertArray, LicensedAudioVideo.class.getSimpleName());
        eventHandler.handle(eventLicVideo);
    }
}
