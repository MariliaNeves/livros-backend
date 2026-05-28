package br.com.livros.service;

import br.com.livros.dto.AutorRequest;
import br.com.livros.dto.AutorResponse;
import br.com.livros.entity.Autor;
import br.com.livros.exception.ResourceNotFoundException;
import br.com.livros.mapper.AutorMapper;
import br.com.livros.repository.AutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;
    private final AutorMapper autorMapper;

    public AutorService(AutorRepository autorRepository, AutorMapper autorMapper) {
        this.autorRepository = autorRepository;
        this.autorMapper = autorMapper;
    }


    @Transactional(readOnly = true)
    public List<AutorResponse> listar() {
        return autorRepository.findAll().stream().map(autorMapper::toResponse).toList();
    }

    @Transactional
    public AutorResponse criar(AutorRequest request) {
        Autor autor = autorMapper.toEntity(request);
        return autorMapper.toResponse(autorRepository.save(autor));
    }

    @Transactional(readOnly = true)
    public AutorResponse buscarPorId(Integer id) {
        return autorMapper.toResponse(buscarEntidade(id));
    }

    @Transactional
    public AutorResponse atualizar(Integer id, AutorRequest request) {
        Autor autor = buscarEntidade(id);
        autorMapper.updateEntity(autor, request);
        return autorMapper.toResponse(autorRepository.save(autor));
    }

    @Transactional
    public void excluir(Integer id) {
        Autor autor = buscarEntidade(id);
        autorRepository.delete(autor);
    }

    private Autor buscarEntidade(Integer id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor nao encontrado: " + id));
    }
}
