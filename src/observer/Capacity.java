package observer;

import data.Storage;
import data.StorageAsSingelton;

import java.math.BigDecimal;

public class Capacity {

    private BigDecimal max;
    private BigDecimal procent;
    private BigDecimal checkValue;
    private Storage storage;

    private BigDecimal result;

    public BigDecimal getMax() {
        return max;
    }

    public BigDecimal getProcent() {
        return procent;
    }

    public BigDecimal getCheckValue() {
        return checkValue;
    }

    public BigDecimal getCapacity() {
        return result;
    }

    public Capacity() {
        this.storage = StorageAsSingelton.getInstance();
    }

    public Capacity(BigDecimal max, BigDecimal procent, BigDecimal checkValue) {

        if (procent.compareTo(BigDecimal.valueOf(0)) < 0 || procent.compareTo(BigDecimal.valueOf(100)) > 0) {
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

     * @return if number over Procent of value
     * @throws IllegalArgumentException
     */
    public boolean cautionOfOverLoad() throws IllegalArgumentException {
        checkValue = convertToByte(checkValue);
        result = procentCalu(max, procent);

        //Unter x% e.g 90%
        return checkValue.compareTo(result) > 0;
    }

    public BigDecimal procentCalu(BigDecimal size, BigDecimal procent) {
        if (procent.compareTo(BigDecimal.valueOf(0)) < 0 || procent.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Value is not Procent");
        }

        return procent.multiply(size).divide(BigDecimal.valueOf(100));
    }

    public BigDecimal convertToByte(BigDecimal number) {
        return number.divide(BigDecimal.valueOf(8));
    }

}
