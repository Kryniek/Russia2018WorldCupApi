package pl.cup.russia.api.Russia2018Api.external.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import pl.cup.russia.api.Russia2018Api.external.api.definition.FootballApiService;
import pl.cup.russia.api.Russia2018Api.external.api.enums.FootballApiAction;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiLeague;
import pl.cup.russia.api.Russia2018Api.external.api.util.builder.FootballApiUrlBuilder;

import java.util.List;

@Service
public class FootballApiServiceImpl implements FootballApiService {

    @Autowired
    private RestTemplate restTemplate;

	@Override
	public List<ApiLeague> getApiLeagues() {
        String url = new FootballApiUrlBuilder(FootballApiAction.GET_LEAGUES).withCountryId().build();

        // move it to some util class with <T> type and parametrization
        ResponseEntity<List<ApiLeague>> re = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ApiLeague>>() {});
        List<ApiLeague> leagues = re.getBody();

		return leagues;
	}

}