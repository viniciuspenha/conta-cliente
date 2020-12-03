package br.com.viniciuspenha.contacliente.model.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroClienteDTO {

    @NotNull(message = "Nome nao pode ser nulo")
    @Size(min = 2, max = 100, message = "Nome precisa ter entre 2 e 100 caracteres")
    private String nome;
}