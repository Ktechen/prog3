package cli.commands;


import java.io.IOException;

public interface ICommand {
    void run() throws IOException;
    String toString();
}
