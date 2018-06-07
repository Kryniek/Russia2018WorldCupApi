package pl.cup.russia.api.Russia2018Api.external.api.service;

import static pl.cup.russia.api.Russia2018Api.external.api.util.HttpConnectionUtil.getJSONArrayFromConnectionByUrl;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import pl.cup.russia.api.Russia2018Api.external.api.definition.FootballApiService;
import pl.cup.russia.api.Russia2018Api.external.api.enums.FootballApiAction;
import pl.cup.russia.api.Russia2018Api.external.api.util.builder.FootballApiUrlBuilder;

@Service
public class FootballApiServiceImpl implements FootballApiService {

	@Override
	public JSONArray getLeaguesJSONArray() {
		return getJSONArrayFromConnectionByUrl(new FootballApiUrlBuilder(FootballApiAction.GET_LEAGUES).withCountryId().build());
	}
}