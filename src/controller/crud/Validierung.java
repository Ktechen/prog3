package controller.crud;

import modell.data.storage.Storage;

import java.math.BigDecimal;


public class Validierung {

    /**
     * This method is check a Size of Limit
     *
     * @param limit = setLimit
     * @throws IllegalArgumentException = If size too high then throw an exception
     */
    void checkSize(BigDecimal limit) throws IllegalArgumentException {
        if (Storage.MAX_SIZE_OF_FILE.compareTo(limit) < 0) {
            throw new IllegalArgumentException("Speicher Limit");
        }
    }

    void isVideoValid(int width, int height, long bitrate, String encoding) throws NullPointerException, NumberFormatException {
        this.isStringNotNull(encoding);
        this.isLongBiggerThanZero(bitrate);
        this.isIntegerBiggerThanZero(width);
        this.isIntegerBiggerThanZero(height);

    }

    void isAudioValid(long bitrate, int samplingRate, String encoding) {
        this.isLongBiggerThanZero(bitrate);
        this.isIntegerBiggerThanZero(samplingRate);
        this.isStringNotNull(encoding);
    }

    void isLongBiggerThanZero(long number) {
        if (number < 0) {
            throw new NumberFormatException("Long number is smaller then 0");
        }
    }

    void isStringNotNull(String s) throws NullPointerException {
        if (s == null) {
            throw new NullPointerException("String is null");
        }
    }

    void isIntegerBiggerThanZero(int number) throws NumberFormatException {
        if (number < 0) {
            throw new NumberFormatException("Integer number is smaller then 0");
        }
    }

}
