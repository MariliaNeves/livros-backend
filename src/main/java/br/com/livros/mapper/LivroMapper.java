package br.com.livros.mapper;

import br.com.livros.dto.AssuntoResponse;
import br.com.livros.dto.AutorResponse;
import br.com.livros.dto.LivroRequest;
import br.com.livros.dto.LivroResponse;
import br.com.livros.entity.Assunto;
import br.com.livros.entity.Autor;
import br.com.livros.entity.Livro;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LivroMapper {

    public Livro toEntity(LivroRequest request, Set<Autor> autores, Set<Assunto> assuntos) {
        Livro livro = new Livro();
        fillEntity(livro, request, autores, assuntos);
        return livro;
    }

    public void updateEntity(Livro livro, LivroRequest request, Set<Autor> autores, Set<Assunto> assuntos) {
        fillEntity(livro, request, autores, assuntos);
    }

    public LivroResponse toResponse(Livro livro) {
        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getEditora(),
                livro.getEdicao(),
                livro.getAnoPublicacao(),
                livro.getValor(),
                livro.getAutores().stream()
                        .sorted(Comparator.comparing(Autor::getId))
                        .map(autor -> new AutorResponse(autor.getId(), autor.getNome()))
                        .collect(Collectors.toCollection(java.util.LinkedHashSet::new)),
                livro.getAssuntos().stream()
                        .sorted(Comparator.comparing(Assunto::getId))
                        .map(assunto -> new AssuntoResponse(assunto.getId(), assunto.getDescricao()))
                        .collect(Collectors.toCollection(java.util.LinkedHashSet::new))
        );
    }

    private void fillEntity(Livro livro, LivroRequest request, Set<Autor> autores, Set<Assunto> assuntos) {
        livro.setTitulo(request.titulo());
        livro.setEditora(request.editora());
        livro.setEdicao(request.edicao());
        livro.setAnoPublicacao(request.anoPublicacao());
        livro.setValor(request.valor());
        livro.setAutores(autores);
        livro.setAssuntos(assuntos);
    }
}
