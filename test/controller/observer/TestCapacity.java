package controller.observer;

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
}
