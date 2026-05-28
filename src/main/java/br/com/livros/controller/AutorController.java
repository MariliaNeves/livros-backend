package br.com.livros.controller;

import br.com.livros.dto.AutorRequest;
import br.com.livros.dto.AutorResponse;
import br.com.livros.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public List<AutorResponse> listar() {
        return autorService.listar();
    }

    @GetMapping("/{id}")
    public AutorResponse buscarPorId(@PathVariable Integer id) {
        return autorService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutorResponse criar(@Valid @RequestBody AutorRequest request) {
        return autorService.criar(request);
    }

    @PutMapping("/{id}")
    public AutorResponse atualizar(@PathVariable Integer id, @Valid @RequestBody AutorRequest request) {
        return autorService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        autorService.excluir(id);
    }
}
