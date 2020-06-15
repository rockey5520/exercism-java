import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gigasecond {

    private final LocalDateTime localDateTime;
    public Gigasecond(LocalDate moment) {
        localDateTime = moment.atTime(0,0);
    }

    public Gigasecond(LocalDateTime moment) {
        localDateTime = moment;
    }

    public LocalDateTime getDateTime() {
        return localDateTime.plusSeconds(1000000000);
    }
}
