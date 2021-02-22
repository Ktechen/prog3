package controller.crud;


public class Validated {


    public static final String BITRATE_MSG = "Bitrate number is smaller then 0";
    public static final String WIDTH_MSG = "Width number is smaller then 0";
    public static final String HEIGHT_MSG = "Height number is smaller then 0";
    public static final String SAMPLING_MSG = "SamplingRate number is smaller then 0";
    public static final String ENCODING_MSG = "Encoding is null";
    public static final String TYPE_MSG = "Type is null";
    public static final String HOLDER_MSG = "HOLDER is null";

    void isVideoValid(int width, int height, long bitrate, String encoding) throws NullPointerException, NumberFormatException {
        this.isStringNotNull(encoding, ENCODING_MSG);
        this.isLongBiggerThanZero(bitrate, BITRATE_MSG);
        this.isIntegerBiggerThanZero(width, WIDTH_MSG);
        this.isIntegerBiggerThanZero(height, HEIGHT_MSG);

    }

    void isAudioValid(long bitrate, int samplingRate, String encoding) {
        this.isLongBiggerThanZero(bitrate, BITRATE_MSG);
        this.isIntegerBiggerThanZero(samplingRate, SAMPLING_MSG);
        this.isStringNotNull(encoding, ENCODING_MSG);
    }

    void isLongBiggerThanZero(long number, String errorMsg) {
        if (number < 0) {
            throw new NumberFormatException(errorMsg);
        }
    }

    void isStringNotNull(String s, String errorMsg) throws NullPointerException {
        if (s == null) {
            throw new NullPointerException(errorMsg);
        }
    }

    void isIntegerBiggerThanZero(int number, String errorMsg) throws NumberFormatException {
        if (number < 0) {
            throw new NumberFormatException(errorMsg);
        }
    }

    void isObjectNotNull(Object o, String errorMsg) throws NullPointerException {
        if (o == null) {
            throw new NullPointerException(errorMsg);
        }
    }

}
