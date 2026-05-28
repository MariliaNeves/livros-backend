package br.com.livros.repository;

import java.math.BigDecimal;

public interface RelatorioLivroPorAutorProjection {
    Integer getAutorId();
    String getNomeAutor();
    Integer getLivroId();
    String getTitulo();
    String getEditora();
    Integer getEdicao();
    String getAnoPublicacao();
    BigDecimal getValor();
    String getAssuntos();
}
