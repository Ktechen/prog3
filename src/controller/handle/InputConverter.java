package controller.handle;

import controller.crud.Create;
import controller.crud.Read;
import modell.data.content.Person;
import modell.mediaDB.Tag;
import modell.mediaDB.Uploader;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;

 public class InputConverter {

    public InputConverter() {
    }

    /**
     * Convert e.g Interactive
     * Split your Array before you start (value.split("\\s+");)
     *
     * @param arr
     * @return length = 9 and arr with all option from Lic Video
     */
    public Object[] convertLicensedVideo(String[] arr) {
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

        Object[] o = new Object[8];

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

        String value = "";

        for (String var : arr) {
            value += var;
        }

        return value;
    }

    private int intConverter(String[] value, int index) {
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

    private Collection<Tag> tagCollectionConverter(String[] value, int index) {
        Collection<Tag> collection = new ArrayList<>();
        String[] tagArr = value[5].split("\\s*,\\s*");
        for (String s : tagArr) {
            collection.add(Tag.valueOf(s));
        }

        return collection;
    }

    private Uploader uploaderConverter(String[] value, int index) {
        Uploader uploader = new Person(value[index]);
        return uploader;
    }
}
