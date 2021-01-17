package controller.handleInput;

import modell.data.content.Person;
import modell.mediaDB.Tag;
import net.bytebuddy.implementation.auxiliary.MethodCallProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TestInputConverter {


    @Test
    public void testArrayIsNullConvertLicensedVideo() {
        final InputConverter inputConverter = new InputConverter();

        try {
            inputConverter.convertLicensedVideo(null);
        } catch (NullPointerException e) {
            Assertions.assertEquals("Array is null or empty", e.getMessage());
        }
    }

    @Test
    public void testArrayLengthNot9ConvertLicensedVideo() {
        final InputConverter inputConverter = new InputConverter();

        String[] strings = new String[3];

        try {
            inputConverter.convertLicensedVideo(strings);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void testArrayLengthIs9ConvertLicensedVideo() {
        final InputConverter inputConverter = new InputConverter();

        String[] strings = new String[9];

        try {
            inputConverter.convertLicensedVideo(strings);
        } catch (NumberFormatException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void testConvertInteractionVideo() {
        final InputConverter inputConverter = new InputConverter();

        String[] value = new String[8];
        value[0] = "300";
        value[1] = "300";
        value[2] = "mix";
        value[3] = "3043";
        value[4] = "PT30m";
        value[5] = "News";
        value[6] = "KevinTechen";
        value[7] = "F.B.I Gaming Studio";

        Object[] converted = new Object[value.length];
        converted[0] = 300;
        converted[1] = 300;
        converted[2] = "mix";
        converted[3] = Long.parseLong("3043");
        converted[4] = Duration.parse("PT30m");
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.News);
        converted[5] = tags;
        Person person = new Person("KevinTechen");
        converted[6] = person;
        converted[7] = "F.B.I Gaming Studio";

        Object[] result = inputConverter.convertInteractionVideo(value);
        Assertions.assertEquals(result[0], converted[0]);
        Assertions.assertEquals(result[1], converted[1]);
        Assertions.assertEquals(result[2], converted[2]);
        Assertions.assertEquals(result[3], converted[3]);
        Assertions.assertEquals(result[4], converted[4]);
        Assertions.assertEquals(result[5], converted[5]);
        Assertions.assertEquals(result[6].toString(), converted[6].toString());
        Assertions.assertEquals(result[7], converted[7]);
    }

    @Test
    public void testConvertInteractionVideoWithoutDurationPT() {
        final InputConverter inputConverter = new InputConverter();

        String[] value = new String[8];
        value[0] = "300";
        value[1] = "300";
        value[2] = "mix";
        value[3] = "3043";
        value[4] = "30m";
        value[5] = "News";
        value[6] = "KevinTechen";
        value[7] = "F.B.I Gaming Studio";

        Object[] converted = new Object[value.length];
        converted[0] = 300;
        converted[1] = 300;
        converted[2] = "mix";
        converted[3] = Long.parseLong("3043");
        converted[4] = Duration.parse("PT30m");
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.News);
        converted[5] = tags;
        Person person = new Person("KevinTechen");
        converted[6] = person;
        converted[7] = "F.B.I Gaming Studio";

        Object[] result = inputConverter.convertInteractionVideo(value);
        Assertions.assertEquals(result[0], converted[0]);
        Assertions.assertEquals(result[1], converted[1]);
        Assertions.assertEquals(result[2], converted[2]);
        Assertions.assertEquals(result[3], converted[3]);
        Assertions.assertEquals(result[4], converted[4]);
        Assertions.assertEquals(result[5], converted[5]);
        Assertions.assertEquals(result[6].toString(), converted[6].toString());
        Assertions.assertEquals(result[7], converted[7]);
    }

    @Test
    public void testConvertInteractionVideoThrowNullPointerException() {
        final InputConverter inputConverter = new InputConverter();

        try {
            inputConverter.convertInteractionVideo(null);
        } catch (NullPointerException e) {
            Assertions.assertEquals("Array is null or empty", e.getMessage());
        }
    }

    @Test
    public void testConvertedUploaderNullPointer() {
        final InputConverter inputConverter = new InputConverter();
        try {
            inputConverter.convertedUploader(null);
        } catch (NullPointerException e) {
            Assertions.assertEquals("Array is null or empty", e.getMessage());
        }
    }

}
