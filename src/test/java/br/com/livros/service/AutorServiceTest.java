package br.com.livros.service;

import br.com.livros.dto.AutorRequest;
import br.com.livros.dto.AutorResponse;
import br.com.livros.entity.Autor;
import br.com.livros.mapper.AutorMapper;
import br.com.livros.repository.AutorRepository;
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
class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    private AutorService autorService;

    @BeforeEach
    void setUp() {
        autorService = new AutorService(autorRepository, new AutorMapper());
    }

    @Test
    void listar() {
        Autor autor = Autor.builder().id(1).nome("Machado de Assis").build();
        when(autorRepository.findAll()).thenReturn(List.of(autor));
        List<AutorResponse> resultado = autorService.listar();
        assertEquals(1, resultado.size());

    }

    @Test
    void criar() {
        AutorRequest request = new AutorRequest("Clarice Lispector");
        Autor autorSalvo = Autor.builder().id(1).nome("Clarice Lispector").build();

        when(autorRepository.save(any(Autor.class))).thenReturn(autorSalvo);
        AutorResponse resultado = autorService.criar(request);
        assertEquals(1, resultado.id());
        assertEquals("Clarice Lispector", resultado.nome());
    }

    @Test
    void buscarPorId() {
        Autor autor = Autor.builder().id(1).nome("Jorge Amado").build();
        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        AutorResponse resultado = autorService.buscarPorId(1);
        assertEquals(1, resultado.id());
        verify(autorRepository).findById(1);
    }

    @Test
    void atualizar() {
        Autor autor = Autor.builder().id(1).nome("Nome").build();
        AutorRequest request = new AutorRequest("Nome EDIT");
        Autor autorAtualizado = Autor.builder().id(1).nome("Nome EDIT").build();

        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        when(autorRepository.save(autor)).thenReturn(autorAtualizado);
        AutorResponse resultado = autorService.atualizar(1, request);
        assertEquals(1, resultado.id());
        assertEquals("Nome EDIT", resultado.nome());
        verify(autorRepository).save(autor);
    }

    @Test
    void excluir() {
        Autor autor = Autor.builder().id(1).nome("Carlos Drummond de Andrade").build();

        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        autorService.excluir(1);
        verify(autorRepository).delete(autor);
    }
}
