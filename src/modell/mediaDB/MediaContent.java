package modell.mediaDB;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;

public interface MediaContent extends Content, Serializable {
    long getBitrate();

    Duration getLength();

    BigDecimal getSize();
}
