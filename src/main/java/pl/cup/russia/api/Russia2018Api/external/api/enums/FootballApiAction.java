package pl.cup.russia.api.Russia2018Api.external.api.enums;

public enum FootballApiAction {
	// @formatter:off
	GET_LEAGUES("get_leagues");
	// @formatter:on

	private String value;

	private FootballApiAction(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
