package br.com.viniciuspenha.contacliente.repository;

import br.com.viniciuspenha.contacliente.model.entity.transferencia.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findAllByContaOrigemOrContaDestinoOrderByDataCriacaoDesc(Long contaOrigem, Long contaDestino);
}