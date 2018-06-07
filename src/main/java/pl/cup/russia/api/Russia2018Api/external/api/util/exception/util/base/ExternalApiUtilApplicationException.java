package pl.cup.russia.api.Russia2018Api.external.api.util.exception.util.base;

import pl.cup.russia.api.Russia2018Api.enums.ErrorMessage;
import pl.cup.russia.api.Russia2018Api.external.api.util.exception.base.ExternalApiApplicationException;

@SuppressWarnings("serial")
public abstract class ExternalApiUtilApplicationException extends ExternalApiApplicationException {

	public ExternalApiUtilApplicationException(ErrorMessage errorMessage) {
		super(errorMessage);
	}
}
