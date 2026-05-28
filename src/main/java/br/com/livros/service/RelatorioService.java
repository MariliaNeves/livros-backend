package br.com.livros.service;

import br.com.livros.dto.RelatorioLivroPorAutorResponse;
import br.com.livros.mapper.RelatorioMapper;
import br.com.livros.repository.RelatorioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final RelatorioMapper relatorioMapper;

    public RelatorioService(RelatorioRepository relatorioRepository, RelatorioMapper relatorioMapper) {
        this.relatorioRepository = relatorioRepository;
        this.relatorioMapper = relatorioMapper;
    }

    @Transactional(readOnly = true)
    public List<RelatorioLivroPorAutorResponse> livrosPorAutor() {
        return relatorioRepository.buscarLivrosPorAutor().stream()
                .map(relatorioMapper::toResponse)
                .toList();
    }
}
