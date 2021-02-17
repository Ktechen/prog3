package controller.observer;

import modell.data.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestCapacity {

    @Test
    public void testCapacityProcentOver101() {
        final Capacity capacity = new Capacity();

        try {
            capacity.procentCalu(BigDecimal.valueOf(3000), BigDecimal.valueOf(101));
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void testCapacityProcentOverNegative() {
        final Capacity capacity = new Capacity();

        try {
            capacity.procentCalu(BigDecimal.valueOf(3000), BigDecimal.valueOf(-1203));
        } catch (IllegalArgumentException e) {
            Assertions.assertTrue(true);
        }
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
