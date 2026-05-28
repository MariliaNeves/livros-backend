package br.com.livros.service;

import br.com.livros.dto.LivroRequest;
import br.com.livros.dto.LivroResponse;
import br.com.livros.entity.Assunto;
import br.com.livros.entity.Autor;
import br.com.livros.entity.Livro;
import br.com.livros.mapper.LivroMapper;
import br.com.livros.repository.AssuntoRepository;
import br.com.livros.repository.AutorRepository;
import br.com.livros.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private AssuntoRepository assuntoRepository;

    private LivroService livroService;

    @BeforeEach
    void setUp() {
        livroService = new LivroService(livroRepository, autorRepository, assuntoRepository, new LivroMapper());
    }

    @Test
    void listar() {
        Livro livro = livro();

        when(livroRepository.findAll()).thenReturn(List.of(livro));
        List<LivroResponse> resultado = livroService.listar();
        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).id());
        assertEquals("Dom Casmurro", resultado.get(0).titulo());
        assertEquals(1, resultado.get(0).autores().size());
        assertEquals(1, resultado.get(0).assuntos().size());
        verify(livroRepository).findAll();
    }

    @Test
    void criar() {
        LivroRequest request = request();
        Autor autor = autor();
        Assunto assunto = assunto();
        Livro livroSalvo = livro();
        when(autorRepository.findAllById(Set.of(1))).thenReturn(List.of(autor));
        when(assuntoRepository.findAllById(Set.of(1))).thenReturn(List.of(assunto));
        when(livroRepository.save(any(Livro.class))).thenReturn(livroSalvo);

        LivroResponse resultado = livroService.criar(request);
        assertEquals(1, resultado.id());
    }

    @Test
    void buscarPorId() {
        Livro livro = livro();
        when(livroRepository.findById(1)).thenReturn(Optional.of(livro));
        LivroResponse resultado = livroService.buscarPorId(1);
        assertEquals(1, resultado.id());
        verify(livroRepository).findById(1);
    }

    @Test
    void atualizar() {
        Livro livro = livro();
        LivroRequest request = new LivroRequest(
                "Memorias Postumas",
                "Editora B",
                2,
                "1881",
                new BigDecimal("39.90"),
                Set.of(1),
                Set.of(1)
        );
        Autor autor = autor();
        Assunto assunto = assunto();
        Livro livroAtualizado = Livro.builder()
                .id(1)
                .titulo("Memorias Postumas")
                .editora("Editora B")
                .edicao(2)
                .anoPublicacao("1881")
                .valor(new BigDecimal("39.90"))
                .autores(Set.of(autor))
                .assuntos(Set.of(assunto))
                .build();

        when(livroRepository.findById(1)).thenReturn(Optional.of(livro));
        when(autorRepository.findAllById(Set.of(1))).thenReturn(List.of(autor));
        when(assuntoRepository.findAllById(Set.of(1))).thenReturn(List.of(assunto));
        when(livroRepository.save(livro)).thenReturn(livroAtualizado);

        LivroResponse resultado = livroService.atualizar(1, request);
        assertEquals(1, resultado.id());
        assertEquals("Memorias Postumas", resultado.titulo());
        assertEquals("Editora B", resultado.editora());
        assertEquals(2, resultado.edicao());
        verify(livroRepository).save(livro);
    }

    @Test
    void excluir() {
        Livro livro = livro();
        when(livroRepository.findById(1)).thenReturn(Optional.of(livro));
        livroService.excluir(1);
        verify(livroRepository).delete(livro);
    }

    private LivroRequest request() {
        return new LivroRequest(
                "Dom Casmurro",
                "Editora A",
                1,
                "1899",
                new BigDecimal("29.90"),
                Set.of(1),
                Set.of(1)
        );
    }

    private Livro livro() {
        return Livro.builder()
                .id(1)
                .titulo("Dom Casmurro")
                .editora("Editora A")
                .edicao(1)
                .anoPublicacao("1899")
                .valor(new BigDecimal("29.90"))
                .autores(Set.of(autor()))
                .assuntos(Set.of(assunto()))
                .build();
    }

    private Autor autor() {
        return Autor.builder().id(1).nome("Machado de Assis").build();
    }

    private Assunto assunto() {
        return Assunto.builder().id(1).descricao("Romance").build();
    }
}
