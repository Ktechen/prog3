package controller.handleInput;

import controller.observer.observers.ObserverConsoleSize;
import modell.data.content.*;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.lang.reflect.Constructor;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;

public class InputConverter {

    public final static String NAME = "name: ";
    public final static String LICENSED_AUDIO_VIDEO = "lav: ";
    public final static String LICENSED_AUDIO = "la: ";
    public final static String LICENSED_VIDEO = "lv: ";
    public final static String INTERACTIVE_VIDEO = "iv: ";
    public final static String AUDIO = "a: ";
    public final static String AUDIO_VIDEO = "av: ";
    public final static String VIDEO = "v: ";

    public final static String MEDIA_CONTENT = "mc:";

    public final int AUDIO_LENGTH;
    public final int VIDEO_LENGTH;
    public final int AUDIO_VIDEO_LENGTH;
    public final int LICENSED_AUDIO_LENGTH;
    public final int LICENSED_VIDEO_LENGTH;
    public final int LICENSED_AUDIO_VIDEO_LENGTH;
    public final int INTERACTIVE_VIDEO_LENGTH;

    public static final String SAVE_JOS = "File was save by JOS";
    public static final String LOAD_JOS = "File was loaded by JOS";
    public static final String SAVE_JBP = "File was save by JBP";
    public static final String LOAD_JBP = "File was loaded by JBP";
    public static final String SAVE_RANDOM_ACCESS = "File was save by Random Access";
    public static final String LOAD_RANDOM_ACCESS = "File was Load by Random Access";

    public final static String SHOW_ALL = "1. Showall or filter\n";
    public final static String SHOW_PER_INDEX = "2. User per Index \n";
    public final static String SHOW_TAGS = "3. Show used tags\n";
    public final static String SHOW_CONTENT = "content[[Typ]] Anzeige der Mediadateien-ggf. \n " +
            "gefiltert nach Typ1-mit Abrufadresse, Upload-Datumund Anzahl der Abrufe" + "\n"
            + "Typs: (date, clicks, address)";

    public final static String SHOW_TAG = "tag[enthalten(i)/nicht enthalten(e)]";

    public final static String SHOW_ALL_TEXT_VIEW = "Please enter a filter like: " + InteractiveVideo.class.getSimpleName() + " or No Filter press enter";
    public final static String SHOW_Uploader_TEXT_VIEW = "Please enter your name";

    public final static String DELETE_USER = "[Produzentenname]löscht den Produzenten";
    public final static String DELETE_ADDRESSE = "[Abrufadresse]löscht die Mediadatei";

    private final static String usedTag = "Enter TAG: ";

    public final static String LICENSED_AUDIO_VIDEO_TEXT = usedTag + LICENSED_AUDIO_VIDEO + LicensedAudioVideo.class.getSimpleName() +
            "(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Person person, String holder, int samplingRate)\n";

    public final static String INTER_VIDEO_TEXT = usedTag + INTERACTIVE_VIDEO + " Interactive: " +
            "(int width, int height, String encoding, long bitrate, Duration length, " +
            "Collection<Tag> tag, Person person, String type)\n";

    public final static String AUDIO_TEXT = usedTag + AUDIO + Audio.class.getSimpleName() +
            "(long bitrate, Duration duration, Collection<Tag> tags, int samplingRate, String encoding, Uploader uploader)\n";

    public final static String AUDIO_VIDEO_TEXT = usedTag + AUDIO_VIDEO + AudioVideo.class.getSimpleName() +
            "(int width, int height, String encoding, long bitrate, Duration duration, Collection<Tag> tags, Uploader uploader, int samplingRate)\n";

    public final static String VIDEO_TEXT = usedTag + VIDEO + Video.class.getSimpleName() +
            "(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tags, Uploader uploader)\n";

    public final static String LICENSED_VIDEO_TEXT = usedTag + LICENSED_VIDEO + LicensedVideo.class.getSimpleName() +
            "(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tags, Uploader uploader, String holder)\n";

    public final static String LICENSED_AUDIO_TEXT = usedTag + LICENSED_AUDIO + LicensedAudio.class.getSimpleName() +
            "(long bitrate, Duration duration, Collection<Tag> tags, int samplingRate, String encoding, Uploader uploader, String holder)\n";

    public final static String USER_TEXT = usedTag + NAME + " [Produzentenname] fügt einen Produzentein\n";

    public final static String MEDIA_TEXT = USER_TEXT
            + LICENSED_AUDIO_VIDEO_TEXT
            + INTER_VIDEO_TEXT
            + AUDIO_TEXT
            + AUDIO_VIDEO_TEXT
            + VIDEO_TEXT
            + LICENSED_VIDEO_TEXT
            + LICENSED_AUDIO_TEXT;

    public final static String MAIN_TEXT = "\n" + ":c Wechsel in den Einfügemodus\n" +
            ":d Wechsel inden Löschmodus\n" +
            ":r Wechsel in den Anzeigemodus\n" +
            ":u Wechsel in den Änderungsmodus\n" +
            ":p Wechsel in den Persistenzmodus\n" +
            ":config Wechsel in den Konfigurationsmodus\n" +
            ":back - Show default view\n";

    public final static String CONFIG_ADD_TEXT = "add [Klassenname]";
    public final static String CONFIG_REMOVE_TEXT = "remove [Klassenname]";
    public final static String CONFIG_ADD_TEXT_VIEW = "ObserverConsoleSize was been added";
    public final static String CONFIG_REMOVE_TEXT_VIEW = ObserverConsoleSize.class.getSimpleName() + " was been removed";
    public final static String CONFIG_NOT_FOUND = "Observer not found";

    public final static String UPDATE_TEXT = "[Abrufadresse] erhöht den Zähler für die Abrufe um eins";

    public final static String OPTION_NOT_VALID = "The option is not valid";

    public final static String TCP_SERVER_TEXT = "Der ServerTCP wird mit 2 Argumenten gestartet: Protokoll und Lagerkapazität.";
    public final static String TCP_PROTOCOL_TEXT = "Protocol: [TCP/UDP]";
    public static final String TCP_STORAGE_CAPACITY_TEXT = "Storage capacity : [500 ~ 10 files]";

    public final static String PERSISTENCE_TEXT =
            "saveJOS speichert mittels JOS\n" +
                    "loadJOS lädt mittels JOS" + "\n" +
                    "saveJBP speichert mittels JBP" + "\n" +
                    "loadJBP lädt mittels JBP" + "\n" +
                    "save [Abrufadresse] speichert eine einzelne Instanzineine Datei für alle Instanzen, falls die Datei nicht existiert werden alle Instanzen in eine neue gespeichert" + "\n" +
                    "load [Abrufadresse] lädt eine einzelne Instanz aus der Datei";

    public InputConverter() {
        AUDIO_LENGTH = this.getParameter(Audio.class);
        INTERACTIVE_VIDEO_LENGTH = this.getParameter(InteractiveVideo.class);
        LICENSED_AUDIO_VIDEO_LENGTH = this.getParameter(LicensedAudioVideo.class);
        AUDIO_VIDEO_LENGTH = this.getParameter(AudioVideo.class);
        VIDEO_LENGTH = this.getParameter(Video.class);
        LICENSED_AUDIO_LENGTH = this.getParameter(LicensedAudio.class);
        LICENSED_VIDEO_LENGTH = this.getParameter(LicensedVideo.class);
    }

    private void checkArray(String[] value) {
        if (null == value) {
            throw new NullPointerException("Array is null or empty");
        }

        for (String s : value) {
            if (s == null) {
                throw new IllegalArgumentException("One Parameter is null");
            }
        }
    }

    /**
     * Convert Video to arrays
     *
     * @param value
     * @return
     */
    public Object[] video(String[] value) throws NullPointerException, IllegalArgumentException {

        this.checkArray(value);

        Object[] o = new Object[value.length];

        try {
            o[0] = this.intConverter(value, 0);// Width
            o[1] = this.intConverter(value, 1); // heigth
            o[2] = value[2]; // encoding
            o[3] = this.longConverter(value, 3); // bitrate
            o[4] = this.durationConverter(value, 4); // duraction
            o[5] = this.tagCollectionConverter(value, 5); // tags
            o[6] = this.uploaderConverter(value, 6); // Person
        } catch (NumberFormatException | DateTimeParseException e) {
            e.printStackTrace();
        }

        return o;
    }

    /**
     * Convert audio to arrays
     *
     * @param value
     * @return
     */
    public Object[] audio(String[] value) throws NullPointerException, IllegalArgumentException {

        this.checkArray(value);

        Object[] o = new Object[value.length];

        try {
            o[0] = this.longConverter(value, 0); // bitrate
            o[1] = this.durationConverter(value, 1); // duration
            o[2] = this.tagCollectionConverter(value, 2); // tags
            o[3] = this.intConverter(value, 3); // samplingRate
            o[4] = value[4]; // endcoding
            o[5] = this.uploaderConverter(value, 5); // uploader
        } catch (NumberFormatException | DateTimeParseException e) {
            e.printStackTrace();
        }

        return o;
    }

    public Object[] audioVideo(String[] value) {
        Object[] video = this.video(value);
        video[7] = this.intConverter(value, 7);
        return video;
    }

    public Object[] licenseAudio(String[] value) {
        Object[] audio = this.audio(value);
        audio[6] = value[6];
        return audio;
    }

    public Object[] licensedVideo(String[] value) {
        Object[] video = this.video(value);
        video[7] = value[7];
        return video;
    }

    public Object[] interactionVideo(String[] value) {
        Object[] video = this.video(value);
        video[7] = value[7];
        return video;
    }

    /**
     * Convert e.g Interactive
     * Split your Array before you start (value.split("\\s+");)
     *
     * @param value
     * @return length = 9 and arr with all option from Lic Video
     */
    public Object[] licensedAudioVideo(String[] value) throws IllegalArgumentException {

        if (null == value) {
            throw new NullPointerException("Array is null or empty");
        }

        if (value[value.length - 1] == null) {
            throw new IllegalArgumentException("Last Parameter of LicensedVideo is null");
        }

        Object[] array = this.interactionVideo(value);

        try {
            //sampling rate
            array[array.length - 1] = Integer.parseInt(value[array.length - 1]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return array;
    }

    /**
     * Return a String from arr[]
     *
     * @param arr
     * @return uploader as string
     */
    public String convertedUploader(String[] arr) {

        if (null == arr) {
            throw new NullPointerException("Array is null or empty");
        }

        String value = "";

        for (String var : arr) {
            value += var;
        }

        return value;
    }

    private int intConverter(String[] value, int index) throws NumberFormatException {
        return Integer.parseInt(value[index]);
    }

    private long longConverter(String[] value, int index) {
        return Long.parseLong(value[index]);
    }

    private Duration durationConverter(String[] value, int index) {

        if (!value[index].contains("PT")) {
            value[index] = "PT" + value[index];
        }

        return Duration.parse(value[index]);
    }

    private Collection<Tag> tagCollectionConverter(String[] value, int index) {
        Collection<Tag> collection = new ArrayList<>();
        String[] tagArr = value[index].split("\\s*,\\s*");

        for (String s : tagArr) {
            collection.add(Tag.valueOf(s));
        }

        return collection;
    }

    public Uploader uploaderConverter(String[] value, int index) {
        Uploader uploader = new Person(value[index]);
        return uploader;
    }

    /**
     * Get max size of Parameter in Class
     * https://www.geeksforgeeks.org/constructor-getparametercount-method-in-java-with-examples/
     *
     * @param c = Class from Media
     * @return Integer
     */
    private int getParameter(Class c) {

        // get Constructor object
        // array from class object
        Constructor[] cons = c.getConstructors();

        ArrayList<Integer> list = new ArrayList<>();

        for (Constructor con : cons) {
            int params = con.getParameterCount();
            list.add(params);
        }

        return list.stream()
                .reduce(Integer::max)
                .get();
    }

    @Override
    public String toString() {
        return LICENSED_AUDIO_VIDEO_TEXT + "\n" +
                INTER_VIDEO_TEXT + "\n" +
                AUDIO_TEXT + "\n" +
                AUDIO_VIDEO_TEXT + "\n" +
                VIDEO_TEXT + "\n" +
                LICENSED_AUDIO_TEXT + "\n" +
                LICENSED_VIDEO_TEXT + "\n" +
                USER_TEXT;
    }
}
