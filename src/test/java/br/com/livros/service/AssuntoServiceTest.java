package br.com.livros.service;

import br.com.livros.dto.AssuntoRequest;
import br.com.livros.dto.AssuntoResponse;
import br.com.livros.entity.Assunto;
import br.com.livros.mapper.AssuntoMapper;
import br.com.livros.repository.AssuntoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssuntoServiceTest {

    @Mock
    private AssuntoRepository assuntoRepository;

    private AssuntoService assuntoService;

    @BeforeEach
    void setUp() {
        assuntoService = new AssuntoService(assuntoRepository, new AssuntoMapper());
    }

    @Test
    void listar() {
        Assunto assunto = Assunto.builder().id(1).descricao("Romance").build();

        when(assuntoRepository.findAll()).thenReturn(List.of(assunto));
        List<AssuntoResponse> resultado = assuntoService.listar();
        assertEquals(1, resultado.size());
        verify(assuntoRepository).findAll();
    }

    @Test
    void criar() {
        AssuntoRequest request = new AssuntoRequest("Ficcao");
        Assunto assuntoSalvo = Assunto.builder().id(1).descricao("Ficcao").build();

        when(assuntoRepository.save(any(Assunto.class))).thenReturn(assuntoSalvo);
        AssuntoResponse resultado = assuntoService.criar(request);
        assertEquals(1, resultado.id());
        assertEquals("Ficcao", resultado.descricao());
    }

    @Test
    void buscarPorId() {
        Assunto assunto = Assunto.builder().id(1).descricao("Biografia").build();

        when(assuntoRepository.findById(1)).thenReturn(Optional.of(assunto));
        AssuntoResponse resultado = assuntoService.buscarPorId(1);
        assertEquals(1, resultado.id());
        verify(assuntoRepository).findById(1);
    }

    @Test
    void atualizar() {
        Assunto assunto = Assunto.builder().id(1).descricao("Antigo").build();
        AssuntoRequest request = new AssuntoRequest("Novo");
        Assunto assuntoAtualizado = Assunto.builder().id(1).descricao("Novo").build();

        when(assuntoRepository.findById(1)).thenReturn(Optional.of(assunto));
        when(assuntoRepository.save(assunto)).thenReturn(assuntoAtualizado);
        AssuntoResponse resultado = assuntoService.atualizar(1, request);
        assertEquals(1, resultado.id());
        assertEquals("Novo", resultado.descricao());
        verify(assuntoRepository).save(assunto);
    }

    @Test
    void excluir() {
        Assunto assunto = Assunto.builder().id(1).descricao("Poesia").build();
        when(assuntoRepository.findById(1)).thenReturn(Optional.of(assunto));
        assuntoService.excluir(1);
        verify(assuntoRepository).delete(assunto);
    }
}
