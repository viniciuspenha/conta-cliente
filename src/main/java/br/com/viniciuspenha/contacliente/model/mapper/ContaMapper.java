package br.com.viniciuspenha.contacliente.model.mapper;

import br.com.viniciuspenha.contacliente.model.dto.conta.ContaDTO;
import br.com.viniciuspenha.contacliente.model.entity.Cliente;
import br.com.viniciuspenha.contacliente.model.entity.Conta;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ContaMapper {

    public ContaDTO toContaDTO(Conta conta) {
        return new ContaDTO(conta.getId(), conta.getCliente().getId(), conta.getNumero(), conta.getSaldo());
    }

    public Conta toNovaConta(Cliente cliente, String numeroConta) {
        return Conta.builder()
                .cliente(cliente)
                .numero(numeroConta)
                .saldo(BigDecimal.ZERO)
                .build();
    }
}