package controller.cli;

@Deprecated
public enum KeysAction {

    BACKTOMAIN(99), EXIT(0), UPLOADER(1), INTERACTIONVIDEO(2), LicensedAudioVideo(3),
    ADRESS(2),
    ListAllUsernamePerIndexValue(1), FULLLIST(2), TAGS(3);

    private int i;

    KeysAction(int i) {
        this.i = i;
    }
}
