package br.com.livros.mapper;

import br.com.livros.dto.AssuntoRequest;
import br.com.livros.dto.AssuntoResponse;
import br.com.livros.entity.Assunto;
import org.springframework.stereotype.Component;

@Component
public class AssuntoMapper {


    public Assunto toEntity(AssuntoRequest request) {
        Assunto assunto = new Assunto();
        assunto.setDescricao(request.descricao());
        return assunto;
    }

    public void  updateEntity(Assunto assunto, AssuntoRequest request) {
        assunto.setDescricao(request.descricao());
    }

    public AssuntoResponse toResponse(Assunto assunto) {
        return  new AssuntoResponse(assunto.getId(), assunto.getDescricao());
    }

}
