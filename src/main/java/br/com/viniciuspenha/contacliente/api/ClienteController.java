package br.com.viniciuspenha.contacliente.api;

import br.com.viniciuspenha.contacliente.exception.ContaNotFoundException;
import br.com.viniciuspenha.contacliente.model.dto.cliente.CadastroClienteDTO;
import br.com.viniciuspenha.contacliente.model.dto.cliente.ClienteDTO;
import br.com.viniciuspenha.contacliente.model.mapper.ClienteMapper;
import br.com.viniciuspenha.contacliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO novoCliente(@RequestBody CadastroClienteDTO cadastroClienteDTO) {
        return clienteMapper.toClienteDTO(
                clienteService.novoCliente(cadastroClienteDTO)
        );
    }

    @GetMapping
    public Set<ClienteDTO> listaClientes() {
        return clienteService.listaClientes()
                .stream()
                .map(clienteMapper::toClienteDTO)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{numeroConta}")
    public ClienteDTO buscaPorNumeroDaConta(@PathVariable("numeroConta") String numeroConta) throws ContaNotFoundException {
        return clienteMapper.toClienteDTO(
                clienteService.buscaPorNumeroDaConta(numeroConta)
        );
    }
}