package pl.cup.russia.api.Russia2018Api.util.exception.base;

@SuppressWarnings("serial")
public abstract class ApplicationException extends RuntimeException {

	public ApplicationException(String message) {
		super(message);
	}
}
