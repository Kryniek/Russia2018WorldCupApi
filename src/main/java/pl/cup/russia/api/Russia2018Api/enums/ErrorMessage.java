package pl.cup.russia.api.Russia2018Api.enums;

public enum ErrorMessage {
	// @formatter:off
	URL_PARSE_EXCEPTION("Exception occurred during URL object parsing.");
	// @formatter:on

	private String value;

	private ErrorMessage(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
