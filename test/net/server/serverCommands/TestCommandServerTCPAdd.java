package net.server.serverCommands;

import modell.data.content.Person;
import modell.data.storage.Storage;
import modell.mediaDB.Uploader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;

public class TestCommandServerTCPAdd {

    /**
     * Das Senden vom DataInput oder DataOutput hat nicht richtig funktioniert
     */
    /**
     * @Test public void testAddUser() throws IOException {
     * Storage.getInstance().clear();
     * <p>
     * //Kann man auch ohne mockito machen
     * <p>
     * ByteArrayOutputStream baos = new ByteArrayOutputStream();
     * DataOutputStream out = new DataOutputStream(baos);
     * <p>
     * byte[] bytes = new byte[1024];
     * DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
     * <p>
     * String name = "name: KevinTechen";
     * out.writeUTF(name);
     * //before out
     * CommandServerAdd commandServerAdd = new CommandServerAdd(in, out);
     * commandServerAdd.run();
     * <p>
     * in.readUTF();
     * <p>
     * //after in
     * <p>
     * //Cli ohne netzwerk sollte observer funktion da sein
     * <p>
     * Assertions.assertEquals(1, Storage.getInstance().getPerson().size());
     * Assertions.assertEquals("KevinTechen", Storage.getInstance().getPerson().iterator().next());
     * }
     */

    @Test
    public void testAddUserHandleArgs() throws IOException {
        Storage.getInstance().clear();

        DataOutputStream out = Mockito.mock(DataOutputStream.class);
        DataInputStream in = Mockito.mock(DataInputStream.class);

        final String name = "name: KevinTechen";
        final String expected = "KevinTechen";

        final Person person = new Person(expected);

        CommandServerAdd commandServerAdd = new CommandServerAdd(in, out);
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
            CommandServerAdd commandServerAdd = new CommandServerAdd(in, out);
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
            CommandServerAdd commandServerAdd = new CommandServerAdd(in, out);
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

        CommandServerAdd commandServerAdd = new CommandServerAdd(in, out);
        commandServerAdd.handleArgs(name);

        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
    }
}
