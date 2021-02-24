package controller.management;

import modell.data.content.Person;
import controller.storage.Storage;
import modell.mediaDB.Uploader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.util.HashSet;

public class TestCommandManagementAdd {


    @Test
    public void testAddUser() throws IOException {
        Storage.getInstance().clear();

        //Kann man auch ohne mockito machen

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        //before out
        String name = "name: KevinTechen";
        //out.writeUTF(name);
        //out.flush();

        /**
         * DataOutputStream zur um DataInputStream gewandelt
         */

        //Input Stream and Outputstream sind verstauscht deswegen muss dem bekommt der Input denn Output
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(name);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementAdd commandServerAdd = new CommandManagementAdd(in, out);
        commandServerAdd.run();

        //in.readUTF();

        //after in

        //Cli ohne netzwerk sollte observer funktion da sein

        Uploader uploader = (Uploader) Storage.getInstance().getPerson().iterator().next();

        Assertions.assertEquals(1, Storage.getInstance().getPerson().size());
        Assertions.assertEquals("KevinTechen", uploader.getName());
    }


    @Test
    public void testAddUserHandleArgs() throws IOException {
        Storage.getInstance().clear();

        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        DataInputStream in = Mockito.mock(DataInputStream.class);

        final String name = "name: KevinTechen";
        final String expected = "KevinTechen";

        final Person person = new Person(expected);

        CommandManagementAdd commandServerAdd = new CommandManagementAdd(in, out);
        commandServerAdd.handleArgs(name);

        HashSet<Uploader> uploaders = Storage.getInstance().getPerson();


        Assertions.assertEquals(1, Storage.getInstance().getPerson().size());
        Assertions.assertEquals(person.getName(), uploaders.iterator().next().getName());
    }

    @Test
    public void testAddUserHandleArgsTagUnknown() throws IOException {
        Storage.getInstance().clear();

        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        DataInputStream in = Mockito.mock(DataInputStream.class);

        final String name = "lost: person";

        try {
            CommandManagementAdd commandServerAdd = new CommandManagementAdd(in, out);
            commandServerAdd.handleArgs(name);
        } catch (NullPointerException e) {
            Assertions.assertTrue(true);
        }

        Assertions.assertEquals(0, Storage.getInstance().getPerson().size());
    }

    @Test
    public void testAddLicVideoAudioFalseTag() throws IOException {
        Storage.getInstance().clear();

        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        DataInputStream in = Mockito.mock(DataInputStream.class);

        final String name = "iv: person";

        try {
            CommandManagementAdd commandServerAdd = new CommandManagementAdd(in, out);
            commandServerAdd.handleArgs(name);
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(true);
        }

        Assertions.assertEquals(0, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testAddLicVideo() throws IOException {
        Storage.getInstance().clear();

        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        DataInputStream in = Mockito.mock(DataInputStream.class);

        //(int width, int height, String encoding, long bitrate, Duration length, Collection<Tag> tag, Person person, String holder, int samplingRate)
        final String name = "lav: 300 400 Mix 9382 20m News Kevin Paul 2035";

        CommandManagementAdd commandServerAdd = new CommandManagementAdd(in, out);
        commandServerAdd.handleArgs(name);

        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
    }
}
