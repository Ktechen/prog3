package controller.cli.commands.options;

import controller.handleInput.InputConverter;
import controller.event.EventHandler;
import controller.event.EventListener;
import controller.cli.Console;
import controller.cli.ICommand;
import controller.event.events.commands.CommandAddEvents;


public class CommandAdd implements ICommand {

    private EventHandler<EventListener> eventHandler;
    private final Console cs;
    private InputConverter converter;
    private final CommandAddEvents commandAddEvents;

    public CommandAdd() {
        this.eventHandler = new EventHandler<>();
        this.cs = new Console();
        this.converter = new InputConverter();
        this.commandAddEvents = new CommandAddEvents(this.converter, this.eventHandler);
    }

    /**
     * ServerTCP a
     *
     * @throws NullPointerException = input was null or empty
     */
    @Override
    public void run() throws NullPointerException {

        //TODO Change to Updated code versions show controller/handle

        System.out.println(toString());
        Console console = new Console();
        String value = console.input("--------------------");

        //Convert a String to String[]
        String[] videoArray = value.split("\\s+");

        if (videoArray.length == 0) {
            throw new NullPointerException("Input cannot be analyse");
        }

        //Name convert to one string
        if (videoArray.length == 2) {
            videoArray[0] = videoArray[0] + videoArray[1];
            videoArray[1] = null;
        }

        //TODO Vorher erstellen in der Config
        switch (videoArray.length) {
            case 1:
            case 2:
                this.commandAddEvents.eventUser(videoArray[0]);
                break;
            case 8:
                this.commandAddEvents.eventInteractiveVideo(videoArray);
                break;
            case 9:
                this.commandAddEvents.eventLicenseVideo(videoArray);
                break;
        }
    }


    @Override
    public String toString() {
        return InputConverter.USER_TEXT + "\n" +
                InputConverter.INTER_VIDEO_TEXT + "\n" +
                InputConverter.LICENSED_AUDIO_VIDEO_TEXT;
    }
}
