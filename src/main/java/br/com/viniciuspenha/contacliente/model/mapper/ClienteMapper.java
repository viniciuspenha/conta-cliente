package br.com.viniciuspenha.contacliente.model.mapper;

import br.com.viniciuspenha.contacliente.model.dto.cliente.CadastroClienteDTO;
import br.com.viniciuspenha.contacliente.model.dto.cliente.ClienteDTO;
import br.com.viniciuspenha.contacliente.model.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toClienteDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getNome());
    }

    public Cliente toCliente(CadastroClienteDTO cadastroClienteDTO) {
        return Cliente.builder()
                .nome(cadastroClienteDTO.getNome())
                .build();
    }
}