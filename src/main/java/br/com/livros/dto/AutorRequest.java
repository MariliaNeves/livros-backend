package br.com.livros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorRequest(
        @NotBlank
        @Size(max = 40)
        String nome
) {
}
