package pl.cup.russia.api.Russia2018Api.external.api.util.exception.service.footballApiService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.cup.russia.api.Russia2018Api.enums.ErrorMessage;
import pl.cup.russia.api.Russia2018Api.external.api.util.exception.service.footballApiService.base.FootballApiServiceApplicationException;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UrlParseException extends FootballApiServiceApplicationException {

	public UrlParseException() {
		super(ErrorMessage.URL_PARSE_EXCEPTION);
	}
}
