package pl.cup.russia.api.Russia2018Api.dto.rest;

import pl.cup.russia.api.Russia2018Api.dto.rest.base.RestResponse;

public class ErrorRestResponse extends RestResponse {

	public ErrorRestResponse(String errorMessage, String requestURI) {
		this.setSuccess(false);
		this.setErrorMessage(errorMessage);
		this.setRequestURI(requestURI);
	}
}
