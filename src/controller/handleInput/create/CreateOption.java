package controller.handleInput.create;

import controller.handleInput.InputConverter;

/**
 * Handle die Eingabe vom Benutzer
 */
public final class CreateOption {

    private CreateController createController;

    public CreateOption() {
        createController = new CreateController();
    }

    /**
     * Verarbeitet die Eingabe der Benutzer
     *
     * @param value
     * @param select
     * @return which value was been created e.g uploader or Mediafile ...
     * @throws NullPointerException
     */
    public String run(String[] value, String select) throws NullPointerException, IllegalArgumentException {
        InputConverter inputConverter = new InputConverter();
        String msg = "input was too incorrect";

        switch (select) {
            case "name":
                createController.person(value);
                return "Uploader";
            case "iv":
                if (value.length != inputConverter.INTERACTIVE_VIDEO_LENGTH) {
                    throw new IllegalArgumentException(msg);
                }
                createController.interactiveVideo(value);
                return "INTERACTIVE_VIDEO";
            case "lav":
                if (value.length != inputConverter.LICENSED_AUDIO_VIDEO_LENGTH) {
                    throw new IllegalArgumentException(msg);
                }
                createController.licensedAudioVideo(value);
                return "LICENSED_AUDIO_VIDEO";
            case "a":
                if (value.length != inputConverter.AUDIO_LENGTH) {
                    throw new IllegalArgumentException(msg);
                }
                this.createController.audio(value);
                return "AUDIO";
            case "av":
                if (value.length != inputConverter.AUDIO_VIDEO_LENGTH) {
                    throw new IllegalArgumentException(msg);
                }
                this.createController.audioVideo(value);
                return "AUDIO_VIDEO";
            case "v":
                if (value.length != inputConverter.VIDEO_LENGTH) {
                    throw new IllegalArgumentException(msg);
                }
                this.createController.video(value);
                return "VIDEO";
            case "la":
                if (value.length != inputConverter.LICENSED_AUDIO_LENGTH) {
                    throw new IllegalArgumentException(msg);
                }
                this.createController.licensedAudio(value);
                return "LICENSED_AUDIO";
            case "lv":
                if (value.length != inputConverter.LICENSED_VIDEO_LENGTH) {
                    throw new IllegalArgumentException(msg);
                }
                this.createController.licensedVideo(value);
                return "LICENSED_VIDEO";
            default:
                throw new NullPointerException("Selected element no found");
        }
    }

}
