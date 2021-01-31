import controller.cli.commands.CommandMain;

import java.io.IOException;

public class MainCLI {
    public static void main(String[] args) {
        try {
            new CommandMain().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
