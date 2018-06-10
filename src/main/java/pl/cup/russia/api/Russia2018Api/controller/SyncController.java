package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cup.russia.api.Russia2018Api.definition.LeagueService;

@RestController
@RequestMapping("/sync")
public class SyncController {

    // TODO: CONTROLLER TO DELETE AFTER REFACTOR AND TESTS

    @Autowired
    private LeagueService leagueService;

    // WARNING - THIS CONTROLLER ENDPOINT CAN BE USED ONLY ONE TIME FOR NOW WITHOUT CLEARING THE DATABASE AND USE AGAIN
    @GetMapping("/leagues")
    public void syncLeagues() {
        leagueService.syncLeagues();
    }

}
