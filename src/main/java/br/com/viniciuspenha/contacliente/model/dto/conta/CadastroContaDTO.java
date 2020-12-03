package br.com.viniciuspenha.contacliente.model.dto.conta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroContaDTO {

    @NotNull(message = "clienteId nao pode ser nulo")
    private Long clienteId;
}