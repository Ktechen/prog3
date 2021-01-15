package controller.cli;

import modell.mediaDB.Tag;

import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

public interface IConsole {

    String readInput(String message) throws IOException;

    String readInput(String message, List<String> allowOptions) throws IOException;

    Integer inputInteger(String message) throws IOException;

    Long inputLong(String message) throws IOException;

    Duration inputDuration(String message) throws IOException;

    Collection<Tag> inputCollection(String message,  String triggerElement);

}
