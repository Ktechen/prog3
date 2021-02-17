package controller.observer;

import modell.data.storage.Storage;

import java.math.BigDecimal;

public class Capacity {

    private BigDecimal max;
    private BigDecimal procent;
    private BigDecimal checkValue;

    /**
     * (2000height * 2000 weight) / 8 = 500.000 = byte
     * 500.000 / 1024 = 488,28125 =  Kibibyte
     * (10 x 488,28125 = 4.882,8125 10 Medias)
     * <p>
     * 500.000      x
     * -------  --------
     * 100%        90%
     * <p>
     * x = 450.000
     */
    public static final BigDecimal valueOf90 = BigDecimal.valueOf(450000);

    private BigDecimal capacity;

    public BigDecimal getMax() {
        return max;
    }

    public BigDecimal getCheckValue() {
        return checkValue;
    }

    public Capacity() {
    }

    public Capacity(BigDecimal max, BigDecimal procent, BigDecimal checkValue) {

        if (procent.compareTo(BigDecimal.valueOf(0)) < 0 || procent.compareTo(procent) > 0) {
            throw new IllegalArgumentException("Value is not Procent");
        }

        this.max = max;
        this.procent = procent;
        this.checkValue = checkValue;
    }

    public Capacity(BigDecimal procent, BigDecimal checkValue) {

        if (procent.compareTo(BigDecimal.valueOf(0)) < 0 || procent.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Value is not Procent");
        }

        this.procent = procent;
        this.checkValue = checkValue;
        this.max = Storage.MAX_SIZE_OF_FILE;
    }

    /**
     * Check if procent exceeded to Size
     *
     * @return if number over Procent of value = true
     * @throws IllegalArgumentException
     */
    public boolean cautionOfOverLoad() throws IllegalArgumentException {

        BigDecimal basicValue = this.checkValue;
        BigDecimal divBy8 = basicValue.divide(BigDecimal.valueOf(8));
        BigDecimal divBy100 = divBy8.divide(BigDecimal.valueOf(100));
        BigDecimal multiProc = divBy100.multiply(this.procent);

        this.capacity = multiProc;

        if (multiProc.equals(valueOf90)) {
            return true;
        }

        return multiProc.compareTo(valueOf90) > 0;
    }


    @Deprecated
    public BigDecimal procentCalu(BigDecimal size, BigDecimal procent) {
        if (procent.compareTo(BigDecimal.valueOf(0)) < 0 || procent.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Value is not Procent");
        }

        return procent.multiply(size).divide(BigDecimal.valueOf(100));
    }

    @Deprecated
    public BigDecimal convertToByte(BigDecimal number) {
        return number.divide(BigDecimal.valueOf(8));
    }

}
