package controller.crud;

import modell.data.storage.Storage;

import java.math.BigDecimal;


public class Validierung {

    private static BigDecimal limit;

    /**
     * This method is check a Size of Limit
     * @param limit = setLimit
     * @throws IllegalArgumentException = If size too high then throw an exception
     */
    public static void checkSize(BigDecimal limit) throws IllegalArgumentException {
        if (Storage.MAX_SIZE_OF_FILE.compareTo(limit) < 0) {
            throw new IllegalArgumentException("Speicher Limit");
        }
    }

}
