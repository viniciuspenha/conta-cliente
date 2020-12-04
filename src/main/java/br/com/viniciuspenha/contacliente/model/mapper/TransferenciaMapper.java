package br.com.viniciuspenha.contacliente.model.mapper;

import br.com.viniciuspenha.contacliente.model.dto.transferencia.TransferenciaResponse;
import br.com.viniciuspenha.contacliente.model.entity.transferencia.Transferencia;
import org.springframework.stereotype.Component;

@Component
public class TransferenciaMapper {

    public Transferencia toTransferencia(TransferenciaResponse transferenciaResponse) {
        return Transferencia.builder()
                .contaDestino(transferenciaResponse.getContaDestino())
                .contaOrigem(transferenciaResponse.getContaOrigem())
                .numeroContaDestino(transferenciaResponse.getNumeroContaDestino())
                .valor(transferenciaResponse.getValor())
                .status(transferenciaResponse.getStatus())
                .erro(transferenciaResponse.getErro())
                .build();
    }

    public TransferenciaResponse toTransferenciaResponse(Transferencia transferencia) {
        return new TransferenciaResponse(
                transferencia.getId(),
                transferencia.getContaOrigem(),
                transferencia.getContaDestino(),
                transferencia.getNumeroContaDestino(),
                transferencia.getValor(),
                transferencia.getStatus(),
                transferencia.getErro(),
                transferencia.getDataCriacao()
        );
    }
}