package controller.management;

import controller.crud.Create;
import controller.handleInput.InputConverter;
import controller.handleInput.stream.StreamOptions;
import controller.observer.observers.ObserverConsoleSize;
import controller.observer.observers.ObserverConsoleTags;
import controller.stream.jos.JOS;
import modell.data.content.AudioVideo;
import modell.data.content.Person;
import modell.data.content.Video;
import controller.storage.Storage;
import modell.mediaDB.MediaContent;
import modell.mediaDB.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class TestCommandManagement {

    @BeforeEach
    public void setup(){
        Storage.getInstance().clear();
        Create.getObserverList().clear();
    }

    @Test
    public void testCommandManagementDefault() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        String name = "Trash: Power";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(name);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        byte[] bytes = baos.toByteArray();

        String s = new String(bytes);
        String exc = "The option is not valid";

        Assertions.assertEquals(exc, s.replace("\0", "").replace("\u0017", ""));
    }

    @Test
    public void testCommandManagementBack() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        String name = ":back";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(name);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        byte[] bytes = baos.toByteArray();

        String s = new String(bytes);

        Assertions.assertEquals(InputConverter.MAIN_TEXT, s.replace("\0", "").replace("�", "").replace("\u0017", ""));
    }

    @Test
    public void testCommandManagementAdd() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        String option = ":c";
        String media = "lav: 300 400 Mix 9382 20m News Kevin Paul 2035";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(media);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testCommandManagementAddMissingBitrateMediaInput() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        String option = ":c";
        //Missing Bitrate
        String media = "lav: 300 400 Mix 20m News Kevin Paul 2035";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(media);

        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
            commandManagementExecuteSession.executeSession(in, out);
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(0, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testCommandManagementDelete() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        String option = ":d";

        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));

        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(0);

        String media = content.getAddress();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(media);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        Assertions.assertEquals(0, Storage.getInstance().getMedia().size());
    }

    @Test
    public void testCommandManagementAllShow() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        String option = ":r";
        String selected = "1";
        String input = "";

        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));

        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(selected);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        byte[] bytes = baos.toByteArray();

        String s = new String(bytes);

        Assertions.assertTrue(s.contains(content.getAddress()));
    }

    @Test
    public void testCommandManagementNotFoundSearchByAudioVideoShow() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        String option = ":r";
        String selected = "1";
        String input = AudioVideo.class.getSimpleName();

        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));

        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(selected);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        byte[] bytes = baos.toByteArray();

        String s = new String(bytes);

        Assertions.assertFalse(s.contains(content.getAddress()));
    }

    @Test
    public void testCommandManagementShow2Medias() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        String option = ":r";
        String selected = "1";
        String input = Video.class.getSimpleName();

        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));

        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(0);
        MediaContent content1 = (MediaContent) Storage.getInstance().getMedia().get(1);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(selected);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        byte[] bytes = baos.toByteArray();

        String s = new String(bytes);

        Assertions.assertTrue(s.contains(content.getAddress()));
        Assertions.assertTrue(s.contains(content1.getAddress()));
    }

    @Test
    public void testCommandManagementShowUploaderUsed() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));

        String option = ":r";
        String selected = "2";
        String input = "Rambo";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(selected);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        byte[] bytes = baos.toByteArray();

        String s = new String(bytes);

        String excepted = "[Rambo] | [2]";

        Assertions.assertTrue(s.contains(excepted));
    }

    @Test
    public void testCommandManagementShowUploaderNotUsed() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));

        String option = ":r";
        String selected = "2";
        String input = "Tim Rafaelo";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(selected);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        byte[] bytes = baos.toByteArray();

        String s = new String(bytes);

        String excepted = "[TimRafaelo] | [0]";

        Assertions.assertTrue(s.contains(excepted));
    }

    @Test
    public void testCommandManagementShowUsedTagsE() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));

        String option = ":r";
        String selected = "3";
        String input = "e";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(selected);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        byte[] bytes = baos.toByteArray();

        String s = new String(bytes);

        String excepted = Tag.Animal.name();

        String exceptedNot1 = Tag.Lifestyle.name();

        String exceptedNot2 = Tag.News.name();

        String exceptedNot3 = Tag.Tutorial.name();

        Assertions.assertTrue(s.contains(excepted));
        Assertions.assertFalse(s.contains(exceptedNot1));
        Assertions.assertFalse(s.contains(exceptedNot2));
        Assertions.assertFalse(s.contains(exceptedNot3));
    }

    @Test
    public void testCommandManagementShowUsedTagsI() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));

        String option = ":r";
        String selected = "3";
        String input = "i";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(selected);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        byte[] bytes = baos.toByteArray();

        String s = new String(bytes);

        String exceptedNot = Tag.Animal.name();

        String excepted1 = Tag.Lifestyle.name();

        String excepted2 = Tag.News.name();

        String excepted3 = Tag.Tutorial.name();

        Assertions.assertFalse(s.contains(exceptedNot));
        Assertions.assertTrue(s.contains(excepted1));
        Assertions.assertTrue(s.contains(excepted2));
        Assertions.assertTrue(s.contains(excepted3));
    }

    @Test
    public void testCommandManagementUpdateAddressNotFound() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();


        String option = ":u";
        String input = "i";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        byte[] bytes = baos.toByteArray();

        String s = new String(bytes);

        String expected = "Address not found";

        Assertions.assertTrue(s.contains(expected));
    }

    @Test
    public void testCommandManagementUpdateAddress() throws IOException {
        Storage.getInstance().clear();

        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));

        MediaContent content = (MediaContent) Storage.getInstance().getMedia().get(0);

        String option = ":u";
        String input = content.getAddress();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        Assertions.assertEquals(1, ((MediaContent) Storage.getInstance().getMedia().get(0)).getAccessCount());
    }

    @Test
    public void testCommandManagementConfigRemoveObserverConsoleSize() throws IOException {
        Storage.getInstance().clear();

        final Create create = new Create();
        ObserverConsoleTags observerConsoleTags = new ObserverConsoleTags(create);
        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(create);

        String option = ":config";
        String input = "remove " + ObserverConsoleSize.class.getSimpleName();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        Assertions.assertEquals(1, Create.getObserverList().size());
        create.leave(observerConsoleSize);
        create.leave(observerConsoleTags);
    }

    @Test
    public void testCommandManagementConfigAddObserverConsoleSize() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        String option = ":config";
        String input = "add";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        Assertions.assertEquals(1, Create.getObserverList().size());
    }

    @Test
    public void testCommandManagementPersistenceFalseTypeOfSave() throws IOException {
        Storage.getInstance().clear();
        Create.getObserverList().clear();

        String option = ":p";
        String input = "saveJOB";

        File file = new File(JOS.PATH + StreamOptions.SAVE_JOS);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        Assertions.assertFalse(file.exists());

    }

    @Test
    public void testCommandManagementPersistenceSaveJOS() throws IOException {
        Storage.getInstance().clear();
        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));

        String option = ":p";
        String input = "saveJOS";

        File file = new File(JOS.PATH + "file.txt");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        Assertions.assertTrue(file.exists());
        Assertions.assertTrue(file.length() > 0);
        file.delete();
    }

    @Test
    public void testCommandManagementPersistenceSaveJOSLoad() throws IOException {
        Storage.getInstance().clear();
        final Create create = new Create();
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.Animal);
        create.video(300, 300, "mix", 3232, Duration.parse("PT20m"), tags, new Person("Rambo"));

        String option = ":p";
        String input = "saveJOS";

        File file = new File(JOS.PATH + "file.txt");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeUTF(option);
        dataOutputStream.writeUTF(input);

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSession = new CommandManagementExecuteSession();
        commandManagementExecuteSession.executeSession(in, out);

        String optionLoad = ":p";
        String inputLoad = "loadJOS";

        ByteArrayOutputStream baosLoad = new ByteArrayOutputStream();
        DataOutputStream outLoad = new DataOutputStream(baosLoad);

        ByteArrayOutputStream byteArrayOutputStreamLoad = new ByteArrayOutputStream();
        DataOutputStream dataOutputStreamLoad = new DataOutputStream(byteArrayOutputStreamLoad);
        dataOutputStreamLoad.writeUTF(optionLoad);
        dataOutputStreamLoad.writeUTF(inputLoad);

        DataInputStream inLoad = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStreamLoad.toByteArray()));
        CommandManagementExecuteSession commandManagementExecuteSessionLoad = new CommandManagementExecuteSession();
        commandManagementExecuteSessionLoad.executeSession(inLoad, outLoad);

        Assertions.assertEquals(1, Storage.getInstance().getMedia().size());
        Assertions.assertEquals(1, Storage.getInstance().getPerson().size());

        file.delete();
    }
}
