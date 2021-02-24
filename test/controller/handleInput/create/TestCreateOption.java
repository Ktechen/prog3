package controller.handleInput.create;

import controller.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCreateOption {

    @Test
    public void testValueIsNull() {
        final CreateOption createOption = new CreateOption();
        Assertions.assertThrows(NullPointerException.class, () -> {
            createOption.run(null, "name");
        });
    }

    @Test
    public void testSelectedIsNull() {
        final CreateOption createOption = new CreateOption();
        Assertions.assertThrows(NullPointerException.class, () -> {
            String[] strings = new String[2];
            strings[0] = "dasdsaas";
            strings[1] = "dasndasd";
            createOption.run(strings, null);
        });
    }

    private void testLengthOfType(String selected) {
        final CreateOption createOption = new CreateOption();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String[] strings = new String[2];
            strings[0] = "dasdsaas";
            strings[1] = "dasndasd";
            createOption.run(strings, selected);
        });
    }

    @Test
    public void testLengthOfInteractiveVideo() {
        this.testLengthOfType("iv");
    }

    @Test
    public void testLengthOfVideo() {
        this.testLengthOfType("v");
    }

    @Test
    public void testLengthOfAudio() {
        this.testLengthOfType("a");
    }

    @Test
    public void testLengthOfAudioVideo() {
        this.testLengthOfType("av");
    }

    @Test
    public void testLengthOfLicAudioVideo() {
        this.testLengthOfType("lav");
    }

    @Test
    public void testLengthOfLicVideo() {
        this.testLengthOfType("lv");
    }

    @Test
    public void testLengthOfLicAudio() {
        this.testLengthOfType("la");
    }

    @Test
    public void testCreateVideo() {
        Storage.getInstance().clear();
        final CreateOption createOption = new CreateOption();
        String[] value = new String[7];
        value[0] = "300";
        value[1] = "300";
        value[2] = "mix";
        value[3] = "3043";
        value[4] = "PT30m";
        value[5] = "News";
        value[6] = "KevinTechen";

        String returnValue = createOption.run(value, "v");
        Assertions.assertEquals("VIDEO", returnValue);
        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
    }


    @Test
    public void testCreateAudio() {
        Storage.getInstance().clear();
        final CreateOption createOption = new CreateOption();
        String[] value = new String[6];
        value[0] = "3043";
        value[1] = "PT30m";
        value[2] = "News";
        value[3] = "3232";
        value[4] = "F.B.I Gaming Studio";
        value[5] = "KevinTechen";

        String returnValue = createOption.run(value, "a");
        Assertions.assertEquals("AUDIO", returnValue);
        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testCreateAudioVideo() {
        Storage.getInstance().clear();
        final CreateOption createOption = new CreateOption();
        String[] value = new String[8];
        value[0] = "300";
        value[1] = "300";
        value[2] = "mix";
        value[3] = "3043";
        value[4] = "PT30m";
        value[5] = "News";
        value[6] = "KevinTechen";
        value[7] = "3232";

        String returnValue = createOption.run(value, "av");
        Assertions.assertEquals("AUDIO_VIDEO", returnValue);
        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testCreateLicensedAudio() {
        Storage.getInstance().clear();
        final CreateOption createOption = new CreateOption();
        String[] value = new String[7];
        value[0] = "3043";
        value[1] = "PT30m";
        value[2] = "News";
        value[3] = "3232";
        value[4] = "F.B.I Gaming Studio";
        value[5] = "KevinTechen";
        value[6] = "Paul";

        String returnValue = createOption.run(value, "la");
        Assertions.assertEquals("LICENSED_AUDIO", returnValue);
        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testCreateLicensedVideo() {
        Storage.getInstance().clear();
        final CreateOption createOption = new CreateOption();
        String[] value = new String[8];
        value[0] = "300";
        value[1] = "300";
        value[2] = "mix";
        value[3] = "3043";
        value[4] = "PT30m";
        value[5] = "News";
        value[6] = "KevinTechen";
        value[7] = "Paule";

        String returnValue = createOption.run(value, "lv");
        Assertions.assertEquals("LICENSED_VIDEO", returnValue);
        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testCreateInteractiveVideo() {
        Storage.getInstance().clear();
        final CreateOption createOption = new CreateOption();
        String[] value = new String[8];
        value[0] = "300";
        value[1] = "300";
        value[2] = "mix";
        value[3] = "3043";
        value[4] = "PT30m";
        value[5] = "News";
        value[6] = "KevinTechen";
        value[7] = "F.B.I Gaming Studio";

        String returnValue = createOption.run(value, "iv");
        Assertions.assertEquals("INTERACTIVE_VIDEO", returnValue);
        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
    }
}
