package br.com.viniciuspenha.contacliente.model.dto.conta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {

    private Long contaId;
    private Long clienteId;
    private String numero;
    private BigDecimal saldo;
}