package br.com.livros.service;

import br.com.livros.dto.AssuntoResponse;
import br.com.livros.entity.Assunto;
import br.com.livros.mapper.AssuntoMapper;
import br.com.livros.repository.AssuntoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssuntoService {

    private final AssuntoRepository assuntoRepository;
    private final AssuntoMapper assuntoMapper;

    public AssuntoService(AssuntoRepository assuntoRepository, AssuntoMapper assuntoMapper) {
        this.assuntoRepository = assuntoRepository;
        this.assuntoMapper = assuntoMapper;
    }

    @Transactional(readOnly = true)
    public List<AssuntoResponse> listar(){
        return assuntoRepository.findAll().stream().map(assuntoMapper::toResponse).toList();
    }

}
