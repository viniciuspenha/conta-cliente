package br.com.viniciuspenha.contacliente.model.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
}