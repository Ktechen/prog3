package cli.commands.options;

import cli.Console;
import cli.commands.CommandMain;
import cli.commands.ICommand;
import crud.Read;
import data.content.Person;
import data.content.InteractionAudioVideo;
import data.content.LicensedAudioAudioVideo;
import event.EventHandler;
import event.EventListener;
import event.events.event.add.EventAddMediaFiles;
import event.events.event.add.EventAddUploader;
import event.events.listener.add.ELAddUploader;
import event.events.listener.add.ELAddMediafiles;
import mediaDB.Tag;
import observer.Observable;
import observer.Observer;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CommandAdd implements ICommand, Observable {

    private final List<Observer> list = new LinkedList<>();

    private EventHandler<EventListener> eventHandler;
    private final Console cs;
    private int capacity;

    final String licVideoText = "LicensedAudioAudioVideo: " +
            "(int width, int height, String encoding, long bitrate, Duration length, " +
            "Collection<Tag> tag, Person person, String holder, int samplingRate)";

    final String interVideoText = "InteractiveVideo: " +
            "(int width, int height, String encoding, long bitrate, Duration length, " +
            "Collection<Tag> tag, Person person, String type)";

    final String userText = "[Produzentenname] fügt einen Produzentein";

    @Override
    public void join(Observer observer) {
        list.add(observer);
    }

    @Override
    public void leave(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void message() {
        for (Observer o : list) {
            o.update();
        }
    }

    public BigDecimal getCapacity() {
        return BigDecimal.valueOf(capacity);
    }

    public void setHandler(EventHandler<event.EventListener> handler) {
        this.eventHandler = handler;
    }

    public CommandAdd() {
        eventHandler = new EventHandler<>();
        cs = new Console();
    }

    /**
     * TODO Sieht gerade nicht so schön aus aber wird noch angepasst
     *
     * @throws IOException
     */
    @Override
    public void run() throws NullPointerException, IOException {

        System.out.println(toString());
        Console console = new Console();
        String value = console.input("--------------------");

        String[] videoArray = splitToArr(value);

        if (videoArray.length == 0) {
            throw new NullPointerException("Input cannot be analyse");
        }

        Object[] convertArray = null;

        final int usedUser = 1;
        final int usedVideo = 8;
        final int usedVideoLic = 9;

        switch (videoArray.length) {
            case usedUser:

                EventAddUploader eventAddUploader = new EventAddUploader(this, videoArray[0]);
                ELAddUploader elAddUploader = new ELAddUploader();
                eventHandler.add(elAddUploader);
                setHandler(eventHandler);
                eventHandler.handle(eventAddUploader);

                break;
            case usedVideo:

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
            case usedVideoLic:

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

        //observer
        this.capacity = Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);
        this.message();

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

    /**
     * Call observer Size and convert input in to array
     *
     * @return
     */
    private String[] splitToArr(String value) {

        String[] arr = null;

        arr = value.split("\\s+");

        return arr;
    }

    @Override
    public String toString() {
        return userText + "\n" +
                interVideoText + "\n" +
                licVideoText;
    }

}
