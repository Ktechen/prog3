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
import event.events.listener.add.ELMediafiles;
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
     * Sieht gerade nicht so schön aus aber wird noch angepasst
     *
     * @throws IOException
     */
    @Override
    public void run() throws NullPointerException, IOException {
        System.out.println(toString());

        Console console = new Console();
        String value = console.readInput("--------------------");

        boolean check = true;

        //TODO nach length trigger e.g name only 1 |  lic 9
        do {
            final String licVideoText = "LicensedAudioAudioVideo: " +
                    "(int width, int height, String encoding, long bitrate, Duration length, " +
                    "Collection<Tag> tag, Person person, String holder, int samplingRate)";
            final String interVideoText = "InteractiveVideo: " +
                    "(int width, int height, String encoding, long bitrate, Duration length, " +
                    "Collection<Tag> tag, Person person, String type)";
            switch (value) {
                case "1":
                    String name = cs.input("Enter your Name:");
                    EventAddUploader eventAddUploader = new EventAddUploader(this, name);
                    ELAddUploader elAddUploader = new ELAddUploader();
                    eventHandler.add(elAddUploader);
                    setHandler(eventHandler);
                    eventHandler.handle(eventAddUploader);

                    break;
                case "2":
                    //length: 8 //TODO ändern auf Flexibel
                    final int parameterSizeInter = 8;

                    System.out.println(interVideoText);

                    String[] interVideoArray = null;
                    Object[] convertToInterVideo = null;

                    try {
                        interVideoArray = splitToArr(InteractionAudioVideo.class.getSimpleName());
                        convertToInterVideo = convertToArr(interVideoArray);
                    } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }

                    if (null == interVideoArray || convertToInterVideo == null) {
                        throw new NullPointerException("Convert has been failed");
                    }

                    EventAddMediaFiles eventInterVideo = new EventAddMediaFiles(this, convertToInterVideo, InteractionAudioVideo.class.getSimpleName());
                    ELMediafiles interVideo = new ELMediafiles();
                    eventHandler.add(interVideo);
                    setHandler(eventHandler);
                    eventHandler.handle(eventInterVideo);

                    break;
                case "3":
                    //length: 9
                    final int parameterSizeLic = 9; //TODO ändern auf Flexibel

                    System.out.println(licVideoText);

                    Object[] convertToLic = null;
                    String[] licVideoArray = null;

                    try {
                        licVideoArray = splitToArr(LicensedAudioAudioVideo.class.getSimpleName());
                        convertToLic = convertToArrSpecial(licVideoArray);
                    } catch (ArrayIndexOutOfBoundsException | DateTimeParseException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    if (null == licVideoArray || convertToLic == null) {
                        throw new NullPointerException("Convert has been failed");
                    }

                    EventAddMediaFiles eventLicVideo = new EventAddMediaFiles(this, convertToLic, LicensedAudioAudioVideo.class.getSimpleName());
                    ELMediafiles licVideo = new ELMediafiles();
                    eventHandler.add(licVideo);
                    setHandler(eventHandler);
                    eventHandler.handle(eventLicVideo);

                    break;
                case "back":
                    new CommandMain().run();
                    break;
            }

            check = false;
        } while (check);
    }

    /**
     * @param arr
     * @return
     */
    private Object[] convertToArrSpecial(String[] arr) {
        Object[] main = convertToArr(arr);

        try {
            //sampling rate
            main[main.length - 1] = Integer.parseInt(arr[main.length - 1]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return main;
    }

    /**
     * Handle default inputs
     *
     * @param arr
     * @return
     */
    private Object[] convertToArr(String[] arr) {
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
     * @param message
     * @return
     */
    private String[] splitToArr(String message) {
        String interVideo = cs.input("Enter your " + message + ":");

        String[] arr = null;

        arr = interVideo.split("\\s+");
        this.capacity = Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);
        this.message();

        return arr;
    }

    @Override
    public String toString() {
        return "[Produzentenname] fügt einen Produzentein\n" +
                "2. Add Interaction Video\n" +
                "3. Add LicenedAudioVideo\n" +
                "back - back to main";
    }

}
