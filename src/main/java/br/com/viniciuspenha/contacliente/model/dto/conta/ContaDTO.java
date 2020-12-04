package br.com.viniciuspenha.contacliente.model.dto.conta;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ContaDTO {

    private Long contaId;
    private Long clienteId;
    private String numero;
    private BigDecimal saldo;
}