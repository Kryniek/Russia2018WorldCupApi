package pl.cup.russia.api.Russia2018Api.external.api.enums;

public enum FootballApiAction {
	// @formatter:off
	GET_LEAGUES("get_leagues"),
	GET_STANDINGS("get_standings"),
	GET_EVENTS("get_events");
	// @formatter:on

	private String value;

	FootballApiAction(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
