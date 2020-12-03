package br.com.viniciuspenha.contacliente.api;

import br.com.viniciuspenha.contacliente.exception.ClienteNotFoundException;
import br.com.viniciuspenha.contacliente.model.dto.conta.CadastroContaDTO;
import br.com.viniciuspenha.contacliente.model.dto.conta.ContaDTO;
import br.com.viniciuspenha.contacliente.model.mapper.ContaMapper;
import br.com.viniciuspenha.contacliente.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;
    private final ContaMapper contaMapper;

    @Autowired
    public ContaController(ContaService contaService, ContaMapper contaMapper) {
        this.contaService = contaService;
        this.contaMapper = contaMapper;
    }

    @PostMapping
    public ContaDTO novaConta(@RequestBody CadastroContaDTO cadastroContaDTO) throws ClienteNotFoundException {
        return contaMapper.toContaDTO(contaService.novaConta(cadastroContaDTO));
    }
}