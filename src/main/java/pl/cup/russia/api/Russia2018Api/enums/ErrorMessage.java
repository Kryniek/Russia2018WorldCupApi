package pl.cup.russia.api.Russia2018Api.enums;

public enum ErrorMessage {
	// @formatter:off
	URL_PARSE("Exception occurred during URL object parsing."),
	CONNECTION_STATUS_CODE_NOT_OK("Connection status code is different than 200."),
	JSON_PARSING("Exception occurred during JSON parsing.");
	// @formatter:on

	private String value;

	private ErrorMessage(String value) {
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
