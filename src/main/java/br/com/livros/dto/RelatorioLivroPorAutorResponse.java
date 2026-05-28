package br.com.livros.dto;

import java.math.BigDecimal;

public record RelatorioLivroPorAutorResponse(
        Integer autorId,
        String nomeAutor,
        Integer livroId,
        String titulo,
        String editora,
        Integer edicao,
        String anoPublicacao,
        BigDecimal valor,
        String assuntos
) {
}