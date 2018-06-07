package pl.cup.russia.api.Russia2018Api.dto.rest;

import org.springframework.http.HttpStatus;

import pl.cup.russia.api.Russia2018Api.dto.rest.base.RestResponse;

public class RestSuccessResponse extends RestResponse {

	public RestSuccessResponse() {
		this.setSuccess(true);
		this.setHttpStatus(HttpStatus.OK);
	}
}
