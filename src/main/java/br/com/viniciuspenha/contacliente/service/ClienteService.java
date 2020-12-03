package br.com.viniciuspenha.contacliente.service;

import br.com.viniciuspenha.contacliente.exception.ContaNotFoundException;
import br.com.viniciuspenha.contacliente.model.dto.cliente.CadastroClienteDTO;
import br.com.viniciuspenha.contacliente.model.entity.Cliente;
import br.com.viniciuspenha.contacliente.model.entity.Conta;
import br.com.viniciuspenha.contacliente.model.mapper.ClienteMapper;
import br.com.viniciuspenha.contacliente.repository.ClienteRepository;
import br.com.viniciuspenha.contacliente.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ContaRepository contaRepository,
                          ClienteMapper clienteMapper) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public Cliente novoCliente(CadastroClienteDTO cadastroClienteDTO) {
        return clienteRepository.save(clienteMapper.toCliente(cadastroClienteDTO));
    }

    public List<Cliente> listaClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscaPorNumeroDaConta(String numeroConta) throws ContaNotFoundException {
        Conta conta = contaRepository.findByNumero(numeroConta)
                .orElseThrow(ContaNotFoundException::new);
        return conta.getCliente();
    }
}