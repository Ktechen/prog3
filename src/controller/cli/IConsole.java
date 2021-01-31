package controller.cli;

import modell.mediaDB.Tag;

import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

public interface IConsole {

    String readInput(String message) throws IOException;

    @Deprecated
    String readInput(String message, List<String> allowOptions) throws IOException;

    @Deprecated
    Integer inputInteger(String message) throws IOException;

    @Deprecated
    Long inputLong(String message) throws IOException;

    @Deprecated
    Duration inputDuration(String message) throws IOException;

    @Deprecated
    Collection<Tag> inputCollection(String message, String triggerElement);

}
