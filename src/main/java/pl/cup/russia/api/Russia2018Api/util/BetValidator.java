package pl.cup.russia.api.Russia2018Api.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;

public class BetValidator {

    private static final LocalDateTime START_OF_SECOND_ROUND_OF_GROUP_STAGE =
            of(2018, 06,19, 20, 0);

    public static Boolean canBetWorldCupWinnerAndGroupWinners() {
        if (now().isBefore(START_OF_SECOND_ROUND_OF_GROUP_STAGE))
            return true;

        return false;
    }

    public static Boolean canBetMatch(LocalDate date, LocalTime time) {
        LocalDateTime matchDateTime = LocalDateTime.of(date, time);
        if (now().isBefore(matchDateTime))
            return true;

        return false;
    }

}
