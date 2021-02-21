import controller.cli.Console;
import controller.gui.delegate.view.ActionDebug;
import controller.handleInput.InputConverter;
import net.StartServer;
import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigDecimal;

public class MainServer {
    public static void main(String[] args) {

        /**
         * Set Default elemente
         */
        new ActionDebug().setupOne(null);

        while (true) {
            System.out.println(InputConverter.TCP_SERVER_TEXT);
            final Console console = new Console();
            String protocol = console.input(InputConverter.TCP_PROTOCOL_TEXT);
            String capacity = console.input(InputConverter.TCP_STORAGE_CAPACITY_TEXT);
            double valueOfCapacity = Double.parseDouble(capacity);
            StartServer startServer = new StartServer(protocol, BigDecimal.valueOf(valueOfCapacity));
            startServer.run();
        }
    }
}
