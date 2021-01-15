package controller.handle.create.IO;

import controller.crud.Create;
import modell.mediaDB.InteractiveVideo;
import modell.mediaDB.LicensedAudioVideo;

public class CreateOptionIO {

    public CreateOptionIO(Object o) {
        Create create = new Create();
        InteractiveVideo(o, create);
        LicensedAudioVideo(o, create);
    }

    private void InteractiveVideo(Object o, Create create) {
        if (o instanceof InteractiveVideo) {
            InteractiveVideo video = (InteractiveVideo) o;
            create.interactiveVideo(
                    video.getWidth(), video.getWidth(),
                    video.getEncoding(), video.getBitrate(),
                    video.getLength(), video.getTags(),
                    video.getUploader(), video.getType()
            );
        }
    }

    private void LicensedAudioVideo(Object o, Create create) {
        if (o instanceof LicensedAudioVideo) {
            LicensedAudioVideo video = (LicensedAudioVideo) o;
            create.licensedAudioVideo(
                    video.getWidth(), video.getHeight(),
                    video.getEncoding(), video.getBitrate(),
                    video.getLength(), video.getTags(),
                    video.getUploader(), video.getHolder(),
                    video.getSamplingRate()
            );
        }
    }
}
