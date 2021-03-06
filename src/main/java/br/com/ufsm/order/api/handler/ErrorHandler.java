package br.com.ufsm.order.api.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.ufsm.order.api.exceptions.ObjectAlreadyExistsException;
import br.com.ufsm.order.api.exceptions.ObjectNotFoundException;
import br.com.ufsm.order.api.model.Error;
import feign.FeignException;

@RestControllerAdvice
public class ErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Error handleGenericException(Exception ex) {
		return new Error("InternalError", 500, ex.getClass().getName() + ": " + ex.getMessage());
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeignException.class)
	public String handleFeignException(FeignException ex) {
		return ex.contentUTF8();
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	public Error handleObjectNotFoundException(ObjectNotFoundException ex) {
		return new Error("ObjectNotFound", HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ObjectAlreadyExistsException.class)
	public Error handleObjectAlreadyExistsException(ObjectAlreadyExistsException ex) {
		return new Error("ObjectAlreadyExists", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<Error> handle(MethodArgumentNotValidException exception) {
		List<Error> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			Error erro = new Error("ValidationError", HttpStatus.BAD_REQUEST.value(),
					e.getField().toUpperCase() + " " + mensagem);
			dto.add(erro);
		});

		return dto;
	}

}
