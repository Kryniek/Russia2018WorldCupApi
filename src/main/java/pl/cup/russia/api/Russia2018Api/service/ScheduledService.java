package pl.cup.russia.api.Russia2018Api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.cup.russia.api.Russia2018Api.definition.BetService;
import pl.cup.russia.api.Russia2018Api.definition.MatchService;

@Component
public class ScheduledService {

    @Autowired
    private BetService betService;

    @Autowired
    private MatchService matchService;

    @Scheduled(cron = "0 0/1 14-22 * * *")
    public void scheduleMatchSync() {
        matchService.updateTodayMatchesResults();
    }

    @Scheduled(cron = "0 25 15,16,18,19,22 * * *")
    public void scheduleBetSettlement() {
        System.out.println("Odpalam się 3 razy dziennie po meczach");
//        betService.calculatePointsAndUpdateBetRecords();
    }

}
