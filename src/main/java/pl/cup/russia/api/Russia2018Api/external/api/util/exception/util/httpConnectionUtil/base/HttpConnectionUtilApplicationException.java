package pl.cup.russia.api.Russia2018Api.external.api.util.exception.util.httpConnectionUtil.base;

import pl.cup.russia.api.Russia2018Api.enums.ErrorMessage;
import pl.cup.russia.api.Russia2018Api.external.api.util.exception.util.base.ExternalApiUtilApplicationException;

@SuppressWarnings("serial")
public abstract class HttpConnectionUtilApplicationException extends ExternalApiUtilApplicationException {

	public HttpConnectionUtilApplicationException(ErrorMessage errorMessage) {
		super(errorMessage);
	}
}
