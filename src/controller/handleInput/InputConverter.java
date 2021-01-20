package controller.handleInput;

import controller.cli.Keys;
import modell.data.content.Person;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;

public class InputConverter {

    public final static String NAME = "name:";
    public final static String LICENSED_AUDIO_VIDEO = "lav:";
    public final static String INTERACTIVE_VIDEO = "iv:";
    public final static String AUDIO = "a:";
    public final static String AUDIO_VIDEO = "av:";
    public final static String MEDIA_CONTENT = "mc:";
    public final static String VIDEO = "v:";

    public final static int LICENSED_AUDIO_VIDEO_LENGTH = 9;
    public final static int INTERACTIVE_VIDEO_LENGTH = 8;

    public final static String SHOW_ALL = "1. Showall or filter\n";
    public final static String SHOW_PER_INDEX = "2. User per Index \n";
    public final static String SHOW_TAGS = "3. Show used tags\n";
    public final static String SHOW_CONTENT = "content[[Typ]] Anzeige der Mediadateien-ggf. \n " +
            "gefiltert nach Typ1-mit Abrufadresse, Upload-Datumund Anzahl der Abrufe";

    public final static String DELETE_USER = "[Produzentenname]löscht den Produzenten";
    public final static String DELETE_ADDRESSE = "[Abrufadresse]löscht die Mediadatei";

    private final static String usedTag = "Enter TAG: ";

    public final static String LICENSED_AUDIO_VIDEO_TEXT = usedTag + LICENSED_AUDIO_VIDEO + " LicensedAudioVideo: " +
            "(int width, int height, String encoding, long bitrate, Duration length, " +
            "Collection<Tag> tag, Person person, String holder, int samplingRate)";

    public final static String INTER_VIDEO_TEXT = usedTag + INTERACTIVE_VIDEO + " Interactive: " +
            "(int width, int height, String encoding, long bitrate, Duration length, " +
            "Collection<Tag> tag, Person person, String type)";

    public final static String USER_TEXT = usedTag + NAME + " [Produzentenname] fügt einen Produzentein";

    public final static String MAIN_TEXT = "\n" + Keys.ADD.get() + " Wechsel in den Einfügemodus\n" +
            Keys.SHOW.get() + " - Wechsel in den Anzeigemodus\n" +
            Keys.DELETE.get() + " - Wechsel in den Löschmodus\n" +
            Keys.CHANGE.get() + " - Wechsel in den Änderungsmodus\n" +
            Keys.CONFIG.get() + " - Wechsel in den Konfigurationsmodus\n" +
            Keys.PERSISTENCE.get() + " - Wechsel in den Persistenzmodus\n" +
            ":back - Show default view\n";

    public final static String CONFIG_ADD_TEXT = "add [Klassenname]";
    public final static String CONFIG_REMOVE_TEXT = "remove [Klassenname]";

    public final static String UPDATE_TEXT = "[Abrufadresse] erhöht den Zähler für die Abrufe um eins";

    /**
     * Convert e.g Interactive
     * Split your Array before you start (value.split("\\s+");)
     *
     * @param arr
     * @return length = 9 and arr with all option from Lic Video
     */
    public Object[] convertLicensedVideo(String[] arr) throws IllegalArgumentException {

        if (null == arr) {
            throw new NullPointerException("Array is null or empty");
        }

        if (arr.length != 9) {
            throw new IllegalArgumentException("Format isn't incorrect ");
        }

        Object[] array = convertInteractionVideo(arr);

        try {
            //sampling rate
            array[array.length - 1] = Integer.parseInt(arr[array.length - 1]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return array;
    }

    /**
     * Handle InteractionVideo
     * Split your Array before you start (value.split("\\s+");)
     *
     * @param value
     * @return length = 8 and arr with all option from Video
     */
    public Object[] convertInteractionVideo(String[] value) {

        if (null == value) {
            throw new NullPointerException("Array is null or empty");
        }

        Object[] o = new Object[value.length];

        try {
            o[0] = intConverter(value, 0);// Width
            o[1] = intConverter(value, 1); // heigth
            o[2] = value[2]; // encoding
            o[3] = longConverter(value, 3); // bitrate
            o[4] = durationConverter(value, 4); // duraction
            o[5] = tagCollectionConverter(value, 5);
            o[6] = uploaderConverter(value, 6); // Person
            o[7] = value[7]; // type

        } catch (NumberFormatException | DateTimeParseException e) {
            e.printStackTrace();
        }

        return o;
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

        if (!value[4].contains("PT")) {
            value[4] = "PT" + value[4];
        }

        return Duration.parse(value[index]);
    }

    public Collection<Tag> tagCollectionConverter(String[] value, int index) {
        Collection<Tag> collection = new ArrayList<>();
        String[] tagArr = value[5].split("\\s*,\\s*");

        for (String s : tagArr) {
            collection.add(Tag.valueOf(s));
        }

        return collection;
    }

    public Uploader uploaderConverter(String[] value, int index) {
        Uploader uploader = new Person(value[index]);
        return uploader;
    }

    @Override
    public String toString() {
        return LICENSED_AUDIO_VIDEO_TEXT + "\n" +
                INTER_VIDEO_TEXT + "\n" +
                USER_TEXT;
    }
}
