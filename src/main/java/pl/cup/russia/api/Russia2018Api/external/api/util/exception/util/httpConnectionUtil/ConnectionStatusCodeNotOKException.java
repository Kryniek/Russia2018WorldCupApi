package pl.cup.russia.api.Russia2018Api.external.api.util.exception.util.httpConnectionUtil;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.cup.russia.api.Russia2018Api.enums.ErrorMessage;
import pl.cup.russia.api.Russia2018Api.external.api.util.exception.util.httpConnectionUtil.base.HttpConnectionUtilApplicationException;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ConnectionStatusCodeNotOKException extends HttpConnectionUtilApplicationException {

	public ConnectionStatusCodeNotOKException() {
		super(ErrorMessage.CONNECTION_STATUS_CODE_NOT_OK);
	}
}
