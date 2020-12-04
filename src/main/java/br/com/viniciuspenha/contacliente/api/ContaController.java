package br.com.viniciuspenha.contacliente.api;

import br.com.viniciuspenha.contacliente.exception.ClienteNotFoundException;
import br.com.viniciuspenha.contacliente.exception.ContaNotFoundException;
import br.com.viniciuspenha.contacliente.model.dto.conta.AdicionaSaldo;
import br.com.viniciuspenha.contacliente.model.dto.conta.CadastroContaDTO;
import br.com.viniciuspenha.contacliente.model.dto.conta.ContaDTO;
import br.com.viniciuspenha.contacliente.model.mapper.ContaMapper;
import br.com.viniciuspenha.contacliente.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conta/v1")
public class ContaController {

    private final ContaService contaService;
    private final ContaMapper contaMapper;

    @Autowired
    public ContaController(ContaService contaService, ContaMapper contaMapper) {
        this.contaService = contaService;
        this.contaMapper = contaMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaDTO novaConta(@RequestBody @Valid CadastroContaDTO cadastroContaDTO) throws ClienteNotFoundException {
        return contaMapper.toContaDTO(contaService.novaConta(cadastroContaDTO));
    }

    @PostMapping("/saldo")
    public void adicionaSaldo(@RequestBody @Valid AdicionaSaldo adicionaSaldo) throws ContaNotFoundException {
        contaService.adicionaSaldo(adicionaSaldo);
    }

    @GetMapping
    public List<ContaDTO> listaContas() {
        return contaService.listaContas()
                .stream()
                .map(contaMapper::toContaDTO)
                .collect(Collectors.toList());
    }
}