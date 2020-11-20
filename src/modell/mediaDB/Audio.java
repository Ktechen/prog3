package modell.mediaDB;

public interface Audio extends MediaContent, Uploadable {
    int getSamplingRate();

    String getEncoding();
}
