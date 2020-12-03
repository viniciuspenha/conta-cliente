package br.com.viniciuspenha.contacliente.service;

import br.com.viniciuspenha.contacliente.exception.ClienteNotFoundException;
import br.com.viniciuspenha.contacliente.model.dto.conta.CadastroContaDTO;
import br.com.viniciuspenha.contacliente.model.entity.Cliente;
import br.com.viniciuspenha.contacliente.model.entity.Conta;
import br.com.viniciuspenha.contacliente.model.mapper.ContaMapper;
import br.com.viniciuspenha.contacliente.repository.ClienteRepository;
import br.com.viniciuspenha.contacliente.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ContaService {

    private static final int NUMERO_CONTA_MAX = 99999;

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;
    private final ContaMapper contaMapper;

    @Autowired
    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository,
                        ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.contaMapper = contaMapper;
    }

    public Conta novaConta(CadastroContaDTO cadastroContaDTO) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(cadastroContaDTO.getClienteId())
                .orElseThrow(ClienteNotFoundException::new);
        return contaRepository.save(contaMapper.toNovaConta(cliente, this.geraNumeroConta()));
    }

    private String geraNumeroConta() {
        Random random = new Random();
        return String.valueOf(random.nextInt(NUMERO_CONTA_MAX));
    }
}