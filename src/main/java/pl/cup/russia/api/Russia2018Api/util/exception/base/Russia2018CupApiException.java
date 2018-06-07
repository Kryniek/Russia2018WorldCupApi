package pl.cup.russia.api.Russia2018Api.util.exception.base;

import pl.cup.russia.api.Russia2018Api.enums.ErrorMessage;

@SuppressWarnings("serial")
public abstract class Russia2018CupApiException extends RuntimeException {

	public Russia2018CupApiException(ErrorMessage errorMessage) {
		super(errorMessage.getValue());
	}
}
