package pl.cup.russia.api.Russia2018Api.util.exception.base;

import pl.cup.russia.api.Russia2018Api.enums.ErrorMessage;

@SuppressWarnings("serial")
public abstract class ApplicationException extends Russia2018CupApiException {

	public ApplicationException(ErrorMessage errorMessage) {
		super(errorMessage);
	}
}