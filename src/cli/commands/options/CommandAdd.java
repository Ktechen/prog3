package cli.commands.options;

import cli.Console;
import cli.commands.CommandMain;
import cli.commands.ICommand;
import crud.Read;
import data.Person;
import data.content.InteractionAudioVideo;
import data.content.LicensedAudioAudioVideo;
import event.EventHandler;
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
import java.util.*;

public class CommandAdd implements ICommand, Observable {

    private List<Observer> list = new LinkedList<>();

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

    private EventHandler eventHandler;
    private final Console cs;
    private int capacity;

    public BigDecimal getCapacity() {
        return BigDecimal.valueOf(capacity);
    }

    public void setHandler(EventHandler handler) {
        this.eventHandler = handler;
    }

    public CommandAdd() {
        eventHandler = new EventHandler();
        cs = new Console();
    }

    /**
     * Sieht gerade nicht so schön aus aber wird noch angepasst
     *
     * @throws IOException
     */
    @Override
    public void run() throws IOException, IllegalMonitorStateException {
        System.out.println(toString());

        Console console = new Console();
        String value = console.readInput("--------------------");

        boolean check = true;

        //TODO nach length trigger e.g name only 1 |  lic 9
        do {
            switch (value) {
                case "1":
                    String name = cs.input("Enter your Name:");
                    EventAddUploader eventAddUploader = new EventAddUploader(this, name);
                    ELAddUploader elAddUploader = new ELAddUploader();
                    eventHandler.add(elAddUploader);
                    setHandler(eventHandler);

                    if (null != this.eventHandler) {
                        eventHandler.handle(eventAddUploader);
                    }

                    break;
                case "2":
                    //length: 8 //TODO ändern auf Flexibel
                    final int parameterSizeInter = 8;
                    final String interVideoText = "InteractiveVideo: (int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, String type, Person person)";
                    System.out.println(interVideoText);

                    String[] interVideoArray = splitToArr(InteractionAudioVideo.class.getSimpleName());
                    Object[] convertToInterVideo = convertToArr(interVideoArray);

                    if (interVideoArray.length != parameterSizeInter) {
                        throw new IllegalMonitorStateException("Input was incorrect. Try again");
                    }

                    EventAddMediaFiles eventInterVideo = new EventAddMediaFiles(this, convertToInterVideo, InteractionAudioVideo.class.getSimpleName());
                    ELMediafiles interVideo = new ELMediafiles();
                    eventHandler.add(interVideo);
                    setHandler(eventHandler);

                    if (null != this.eventHandler) {
                        eventHandler.handle(eventInterVideo);
                    }

                    break;
                case "3":
                    //length: 9
                    final int parameterSizeLic = 9; //TODO ändern auf Flexibel
                    final String licVideoText = "LicensedAudioAudioVideo: (int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Person person, String holder, int samplingRate)";
                    System.out.println(licVideoText);

                    String[] licVideoArray = splitToArr(LicensedAudioAudioVideo.class.getSimpleName());
                    Object[] convertToLic = convertToArrSpecial(licVideoArray);

                    if (licVideoArray.length != parameterSizeLic) {
                        throw new IllegalMonitorStateException("Input was incorrect. Try again");
                    }

                    EventAddMediaFiles eventLicVideo = new EventAddMediaFiles(this, convertToLic, LicensedAudioAudioVideo.class.getSimpleName());
                    ELMediafiles licVideo = new ELMediafiles();
                    eventHandler.add(licVideo);
                    setHandler(eventHandler);

                    if (null != this.eventHandler) {
                        eventHandler.handle(eventLicVideo);
                    }

                    break;
                case "back":
                    new CommandMain().run();
                    break;
            }

            check = false;
        } while (check);
    }

    /**
     *
     * @param arr
     * @return
     */
    private Object[] convertToArrSpecial(String[] arr){
        Object[] main = convertToArr(arr);

        //sampling rate
        main[main.length-1] = Integer.parseInt(arr[main.length-1]);

        return main;
    }

    /**
     * Handle default inputs
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
        if(!(arr[duration].contains(add))){

        }
        array[duration] = Duration.parse(arr[duration]);

        //TAGS
        Collection<Tag> collection = new ArrayList();
        String[] tagArr = arr[5].split("\\s*,\\s*");

        for (int i = 0; i < tagArr.length; i++) {
            collection.add(Tag.valueOf(tagArr[i]));
        }

        array[5] = collection;

        //Person
        Read read = new Read();
        Person person = null;
        final int personInt = 6;
        //check person object
        if (read.isPersonCreated(array[personInt].toString())) {
            person = read.foundPerson(array[personInt].toString());
        } else {
            person = new Person(array[personInt].toString());
        }

        array[personInt] = person;

        //Type or Holder
        final int typeOrHolder = 7;
        array[typeOrHolder] = arr[typeOrHolder];



        return array;
    }

    private String[] splitToArr(String message) {
        String interVideo = cs.input("Enter your " + message + ":");
        String[] arr = interVideo.split("\\s+");

        this.capacity = Integer.parseInt(arr[0]) * Integer.parseInt(arr[1]);
        this.message();

        return arr;
    }

    @Override
    public String toString() {
        return "1. Add Uploader\n" +
                "2. Add Interaction Video\n" +
                "3. Add LicenedAudioVideo\n" +
                "back - back to main";
    }

}
