package br.com.livros.service;

import br.com.livros.entity.Assunto;
import br.com.livros.repository.AssuntoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssuntoService {

    private final AssuntoRepository assuntoRepository;

    public AssuntoService(AssuntoRepository assuntoRepository) {
        this.assuntoRepository = assuntoRepository;
    }

    @Transactional(readOnly = true)
    public List<Assunto> listar(){
        return assuntoRepository.findAll();
    }

}
