package pl.cup.russia.api.Russia2018Api.external.api.util.exception.base;

import pl.cup.russia.api.Russia2018Api.util.exception.base.ApplicationException;

@SuppressWarnings("serial")
public abstract class ExternalApiApplicationException extends ApplicationException {

	public ExternalApiApplicationException(String message) {
		super(message);
	}
}
