package pl.cup.russia.api.Russia2018Api.enums;

public enum StaticHtmlResource {
	// @formatter:off
	LOGIN("login"),
	REGISTER("register"),
	WORLD_CUP_WINNER("worldCupWinner"),
	GROUPS_WINNERS("groupsWinners"),
	MATCHES("matches"),
	HOME("home");
	//@formatter:on

	private String value;

	private StaticHtmlResource(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}