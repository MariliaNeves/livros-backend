package br.com.livros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AssuntoRequest(
        @NotBlank
        @Size(max = 20)
        String descricao
) {

}
