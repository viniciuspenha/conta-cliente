package br.com.viniciuspenha.contacliente.model.dto.transferencia;

import br.com.viniciuspenha.contacliente.model.entity.transferencia.TransferenciaErro;
import br.com.viniciuspenha.contacliente.model.entity.transferencia.TransferenciaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class TransferenciaResponse {

    private Long id;
    private Long contaOrigem;
    private Long contaDestino;
    private String numeroContaDestino;
    private BigDecimal valor;
    private TransferenciaStatus status;
    private TransferenciaErro erro;
    private LocalDateTime data;
}