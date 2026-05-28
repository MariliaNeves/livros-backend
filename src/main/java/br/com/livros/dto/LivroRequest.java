package br.com.livros.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;

public record LivroRequest(
        @NotBlank
        @Size(max = 40)
        String titulo,

        @NotBlank
        @Size(max = 40)
        String editora,

        @NotNull
        @Min(1)
        Integer edicao,

        @NotBlank
        @Size(min = 4, max = 4)
        @Pattern(regexp = "\\d{4}")
        String anoPublicacao,

        @NotNull
        @DecimalMin(value = "0.00", inclusive = false)
        BigDecimal valor,

        @NotEmpty
        Set<Integer> autoresIds,

        @NotEmpty
        Set<Integer> assuntosIds
) {
}
