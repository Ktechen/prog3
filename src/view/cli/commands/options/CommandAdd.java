package view.cli.commands.options;

import controller.handle.Const;
import controller.handle.InputConverter;
import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.event.add.EventAddMediaFiles;
import controller.event.events.event.add.EventAddUploader;
import controller.event.events.listener.add.ELAddMediafiles;
import controller.event.events.listener.add.ELAddUploader;
import modell.data.content.interaction.InteractiveVideo;
import modell.data.content.licensed.LicensedAudioVideo;
import view.cli.Console;
import view.cli.commands.ICommand;

import java.time.format.DateTimeParseException;

public class CommandAdd implements ICommand {

    private EventHandler<EventListener> eventHandler;
    private final Console cs;
    private InputConverter converter;

    public void setHandler(EventHandler<controller.event.EventListener> handler) {
        this.eventHandler = handler;
    }

    public CommandAdd() {
        eventHandler = new EventHandler<>();
        cs = new Console();
        converter = new InputConverter();
    }

    /**
     * Run a
     * @throws NullPointerException = input was null or empty
     */
    @Override
    public void run() throws NullPointerException, InterruptedException {

        //TODO Change to Updated code versions show controller/handle

        System.out.println(toString());
        Console console = new Console();
        String value = console.input("--------------------");

        //Convert a String to String[]
        String[] videoArray = value.split("\\s+");

        if (videoArray.length == 0) {
            throw new NullPointerException("Input cannot be analyse");
        }

        //Name convert to one string
        if(videoArray.length == 2){
            videoArray[0] = videoArray[0] + videoArray[1];
            videoArray[1] = null;
        }

        Object[] convertArray = null;

        //TODO Vorher erstellen in der Config
        switch (videoArray.length) {
            case 1:
            case 2:

                EventAddUploader eventAddUploader = new EventAddUploader(this, videoArray[0]);

                //TODO Config in Main
                ELAddUploader elAddUploader = new ELAddUploader();
                eventHandler.add(elAddUploader); //TODO EventHandler genauer definieren
                setHandler(eventHandler);
                //

                eventHandler.handle(eventAddUploader);

                break;
            case 8:

                try {
                    convertArray = converter.convertInteractionVideo(videoArray);
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

                break;
            case 9:

                try {
                    convertArray = converter.convertLicensedVideo(videoArray);
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

                break;
        }
    }

    @Override
    public String toString() {
        return Const.USER_TEXT + "\n" +
                Const.INTER_VIDEO_TEXT + "\n" +
                Const.LICENSED_AUDIO_VIDEO_TEXT;
    }
}
