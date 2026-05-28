package br.com.livros.mapper;

import br.com.livros.dto.RelatorioLivroPorAutorResponse;
import br.com.livros.repository.RelatorioLivroPorAutorProjection;
import org.springframework.stereotype.Component;

@Component
public class RelatorioMapper {

    public RelatorioLivroPorAutorResponse toResponse(RelatorioLivroPorAutorProjection projection) {
        return new RelatorioLivroPorAutorResponse(
                projection.getAutorId(),
                projection.getNomeAutor(),
                projection.getLivroId(),
                projection.getTitulo(),
                projection.getEditora(),
                projection.getEdicao(),
                projection.getAnoPublicacao(),
                projection.getValor(),
                projection.getAssuntos()
        );
    }
}

