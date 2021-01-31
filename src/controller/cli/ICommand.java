package controller.cli;


import java.io.IOException;

@Deprecated
public interface ICommand {
    void run() throws IOException, InterruptedException;
    String toString();
}
