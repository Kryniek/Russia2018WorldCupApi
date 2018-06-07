package pl.cup.russia.api.Russia2018Api.external.api.definition;

import org.json.JSONArray;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiLeague;

import java.util.List;

public interface FootballApiService {
	JSONArray getLeaguesJSONArray();

	List<ApiLeague> getApiLeagues();

}
