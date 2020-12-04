package br.com.viniciuspenha.contacliente.model.entity.transferencia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long contaOrigem;
    private Long contaDestino;
    private String numeroContaDestino;
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TransferenciaStatus status;

    @Enumerated(EnumType.STRING)
    private TransferenciaErro erro;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;
}