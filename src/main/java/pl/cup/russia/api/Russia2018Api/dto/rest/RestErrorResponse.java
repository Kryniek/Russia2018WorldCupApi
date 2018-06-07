package pl.cup.russia.api.Russia2018Api.dto.rest;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;

import pl.cup.russia.api.Russia2018Api.dto.rest.base.RestResponse;

public class RestErrorResponse extends RestResponse {

	private String errorMessage;
	private String requestURI;
	private String stackTrace;

	public RestErrorResponse(RuntimeException exception, String requestURI, HttpStatus httpStatus) {
		this.setSuccess(false);
		this.setErrorMessage(exception.getMessage());
		this.setRequestURI(requestURI);
		this.setStackTrace(getStackTrace(exception));
		this.setHttpStatus(httpStatus);
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	/*
	 * Copy of org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace.
	 */
	private String getStackTrace(final Throwable throwable) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);

		return sw.getBuffer().toString();
	}
}
