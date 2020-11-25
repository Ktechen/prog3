package controller;

import controller.crud.Read;
import modell.data.content.Person;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class InputConverter {

    public InputConverter() {
    }

    public static final String LIC_VIDEO_TEXT = "LicensedAudioVideo: " +
            "(int width, int height, String encoding, long bitrate, Duration length, " +
            "Collection<Tag> tag, Person person, String holder, int samplingRate)";

    public static final String INTER_VIDEO_TEXT = "Interactive: " +
            "(int width, int height, String encoding, long bitrate, Duration length, " +
            "Collection<Tag> tag, Person person, String type)";

    public static final String USER_TEXT = "[Produzentenname] fügt einen Produzentein";

    /**
     * Convert e.g Interactive
     * Split your Array before you start (value.split("\\s+");)
     *
     * @param arr
     * @return length = 9 and arr with all option from Lic Video
     */
    public Object[] convertToArrLicVideo(String[] arr) {
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
     * Split your Array before you start (value.split("\\s+");)
     *
     * @param arr
     * @return length = 8 and arr with all option from Video
     */
    public Object[] convertToArr(String[] arr) {

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
        Uploader person = null;
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

}
