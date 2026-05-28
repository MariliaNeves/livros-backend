package br.com.livros.service;

import br.com.livros.dto.RelatorioLivroPorAutorResponse;
import br.com.livros.mapper.RelatorioMapper;
import br.com.livros.repository.RelatorioLivroPorAutorProjection;
import br.com.livros.repository.RelatorioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RelatorioServiceTest {

    @Mock
    private RelatorioRepository relatorioRepository;

    private RelatorioService relatorioService;

    @BeforeEach
    void setUp() {
        relatorioService = new RelatorioService(relatorioRepository, new RelatorioMapper());
    }

    @Test
    void livrosPorAutor() {
        RelatorioLivroPorAutorProjection projection = projection();
        when(relatorioRepository.buscarLivrosPorAutor()).thenReturn(List.of(projection));
        List<RelatorioLivroPorAutorResponse> resultado = relatorioService.livrosPorAutor();

        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).autorId());
        assertEquals("Machado de Assis", resultado.get(0).nomeAutor());
        assertEquals(1, resultado.get(0).livroId());
        assertEquals("Dom Casmurro", resultado.get(0).titulo());
        assertEquals("Editora A", resultado.get(0).editora());
        assertEquals(1, resultado.get(0).edicao());
        assertEquals("1899", resultado.get(0).anoPublicacao());
        assertEquals(new BigDecimal("29.90"), resultado.get(0).valor());
        assertEquals("Romance", resultado.get(0).assuntos());
        verify(relatorioRepository).buscarLivrosPorAutor();
    }

    private RelatorioLivroPorAutorProjection projection() {
        return new RelatorioLivroPorAutorProjection() {
            @Override
            public Integer getAutorId() {
                return 1;
            }

            @Override
            public String getNomeAutor() {
                return "Machado de Assis";
            }

            @Override
            public Integer getLivroId() {
                return 1;
            }

            @Override
            public String getTitulo() {
                return "Dom Casmurro";
            }

            @Override
            public String getEditora() {
                return "Editora A";
            }

            @Override
            public Integer getEdicao() {
                return 1;
            }

            @Override
            public String getAnoPublicacao() {
                return "1899";
            }

            @Override
            public BigDecimal getValor() {
                return new BigDecimal("29.90");
            }

            @Override
            public String getAssuntos() {
                return "Romance";
            }
        };
    }
}
