package pl.cup.russia.api.Russia2018Api.external.api.util.exception.service.footballApiService.base;

import pl.cup.russia.api.Russia2018Api.external.api.util.exception.base.ExternalApiApplicationException;

@SuppressWarnings("serial")
public abstract class FootballApiServiceApplicationException extends ExternalApiApplicationException {

	public FootballApiServiceApplicationException(String message) {
		super(message);
	}
}
