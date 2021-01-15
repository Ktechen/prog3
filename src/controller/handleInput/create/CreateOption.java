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
    public String run(String[] value, String select) throws NullPointerException, InterruptedException {
        switch (select) {
            case "name":
                createController.person(value);
                return "Uploader";
            case "iv":
                if (value.length != InputConverter.INTERACTIVE_VIDEO_LENGTH) {
                    throw new IllegalArgumentException("Input was incorrect");
                }
                createController.interactiveVideo(value);
                return "INTERACTIVE_VIDEO";
            case "lav":
                if (value.length != InputConverter.LICENSED_AUDIO_VIDEO_LENGTH) {
                    throw new IllegalArgumentException("Input was incorrect");
                }
                createController.licensedAudioVideo(value);
                return "LICENSED_AUDIO_VIDEO";
        }
        //TODO ADD ALL MEDIA

        throw new NullPointerException("Selected element no found");
    }

}
