import controller.cli.commands.CommandMain;
import controller.crud.Create;
import controller.gui.delegate.view.ActionDebug;
import controller.observer.observers.ObserverConsoleSize;
import controller.observer.observers.ObserverConsoleTags;
import modell.data.content.Audio;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class MainCLI {


    public static void main(String[] args) {


        /*
         * Set Default elemente

         */
         new ActionDebug().setupOne(null);

         try {
         config();
         new CommandMain().run();
         } catch (IOException e) {
         e.printStackTrace();
         }
    }

    private static void config() {
        final Create create = new Create();
        new ObserverConsoleSize(create);
        new ObserverConsoleTags(create);
    }
}
