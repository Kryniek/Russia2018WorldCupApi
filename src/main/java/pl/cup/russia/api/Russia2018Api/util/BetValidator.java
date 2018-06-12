package pl.cup.russia.api.Russia2018Api.util;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;

public class BetValidator {

    private static final LocalDateTime WORLD_CUP_START = of(2018, 06,14, 17, 0);

    public static Boolean canBetWorldCupWinner() {
        if (now().isBefore(WORLD_CUP_START))
            return true;

        return false;
    }

}
