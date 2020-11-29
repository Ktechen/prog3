package controller.handle;

public class Const {

    public final static String NAME = "name:";
    public final static String LICENSED_AUDIO_VIDEO = "lav:";
    public final static String INTERACTIVE_VIDEO = "iv:";
    public final static String AUDIO = "a:";
    public final static String AUDIO_VIDEO = "av:";
    public final static String MEDIA_CONTENT = "mc:";
    public final static String VIDEO = "v:";

    private static final String usedTag = "Enter TAG: ";

    public static final String LICENSED_AUDIO_VIDEO_TEXT = usedTag + LICENSED_AUDIO_VIDEO + " LicensedAudioVideo: " +
            "(int width, int height, String encoding, long bitrate, Duration length, " +
            "Collection<Tag> tag, Person person, String holder, int samplingRate)";

    public static final String INTER_VIDEO_TEXT = usedTag + INTERACTIVE_VIDEO + " Interactive: " +
            "(int width, int height, String encoding, long bitrate, Duration length, " +
            "Collection<Tag> tag, Person person, String type)";

    public static final String USER_TEXT = usedTag + NAME + " [Produzentenname] fügt einen Produzentein";


    @Override
    public String toString() {
        return LICENSED_AUDIO_VIDEO_TEXT + "\n" +
                INTER_VIDEO_TEXT + "\n" +
                USER_TEXT;
    }
}
