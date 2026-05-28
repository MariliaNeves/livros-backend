package br.com.livros.service;

import br.com.livros.dto.AssuntoRequest;
import br.com.livros.dto.AssuntoResponse;
import br.com.livros.entity.Assunto;
import br.com.livros.exception.ResourceNotFoundException;
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

    @Transactional
    public AssuntoResponse criar(AssuntoRequest request){
        Assunto assunto = assuntoMapper.toEntity(request);
        return  assuntoMapper.toResponse(assuntoRepository.save(assunto));
    }

    @Transactional(readOnly = true)
    public AssuntoResponse buscarPorId(Integer id) {
        return assuntoMapper.toResponse(buscarEntidade(id));
    }

    @Transactional
    public AssuntoResponse atualizar(Integer id, AssuntoRequest request) {
        Assunto assunto = buscarEntidade(id);
        assuntoMapper.updateEntity(assunto, request);
        return assuntoMapper.toResponse(assuntoRepository.save(assunto));
    }

    @Transactional
    public void excluir(Integer id) {
        Assunto assunto = buscarEntidade(id);
        assuntoRepository.delete(assunto);
    }

    private Assunto buscarEntidade(Integer id) {
        return assuntoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assunto nao encontrado: " + id));
    }

}
