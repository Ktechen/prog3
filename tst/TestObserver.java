import cli.commands.options.CommandAdd;
import data.Storage;
import observer.Capacity;
import observer.observers.ObserverConsoleSize;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;

public class TestObserver {

    @Test
    public void ConsoleSizeThrowDoesNotException() {
        CommandAdd observer = Mockito.mock(CommandAdd.class);
        Mockito.when(observer.getCapacity()).thenReturn(BigDecimal.valueOf(300000));

        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(observer);

        try {
            observerConsoleSize.update();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ConsoleSizeGetSystemOutMessage() {

        //https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outContent));

        CommandAdd observer = Mockito.mock(CommandAdd.class);
        Mockito.when(observer.getCapacity()).thenReturn(BigDecimal.valueOf(3000 * 3000));
        ObserverConsoleSize observerConsoleSize = new ObserverConsoleSize(observer);
        observerConsoleSize.update();
        Assertions.assertEquals("Die Capacity von 90 % wurde überschritten", outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    public void CautionOfOverload101() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Capacity(BigDecimal.valueOf(101), BigDecimal.valueOf(0));
        });
    }

    @Test
    public void CautionOfOverloadMinus() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Capacity(BigDecimal.valueOf(-1), BigDecimal.valueOf(0));
        });
    }

    @Test
    public void CautionOfOverload() {
        Capacity capacity = new Capacity(Storage.MAX_SIZE_OF_FILE, BigDecimal.valueOf(90), BigDecimal.valueOf(200));
        boolean value = capacity.cautionOfOverLoad();

        Assertions.assertFalse(value);
    }

    @Test
    public void CautionOfOverLoadFalse() {
        Capacity capacity = new Capacity(BigDecimal.valueOf(90), BigDecimal.valueOf(0));
        boolean test = capacity.cautionOfOverLoad();

        Assertions.assertFalse(test);
    }

    @Test
    public void CautionOfOverLoadMaxSize() {
        Capacity capacity = new Capacity(BigDecimal.valueOf(90), BigDecimal.valueOf(2000 * 2000));
        boolean test = capacity.cautionOfOverLoad();

        Assertions.assertTrue(test);
    }

    @Test
    public void CaluProcent() {
        Capacity capacity = new Capacity();
        BigDecimal value = capacity.procentCalu(Storage.MAX_SIZE_OF_FILE, BigDecimal.valueOf(90));
        Assertions.assertEquals(BigDecimal.valueOf(450000), value);
    }
}
