package controller.event.events.commands.add;

import controller.event.EventHandler;
import controller.event.events.commands.CommandEvent;
import controller.event.events.event.add.EventAddMediaFiles;
import controller.handleInput.InputConverter;
import modell.data.content.*;

import java.time.format.DateTimeParseException;

public class CommandAddEventsMedia extends CommandEvent {

    public CommandAddEventsMedia(InputConverter converter, EventHandler eventHandler) {
        super(converter, eventHandler);
    }

    private void handleEvent(Object[] convertArray, String simpleName) {
        EventAddMediaFiles eventInterVideo = new EventAddMediaFiles(this, convertArray, simpleName);
        eventHandler.handle(eventInterVideo);
    }

    public void eventInteractiveVideo(String[] value) throws ArrayIndexOutOfBoundsException, DateTimeParseException, IllegalArgumentException {
        Object[] convertArray = this.converter.interactionVideo(value);
        this.handleEvent(convertArray, InteractiveVideo.class.getSimpleName());
    }

    public void eventLicenseAudioVideo(String[] value) throws ArrayIndexOutOfBoundsException, DateTimeParseException, IllegalArgumentException {
        Object[] convertArray = this.converter.licensedAudioVideo(value);
        this.handleEvent(convertArray, LicensedAudioVideo.class.getSimpleName());
    }

    public void eventAudio(String[] value) {
        Object[] convertArray = this.converter.audio(value);
        this.handleEvent(convertArray, Audio.class.getSimpleName());
    }

    public void eventAudioVideo(String[] value) {
        Object[] convertArray = this.converter.audioVideo(value);
        this.handleEvent(convertArray, AudioVideo.class.getSimpleName());
    }

    public void eventVideo(String[] value) {
        Object[] convertArray = this.converter.video(value);
        this.handleEvent(convertArray, Video.class.getSimpleName());
    }

    public void eventLicenseAudio(String[] value) {
        Object[] convertArray = this.converter.licenseAudio(value);
        this.handleEvent(convertArray, LicensedAudio.class.getSimpleName());
    }

    public void eventLicensedVideo(String[] value) {
        Object[] convertArray = this.converter.licensedVideo(value);
        this.handleEvent(convertArray, LicensedVideo.class.getSimpleName());
    }
}
