package br.com.viniciuspenha.contacliente.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiError {

    private int status;
    private String error;
    private String mensagem;
    private String path;
    private List<ApiFieldError> fieldErrors;
}