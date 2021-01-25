import controller.cli.commands.CommandMain;

public class MainCLI {
    public static void main(String[] args) {
        try {
            new CommandMain().run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
