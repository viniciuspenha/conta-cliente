package br.com.viniciuspenha.contacliente.service;

import br.com.viniciuspenha.contacliente.exception.ContaNotFoundException;
import br.com.viniciuspenha.contacliente.exception.SaldoInsuficienteException;
import br.com.viniciuspenha.contacliente.model.dto.transferencia.NovaTransferenciaDTO;
import br.com.viniciuspenha.contacliente.model.dto.transferencia.TransferenciaResponse;
import br.com.viniciuspenha.contacliente.model.entity.Conta;
import br.com.viniciuspenha.contacliente.model.entity.transferencia.Transferencia;
import br.com.viniciuspenha.contacliente.model.entity.transferencia.TransferenciaErro;
import br.com.viniciuspenha.contacliente.model.entity.transferencia.TransferenciaStatus;
import br.com.viniciuspenha.contacliente.model.mapper.TransferenciaMapper;
import br.com.viniciuspenha.contacliente.repository.ContaRepository;
import br.com.viniciuspenha.contacliente.repository.TransferenciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TransferenciaService {

    private final ContaService contaService;
    private final ContaRepository contaRepository;
    private final TransferenciaRepository transferenciaRepository;
    private final TransferenciaMapper transferenciaMapper;

    @Autowired
    public TransferenciaService(ContaService contaService, ContaRepository contaRepository,
                                TransferenciaRepository transferenciaRepository,
                                TransferenciaMapper transferenciaMapper) {
        this.contaService = contaService;
        this.contaRepository = contaRepository;
        this.transferenciaRepository = transferenciaRepository;
        this.transferenciaMapper = transferenciaMapper;
    }

    @Transactional
    public void novaTransferencia(NovaTransferenciaDTO novaTransferenciaDTO) throws SaldoInsuficienteException, ContaNotFoundException {
        log.info("TransferenciaService.novaTransferencia - transferencia {}", novaTransferenciaDTO);
        Long contaDestinoId = null;
        TransferenciaStatus status = TransferenciaStatus.ERRO;
        TransferenciaErro erro = null;
        try {
            Conta contaOrigem = contaService.buscaPorId(novaTransferenciaDTO.getContaOrigem());

            Conta contaDestino = contaService.buscaPorNumeroDaConta(novaTransferenciaDTO.getNumeroContaDestino());
            contaDestinoId = contaDestino.getId();

            this.validaSaldo(contaOrigem, contaDestino, novaTransferenciaDTO);

            contaRepository.save(contaOrigem);
            contaRepository.save(contaDestino);
            status = TransferenciaStatus.EXECUTADA;
        } catch (SaldoInsuficienteException e) {
            erro = TransferenciaErro.SALDO_INSUFICIENTE;
            throw e;
        } catch (ContaNotFoundException e) {
            erro = TransferenciaErro.CONTA_DESTINO_NAO_ENCONTRADA;
            throw e;
        } catch (Exception e) {
            erro = TransferenciaErro.ERRO_INESPERADO;
            throw e;
        } finally {
            saveTransferencia(novaTransferenciaDTO, contaDestinoId, status, erro);
        }
    }

    @Transactional
    protected void saveTransferencia(NovaTransferenciaDTO novaTransferenciaDTO, Long contaDestinoId, TransferenciaStatus status, TransferenciaErro erro) {
        transferenciaRepository.save(transferenciaMapper.toTransferencia(
            TransferenciaResponse.builder()
                    .contaOrigem(novaTransferenciaDTO.getContaOrigem())
                    .contaDestino(contaDestinoId)
                    .numeroContaDestino(novaTransferenciaDTO.getNumeroContaDestino())
                    .valor(novaTransferenciaDTO.getValor())
                    .status(status)
                    .erro(erro)
                    .build()
        ));
    }

    @Transactional
    public void validaSaldo(Conta contaOrigem, Conta contaDestino, NovaTransferenciaDTO transferencia) throws SaldoInsuficienteException {
        log.info("TransferenciaService.validaSaldo - saldo conta origem {}, valor transferencia {}", contaOrigem.getSaldo(), transferencia.getValor());
        if (contaOrigem.getSaldo().compareTo(transferencia.getValor()) < 0) {
            throw new SaldoInsuficienteException();
        }
        contaOrigem.removeSaldo(transferencia.getValor());
        contaDestino.adicionaSaldo(transferencia.getValor());
    }

    public List<Transferencia> listaTransferencias() {
        log.info("TransferenciaService.listaTransferencias");
        return transferenciaRepository.findAll();
    }

    public List<Transferencia> listaTransferenciasByContaId(Long contaId) {
        log.info("TransferenciaService.listaTransferenciasByContaId - id {}", contaId);
        return transferenciaRepository.findAllByContaOrigemOrContaDestinoOrderByDataCriacaoDesc(contaId, contaId);
    }
}