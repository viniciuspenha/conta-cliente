package br.com.viniciuspenha.contacliente.unidade;

import br.com.viniciuspenha.contacliente.exception.SaldoInsuficienteException;
import br.com.viniciuspenha.contacliente.model.dto.transferencia.NovaTransferenciaDTO;
import br.com.viniciuspenha.contacliente.model.entity.Conta;
import br.com.viniciuspenha.contacliente.service.TransferenciaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferenciaTest {

    @Autowired
    private TransferenciaService transferenciaService;

    @Test
    public void testaValidacaoDeSaldoPositivo() throws SaldoInsuficienteException {
        Conta contaOrigem = new Conta();
        Conta contaDestino = new Conta();
        NovaTransferenciaDTO transferencia = new NovaTransferenciaDTO();

        contaOrigem.setSaldo(new BigDecimal(100));
        transferencia.setValor(new BigDecimal(50));
        transferenciaService.validaSaldo(contaOrigem, contaDestino, transferencia);

        assertEquals(contaOrigem.getSaldo(), new BigDecimal(50));
        assertEquals(contaDestino.getSaldo(), new BigDecimal(50));
    }

    @Test
    public void testaValidacaoDeSaldoTotal() throws SaldoInsuficienteException {
        Conta contaOrigem = new Conta();
        Conta contaDestino = new Conta();
        NovaTransferenciaDTO transferencia = new NovaTransferenciaDTO();

        contaOrigem.setSaldo(new BigDecimal(100));
        transferencia.setValor(new BigDecimal(100));
        transferenciaService.validaSaldo(contaOrigem, contaDestino, transferencia);

        assertEquals(contaOrigem.getSaldo(), new BigDecimal(0));
        assertEquals(contaDestino.getSaldo(), new BigDecimal(100));
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void testaValidacaoDeSaldoInsuficiente() throws SaldoInsuficienteException {
        Conta contaOrigem = new Conta();
        Conta contaDestino = new Conta();
        NovaTransferenciaDTO transferencia = new NovaTransferenciaDTO();

        contaOrigem.setSaldo(new BigDecimal(100));
        transferencia.setValor(new BigDecimal(101));
        transferenciaService.validaSaldo(contaOrigem, contaDestino, transferencia);
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void testaValidacaoDeSaldoFraction() throws SaldoInsuficienteException {
        Conta contaOrigem = new Conta();
        Conta contaDestino = new Conta();
        NovaTransferenciaDTO transferencia = new NovaTransferenciaDTO();

        contaOrigem.setSaldo(new BigDecimal(100));
        transferencia.setValor(new BigDecimal("100.01"));
        transferenciaService.validaSaldo(contaOrigem, contaDestino, transferencia);
    }
}