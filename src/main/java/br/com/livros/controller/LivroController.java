package br.com.livros.controller;

import br.com.livros.dto.LivroRequest;
import br.com.livros.dto.LivroResponse;
import br.com.livros.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<LivroResponse> listar() {
        return livroService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponse criar(@Valid @RequestBody LivroRequest request) {
        return livroService.criar(request);
    }

    @GetMapping("/{id}")
    public LivroResponse buscarPorId(@PathVariable Integer id) {
        return livroService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public LivroResponse atualizar(@PathVariable Integer id, @Valid @RequestBody LivroRequest request) {
        return livroService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        livroService.excluir(id);
    }
}
