package br.com.viniciuspenha.contacliente.integracao;

import br.com.viniciuspenha.contacliente.model.dto.cliente.CadastroClienteDTO;
import br.com.viniciuspenha.contacliente.model.entity.Cliente;
import br.com.viniciuspenha.contacliente.model.mapper.ClienteMapper;
import br.com.viniciuspenha.contacliente.repository.ClienteRepository;
import br.com.viniciuspenha.contacliente.service.ClienteService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @After
    public void wrapUp() {
        reset(clienteRepository);
    }

    @Test
    public void testaCriacaoDeCliente() {
        CadastroClienteDTO cadastroClienteDTO = new CadastroClienteDTO("Vinicius");
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nome("Vinicius")
                .build();

        given(clienteRepository.save(any(Cliente.class))).will(
                invocation -> invocation.getArgument(0, Cliente.class)
        );
        given(clienteMapper.toCliente(cadastroClienteDTO)).willReturn(cliente);

        Cliente clienteResponse = clienteService.novoCliente(cadastroClienteDTO);
        assertEquals("Vinicius", clienteResponse.getNome());
    }
}