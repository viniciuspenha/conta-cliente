package br.com.viniciuspenha.contacliente.model.dto.conta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdicionaSaldo {

    @NotNull(message = "contaId nao pode ser nula")
    private Long contaId;

    @NotNull(message = "valor nao pode ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "valor nao pode ser zero")
    private BigDecimal valor;
}