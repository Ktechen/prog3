package view.cli.commands.options;

import view.cli.Console;
import view.cli.commands.ICommand;
import controller.crud.Read;
import modell.data.content.InteractionAudioVideo;
import modell.data.content.LicensedAudioAudioVideo;
import modell.data.content.Person;
import controller.event.EventHandler;
import controller.event.EventListener;
import controller.event.events.event.add.EventAddMediaFiles;
import controller.event.events.event.add.EventAddUploader;
import controller.event.events.listener.add.ELAddMediafiles;
import controller.event.events.listener.add.ELAddUploader;
import modell.mediaDB.Tag;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;

public class CommandAdd implements ICommand {

    private EventHandler<EventListener> eventHandler;
    private final Console cs;

    final String licVideoText = "LicensedAudioAudioVideo: " +
            "(int width, int height, String encoding, long bitrate, Duration length, " +
            "Collection<Tag> tag, Person person, String holder, int samplingRate)";

    final String interVideoText = "InteractiveVideo: " +
            "(int width, int height, String encoding, long bitrate, Duration length, " +
            "Collection<Tag> tag, Person person, String type)";

    final String userText = "[Produzentenname] fügt einen Produzentein";

    public void setHandler(EventHandler<controller.event.EventListener> handler) {
        this.eventHandler = handler;
    }

    public CommandAdd() {
        eventHandler = new EventHandler<>();
        cs = new Console();
    }

    /**
     * TODO Sieht gerade nicht so schön aus aber wird noch angepasst
     *
     * @throws NullPointerException = input was null or empty
     */
    @Override
    public void run() throws NullPointerException, InterruptedException {

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

        switch (videoArray.length) {
            case 1:
            case 2:

                EventAddUploader eventAddUploader = new EventAddUploader(this, videoArray[0]);
                ELAddUploader elAddUploader = new ELAddUploader();
                eventHandler.add(elAddUploader);
                setHandler(eventHandler);
                eventHandler.handle(eventAddUploader);

                break;
            case 8:

                try {
                    convertArray = convertToArr(videoArray);
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                }

                EventAddMediaFiles eventInterVideo = new EventAddMediaFiles(this, convertArray, InteractionAudioVideo.class.getSimpleName());
                ELAddMediafiles interVideo = new ELAddMediafiles();
                eventHandler.add(interVideo);
                setHandler(eventHandler);
                eventHandler.handle(eventInterVideo);

                break;
            case 9:

                try {
                    convertArray = convertToArrSpecial(videoArray);
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

                EventAddMediaFiles eventLicVideo = new EventAddMediaFiles(this, convertArray, LicensedAudioAudioVideo.class.getSimpleName());
                ELAddMediafiles licVideo = new ELAddMediafiles();
                eventHandler.add(licVideo);
                setHandler(eventHandler);
                eventHandler.handle(eventLicVideo);

                break;
        }
    }

    /**
     * @param arr
     * @return
     */
    private Object[] convertToArrSpecial(String[] arr) {
        Object[] array = convertToArr(arr);

        try {
            //sampling rate
            array[array.length - 1] = Integer.parseInt(arr[array.length - 1]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return array;
    }

    /**
     * Handle default inputs
     *
     * @param arr
     * @return
     */
    private Object[] convertToArr(String[] arr) {

        //Factory
        Object[] array = new Object[arr.length];

        //Integer width & height
        array[0] = Integer.parseInt(arr[0]);
        array[1] = Integer.parseInt(arr[1]);

        //String encoding
        array[2] = arr[2];

        //long bitrate
        array[3] = Long.parseLong(arr[3]);

        //Duration length
        final String add = "PT";
        final int duration = 4;
        if (!(arr[duration].contains(add))) {
            String durationString = arr[duration];
            String element = add + durationString;
            array[duration] = Duration.parse(element);
        } else {
            array[duration] = Duration.parse(arr[duration]);
        }

        //TAGS
        Collection<Tag> collection = new ArrayList<>();
        String[] tagArr = arr[5].split("\\s*,\\s*");

        for (String s : tagArr) {
            Tag.valueOf(s);
        }

        for (String s : tagArr) {
            collection.add(Tag.valueOf(s));
        }

        array[5] = collection;

        //Person
        Read read = new Read();
        Person person = null;
        final int personInt = 6;
        //check person object
        if (read.isPersonCreated(arr[personInt])) {
            person = read.foundPerson(arr[personInt]);
        } else {
            person = new Person(arr[personInt]);
        }

        array[personInt] = person;

        //Type or Holder
        final int typeOrHolder = 7;
        array[typeOrHolder] = arr[typeOrHolder];

        return array;
    }

    @Override
    public String toString() {
        return userText + "\n" +
                interVideoText + "\n" +
                licVideoText;
    }
}
