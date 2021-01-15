package controller.cli.commands.options.events;

import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.event.add.EventAddMediaFiles;
import controller.event.events.event.add.EventAddUploader;
import controller.event.events.listener.add.ELAddMediafiles;
import controller.event.events.listener.add.ELAddUploader;
import controller.handle.InputConverter;
import modell.data.content.interaction.InteractiveVideo;
import modell.data.content.licensed.LicensedAudioVideo;

import java.time.format.DateTimeParseException;

public class CommandEvents {

    private InputConverter converter;
    private EventHandler<EventListener> eventHandler;

    public CommandEvents(InputConverter converter, EventHandler<EventListener> eventHandler) {
        this.converter = converter;
        this.eventHandler = eventHandler;
    }

    public void setHandler(EventHandler<controller.event.EventListener> handler) {
        this.eventHandler = handler;
    }

    public void eventInteractiveVideo(String[] arr) throws InterruptedException {

        Object[] convertArray = null;
        try {
            convertArray = converter.convertInteractionVideo(arr);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        EventAddMediaFiles eventInterVideo = new EventAddMediaFiles(this, convertArray, InteractiveVideo.class.getSimpleName());

        //TODO Config in Main
        ELAddMediafiles interVideo = new ELAddMediafiles();
        eventHandler.add(interVideo);
        setHandler(eventHandler);
        //

        eventHandler.handle(eventInterVideo);
    }

    public void eventLicenseVideo(String[] arr) throws InterruptedException {
        Object[] convertArray = null;
        try {
            convertArray = converter.convertLicensedVideo(arr);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        EventAddMediaFiles eventLicVideo = new EventAddMediaFiles(this, convertArray, LicensedAudioVideo.class.getSimpleName());

        //TODO Config in Main
        ELAddMediafiles licVideo = new ELAddMediafiles();
        eventHandler.add(licVideo);
        setHandler(eventHandler);
        //

        eventHandler.handle(eventLicVideo);
    }

    public void eventUser(String[] arr) throws InterruptedException {
        //TODO serial per TCP or UDP
        EventAddUploader eventAddUploader = new EventAddUploader(this, arr[0]);

        //TODO Config in Main
        ELAddUploader elAddUploader = new ELAddUploader();
        eventHandler.add(elAddUploader); //TODO EventHandler genauer definieren
        setHandler(eventHandler);
        //

        eventHandler.handle(eventAddUploader);
    }


}
