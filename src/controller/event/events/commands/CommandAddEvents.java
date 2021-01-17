package controller.event.events.commands;

import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.event.add.EventAddMediaFiles;
import controller.event.events.event.add.EventAddUploader;
import controller.event.events.listener.add.ELAddMediafiles;
import controller.event.events.listener.add.ELAddUploader;
import controller.handleInput.InputConverter;
import modell.data.content.interaction.InteractiveVideo;
import modell.data.content.licensed.LicensedAudioVideo;

import java.time.format.DateTimeParseException;

public class CommandAddEvents {

    private InputConverter converter;
    private EventHandler<EventListener> eventHandler;

    public CommandAddEvents(InputConverter converter, EventHandler<EventListener> eventHandler) {
        this.converter = converter;
        this.eventHandler = eventHandler;
    }

    public void setHandler(EventHandler<controller.event.EventListener> handler) {
        this.eventHandler = handler;
    }

    public void eventInteractiveVideo(String[] arr) throws ArrayIndexOutOfBoundsException, DateTimeParseException, IllegalArgumentException {

        Object[] convertArray = null;

        convertArray = converter.convertInteractionVideo(arr);

        EventAddMediaFiles eventInterVideo = new EventAddMediaFiles(this, convertArray, InteractiveVideo.class.getSimpleName());

        //TODO Config in MainGUI
        ELAddMediafiles interVideo = new ELAddMediafiles();
        eventHandler.add(interVideo);
        setHandler(eventHandler);
        //

        eventHandler.handle(eventInterVideo);
    }

    public void eventLicenseVideo(String[] arr) throws ArrayIndexOutOfBoundsException, DateTimeParseException, IllegalArgumentException {
        Object[] convertArray = null;

        convertArray = converter.convertLicensedVideo(arr);

        EventAddMediaFiles eventLicVideo = new EventAddMediaFiles(this, convertArray, LicensedAudioVideo.class.getSimpleName());

        //TODO Config in MainGUI
        ELAddMediafiles licVideo = new ELAddMediafiles();
        eventHandler.add(licVideo);
        setHandler(eventHandler);
        //

        eventHandler.handle(eventLicVideo);
    }

    public void eventUser(String arr) {
        //TODO serial per TCP or UDP
        EventAddUploader eventAddUploader = new EventAddUploader(this, arr);

        //TODO Config in MainGUI
        ELAddUploader elAddUploader = new ELAddUploader();
        eventHandler.add(elAddUploader); //TODO EventHandler genauer definieren
        setHandler(eventHandler);
        //

        eventHandler.handle(eventAddUploader);
    }

}
