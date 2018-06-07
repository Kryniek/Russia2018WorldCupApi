package pl.cup.russia.api.Russia2018Api.dto.rest.base;

import org.springframework.http.HttpStatus;

public abstract class RestResponse {

	private boolean success;
	private HttpStatus httpStatus;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
