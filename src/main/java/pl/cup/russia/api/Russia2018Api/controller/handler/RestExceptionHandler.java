package pl.cup.russia.api.Russia2018Api.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pl.cup.russia.api.Russia2018Api.dto.rest.ErrorRestResponse;
import pl.cup.russia.api.Russia2018Api.util.exception.base.Russia2018CupApiException;

@RestController
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	// http://www.springboottutorial.com/spring-boot-exception-handling-for-rest-services

	@ExceptionHandler(Russia2018CupApiException.class)
	public final ResponseEntity<ErrorRestResponse> handleApplicationException(Russia2018CupApiException exception, WebRequest webRequest) {
		ErrorRestResponse errorRestResponse = new ErrorRestResponse(exception.getMessage(), webRequest.getDescription(false));

		return new ResponseEntity<ErrorRestResponse>(errorRestResponse, HttpStatus.NOT_FOUND);
	}

}
