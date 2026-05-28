package br.com.livros.mapper;

import br.com.livros.dto.AutorRequest;
import br.com.livros.dto.AutorResponse;
import br.com.livros.entity.Autor;
import org.springframework.stereotype.Component;

@Component
public class AutorMapper {

    public Autor toEntity(AutorRequest request) {
        Autor autor = new Autor();
        autor.setNome(request.nome());
        return autor;
    }

    public void updateEntity(Autor autor, AutorRequest request) {
        autor.setNome(request.nome());
    }

    public AutorResponse toResponse(Autor autor) {
        return new AutorResponse(autor.getId(), autor.getNome());
    }
}
