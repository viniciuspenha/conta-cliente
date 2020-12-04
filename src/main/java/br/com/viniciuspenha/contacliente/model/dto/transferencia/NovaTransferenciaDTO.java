package br.com.viniciuspenha.contacliente.model.dto.transferencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovaTransferenciaDTO {

    @NotNull(message = "contaOrigem nao pode ser nula")
    private Long contaOrigem;

    @NotNull(message = "numeroContaDestino nao pode ser nula")
    private String numeroContaDestino;

    @NotNull(message = "valor nao pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "valor nao pode ser zero")
    @DecimalMax(value = "1000.00", message = "valor máximo = 1000.00")
    private BigDecimal valor;
}