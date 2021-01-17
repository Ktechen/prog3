package controller.handleInput.delete;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDeleteOption {

    @Test
    public void testRunParameterIsNull() {
        DeleteOption deleteOption = new DeleteOption();

        try {
            deleteOption.run(null);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void testRunByAddress() {
        DeleteOption deleteOption = new DeleteOption();

        String address = "LicensedAudioVideo-FILE:///-bdb58d8e-f8d9-4d13-86c5-f970161ba924-Mon Jan 18 00:11:06 CET 2021-1610925066705";

        String check = deleteOption.run(address);

        Assertions.assertEquals(check, "Mediafile Status: " + false);
    }

    @Test
    public void testRunByPerUser() {
        DeleteOption deleteOption = new DeleteOption();

        String name = "Torsten Schreiber";

        String check = deleteOption.run(name);

        Assertions.assertEquals(check, "User Status: " + false);
    }
}
