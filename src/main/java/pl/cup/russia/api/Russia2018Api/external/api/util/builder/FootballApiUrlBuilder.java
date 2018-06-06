package pl.cup.russia.api.Russia2018Api.external.api.util.builder;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;

import pl.cup.russia.api.Russia2018Api.external.api.constants.ApiConstants;
import pl.cup.russia.api.Russia2018Api.external.api.enums.FootballApiAction;

public class FootballApiUrlBuilder {
	private static final String BASIC_URL = "https://apifootball.com/api/";
	private static final String API_KEY = "d49fc0bf81b31ef39c4573562e19475e4dfefaaed63100efd1fe7c972588059a";

	private FootballApiAction action;
	private List<String> urlParameters = new ArrayList<String>();

	public FootballApiUrlBuilder(FootballApiAction action) {
		this.action = action;
	}

	public FootballApiUrlBuilder withCountryId() {
		this.urlParameters.add("country_id=" + ApiConstants.WORLD_CUP_COUNTRY_ID.intValue());

		return this;
	}

	public String build() {
		StringBuilder stringBuilder = new StringBuilder(BASIC_URL);
		stringBuilder.append("?");
		stringBuilder.append("action=");
		stringBuilder.append(action.getValue());
		stringBuilder.append("&");

		if (isEmpty(urlParameters)) {
			stringBuilder.append("&");
		} else {
			urlParameters.forEach(urlParameter -> stringBuilder.append(urlParameter).append("&"));
		}

		stringBuilder.append("APIkey=");
		stringBuilder.append(API_KEY);

		return stringBuilder.toString();
	}
}
