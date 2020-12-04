package br.com.viniciuspenha.contacliente.api;

import br.com.viniciuspenha.contacliente.model.dto.transferencia.NovaTransferenciaDTO;
import br.com.viniciuspenha.contacliente.model.dto.transferencia.TransferenciaResponse;
import br.com.viniciuspenha.contacliente.model.mapper.TransferenciaMapper;
import br.com.viniciuspenha.contacliente.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;
    private final TransferenciaMapper transferenciaMapper;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService, TransferenciaMapper transferenciaMapper) {
        this.transferenciaService = transferenciaService;
        this.transferenciaMapper = transferenciaMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void realizaTransferencia(@RequestBody @Valid NovaTransferenciaDTO novaTransferenciaDTO) throws Exception {
        transferenciaService.novaTransferencia(novaTransferenciaDTO);
    }

    @GetMapping
    public List<TransferenciaResponse> listaTransferencias() {
        return transferenciaService.listaTransferencias()
                .stream()
                .map(transferenciaMapper::toTransferenciaResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{contaId}")
    public List<TransferenciaResponse> listaTransferenciasByContaId(@PathVariable("contaId") Long contaId) {
        return transferenciaService.listaTransferenciasByContaId(contaId)
                .stream()
                .map(transferenciaMapper::toTransferenciaResponse)
                .collect(Collectors.toList());
    }
}