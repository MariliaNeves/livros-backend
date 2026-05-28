package br.com.livros.service;

import br.com.livros.dto.LivroRequest;
import br.com.livros.dto.LivroResponse;
import br.com.livros.entity.Assunto;
import br.com.livros.entity.Autor;
import br.com.livros.entity.Livro;
import br.com.livros.exception.BusinessException;
import br.com.livros.exception.ResourceNotFoundException;
import br.com.livros.mapper.LivroMapper;
import br.com.livros.repository.AssuntoRepository;
import br.com.livros.repository.AutorRepository;
import br.com.livros.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final AssuntoRepository assuntoRepository;
    private final LivroMapper livroMapper;

    public LivroService(
            LivroRepository livroRepository,
            AutorRepository autorRepository,
            AssuntoRepository assuntoRepository,
            LivroMapper livroMapper
    ) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.assuntoRepository = assuntoRepository;
        this.livroMapper = livroMapper;
    }

    @Transactional(readOnly = true)
    public List<LivroResponse> listar() {
        return livroRepository.findAll().stream().map(livroMapper::toResponse).toList();
    }

    @Transactional
    public LivroResponse criar(LivroRequest request) {
        Set<Autor> autores = buscarAutores(request.autoresIds());
        Set<Assunto> assuntos = buscarAssuntos(request.assuntosIds());
        Livro livro = livroMapper.toEntity(request, autores, assuntos);
        return livroMapper.toResponse(livroRepository.save(livro));
    }

    @Transactional(readOnly = true)
    public LivroResponse buscarPorId(Integer id) {
        return livroMapper.toResponse(buscarEntidade(id));
    }

    @Transactional
    public LivroResponse atualizar(Integer id, LivroRequest request) {
        Livro livro = buscarEntidade(id);
        Set<Autor> autores = buscarAutores(request.autoresIds());
        Set<Assunto> assuntos = buscarAssuntos(request.assuntosIds());
        livroMapper.updateEntity(livro, request, autores, assuntos);
        return livroMapper.toResponse(livroRepository.save(livro));
    }

    @Transactional
    public void excluir(Integer id) {
        Livro livro = buscarEntidade(id);
        livroRepository.delete(livro);
    }

    private Livro buscarEntidade(Integer id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro nao encontrado: " + id));
    }

    private Set<Autor> buscarAutores(Set<Integer> ids) {
        List<Autor> autores = autorRepository.findAllById(ids);
        if (autores.size() != ids.size()) {
            throw new BusinessException("Um ou mais autores informados nao existem");
        }
        return new HashSet<>(autores);
    }

    private Set<Assunto> buscarAssuntos(Set<Integer> ids) {
        List<Assunto> assuntos = assuntoRepository.findAllById(ids);
        if (assuntos.size() != ids.size()) {
            throw new BusinessException("Um ou mais assuntos informados nao existem");
        }
        return new HashSet<>(assuntos);
    }
}
