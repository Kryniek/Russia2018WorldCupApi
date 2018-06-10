package pl.cup.russia.api.Russia2018Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cup.russia.api.Russia2018Api.definition.LeagueService;
import pl.cup.russia.api.Russia2018Api.model.League;

import java.util.List;

@RestController
@RequestMapping("/leagues")
public class LeagueController {

    @Autowired
    private LeagueService service;

    @GetMapping
    public List<League> getLeagues() {
        return service.selectLeagues();
    }

    @GetMapping("/teams")
    public List<String> getAllTeams() {
        return service.selectAllTeams();
    }

    @GetMapping("/{leagueId}/teams")
    public List<String> getTeamsInLeague(@PathVariable Integer leagueId) {
        return service.selectTeamsByLeagueId(leagueId);
    }

}
