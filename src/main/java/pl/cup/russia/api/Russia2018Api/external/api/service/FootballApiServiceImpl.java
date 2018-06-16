package pl.cup.russia.api.Russia2018Api.external.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.cup.russia.api.Russia2018Api.external.api.definition.FootballApiService;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiEvent;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiLeague;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiStanding;
import pl.cup.russia.api.Russia2018Api.external.api.util.builder.FootballApiUrlBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static pl.cup.russia.api.Russia2018Api.external.api.constants.ApiConstants.FINISH_OF_GROUP_STAGE;
import static pl.cup.russia.api.Russia2018Api.external.api.enums.FootballApiAction.*;

@Service
public class FootballApiServiceImpl implements FootballApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<ApiLeague> getApiLeagues() {
        String url = new FootballApiUrlBuilder(GET_LEAGUES).withCountryId().build();

        // move it to some util class with <T> type and parametrization
        ResponseEntity<List<ApiLeague>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ApiLeague>>() {
                });
        List<ApiLeague> leagues = responseEntity.getBody();

        return leagues;
    }

    @Override
    public List<ApiStanding> getApiStandingsByLeagueId(Integer leagueId) {
        String url = new FootballApiUrlBuilder(GET_STANDINGS).withLeagueId(leagueId).build();
        ResponseEntity<List<ApiStanding>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ApiStanding>>() {
                });

        return responseEntity.getBody();
    }

    // this methods would look different but have to test it a bit
    @Override
    public List<ApiStanding> getApiStandings() {
        // should take it from our database to avoid api calling
        List<Integer> leagueIds = getApiLeagues().stream().map(ApiLeague::getLeagueId).collect(toList());

        List<String> urls = new ArrayList<>();
        for (Integer id : leagueIds)
            urls.add(new FootballApiUrlBuilder(GET_STANDINGS).withLeagueId(id).build());

        List<ApiStanding> standings = new ArrayList<>();
        for (String url : urls) {
            ResponseEntity<List<ApiStanding>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<ApiStanding>>() {
                    });
            standings.addAll(responseEntity.getBody());
        }

        return standings;
    }

    @Override
    public List<ApiEvent> getApiEventsByLeagueId(Integer leagueId) {
        // TODO: cause dates are mandatory it would be by league - if isGroup() return true will be from beginning of WC
        // TODO: to the end of group stage (consts), else it will be from beginning of playoff stage to final
        String url = new FootballApiUrlBuilder(GET_EVENTS).withLeagueId(leagueId)
                .fromDate(now()).toDate(FINISH_OF_GROUP_STAGE).build();

        ResponseEntity<List<ApiEvent>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ApiEvent>>() {
                });

        return responseEntity.getBody();
    }

    @Override
    public List<ApiEvent> getApiEvents() {
        // should be from database
        List<Integer> leagueIds = getApiLeagues().stream().map(ApiLeague::getLeagueId).collect(toList());

        List<String> urls = new ArrayList<>();
        for (Integer id : leagueIds) {
            urls.add(new FootballApiUrlBuilder(GET_EVENTS).withLeagueId(id).fromDate(now())
                    .toDate(FINISH_OF_GROUP_STAGE).build());
        }

        List<ApiEvent> events = new ArrayList<>();
        for (String url : urls) {
            ResponseEntity<List<ApiEvent>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<ApiEvent>>() {
            });

            events.addAll(responseEntity.getBody());
        }

        Map<Integer, List<ApiEvent>> eventsByLeagueId = events.stream().collect(groupingBy(ApiEvent::getLeagueId));

        return events;
    }

    @Override
    public List<ApiEvent> getTodayApiEvents() {
        String url = new FootballApiUrlBuilder(GET_EVENTS).withCountryId().fromDate(now()).toDate(now()).build();

        ResponseEntity<List<ApiEvent>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ApiEvent>>() {});

        return responseEntity.getBody();
    }

}