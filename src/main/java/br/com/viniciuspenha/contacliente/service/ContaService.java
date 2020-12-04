package br.com.viniciuspenha.contacliente.service;

import br.com.viniciuspenha.contacliente.exception.ClienteNotFoundException;
import br.com.viniciuspenha.contacliente.exception.ContaNotFoundException;
import br.com.viniciuspenha.contacliente.model.dto.conta.AdicionaSaldo;
import br.com.viniciuspenha.contacliente.model.dto.conta.CadastroContaDTO;
import br.com.viniciuspenha.contacliente.model.entity.Cliente;
import br.com.viniciuspenha.contacliente.model.entity.Conta;
import br.com.viniciuspenha.contacliente.model.mapper.ContaMapper;
import br.com.viniciuspenha.contacliente.repository.ClienteRepository;
import br.com.viniciuspenha.contacliente.repository.ContaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Slf4j
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
        log.info("ContaService.novaConta - conta {}", cadastroContaDTO);
        Cliente cliente = clienteRepository.findById(cadastroContaDTO.getClienteId())
                .orElseThrow(ClienteNotFoundException::new);
        return contaRepository.save(contaMapper.toNovaConta(cliente, this.geraNumeroConta()));
    }

    private String geraNumeroConta() {
        Random random = new Random();
        return String.valueOf(random.nextInt(NUMERO_CONTA_MAX));
    }

    @Transactional(readOnly = true)
    public Conta buscaPorNumeroDaConta(String numeroConta) throws ContaNotFoundException {
        log.info("ContaService.buscaPorNumeroDaConta - numeroConta {}", numeroConta);
        return contaRepository.findByNumero(numeroConta)
                .orElseThrow(ContaNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Conta buscaPorId(Long id) throws ContaNotFoundException {
        log.info("ContaService.buscaPorId - id {}", id);
        return contaRepository.findById(id)
                .orElseThrow(ContaNotFoundException::new);
    }

    public void adicionaSaldo(AdicionaSaldo adicionaSaldo) throws ContaNotFoundException {
        log.info("ContaService.adicionaSaldo - adiona saldo {}", adicionaSaldo);
        Conta conta = contaRepository.findById(adicionaSaldo.getContaId()).orElseThrow(ContaNotFoundException::new);
        conta.adicionaSaldo(adicionaSaldo.getValor());
        contaRepository.save(conta);
    }

    public List<Conta> listaContas() {
        log.info("ContaService.listaContas");
        return contaRepository.findAll();
    }
}