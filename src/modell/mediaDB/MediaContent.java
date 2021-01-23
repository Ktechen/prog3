package modell.mediaDB;

import java.math.BigDecimal;
import java.time.Duration;

public interface MediaContent extends Content, Uploadable {
    long getBitrate();

    Duration getLength();

    BigDecimal getSize();
}
