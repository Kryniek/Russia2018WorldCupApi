package pl.cup.russia.api.Russia2018Api.controller.handler;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

import static java.util.Objects.isNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pl.cup.russia.api.Russia2018Api.dto.rest.RestErrorResponse;
import pl.cup.russia.api.Russia2018Api.util.exception.base.Russia2018CupApiException;

@RestController
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Russia2018CupApiException.class)
	public RestErrorResponse handleApplicationException(Russia2018CupApiException exception, WebRequest webRequest) {
		HttpStatus httpStatus = resolveAnnotatedHttpStatus(exception);

		exception.printStackTrace();

		return new RestErrorResponse(exception, webRequest.getDescription(false), httpStatus);
	}

	private HttpStatus resolveAnnotatedHttpStatus(RuntimeException exception) {
		ResponseStatus annotation = findMergedAnnotation(exception.getClass(), ResponseStatus.class);

		if (isNull(annotation)) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return annotation.value();
	}
}
