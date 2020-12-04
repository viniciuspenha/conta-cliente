package br.com.viniciuspenha.contacliente.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(basePackages = "br.com.viniciuspenha.contacliente.api")
public class RestControllerException {

    private ApiError getApiError(String mensagem, HttpStatus httpStatus, HttpServletRequest req) {
        return new ApiError.ApiErrorBuilder()
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .mensagem(mensagem)
                .path(req.getRequestURI()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleUnexpectedException(HttpServletRequest req, Throwable ex) {
        log.error("RestControllerException.handleUnexpectedException - " + ex.getMessage());
        return this.getApiError("Um erro inesperado aconteceu", HttpStatus.INTERNAL_SERVER_ERROR, req);
    }

    @ExceptionHandler({ClienteNotFoundException.class, ContaNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(HttpServletRequest req, Throwable ex) {
        log.error("RestControllerException.handleNotFoundException - " + ex.getMessage());
        return this.getApiError(ex.getMessage(), HttpStatus.NOT_FOUND, req);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleSaldoInsuficienteException(HttpServletRequest req, Throwable ex) {
        log.error("RestControllerException.handleSaldoInsuficienteException - " + ex.getMessage());
        return this.getApiError(ex.getMessage(), HttpStatus.BAD_REQUEST, req);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException ex) {
        log.error("RestControllerException.handleMethodArgumentNotValidException - " + ex.getMessage());

        BindingResult bindingResult = ex.getBindingResult();
        List<ApiFieldError> apiFieldErrors = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError ->
                        new ApiFieldError.ApiFieldErrorBuilder()
                                .campo(fieldError.getField())
                                .mensagem(fieldError.getDefaultMessage())
                                .build()
                ).collect(Collectors.toList());

        return new ApiError.ApiErrorBuilder()
                .status(HttpStatus.BAD_REQUEST.value())
                .mensagem("Validation failed")
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .path(req.getRequestURI())
                .fieldErrors(apiFieldErrors)
                .build();
    }
}