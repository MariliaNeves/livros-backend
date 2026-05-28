package br.com.livros.controller;

import br.com.livros.dto.AssuntoRequest;
import br.com.livros.dto.AssuntoResponse;
import br.com.livros.entity.Assunto;
import br.com.livros.service.AssuntoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assuntos")
public class AssuntoController {

    private final AssuntoService assuntoService;

    public AssuntoController(AssuntoService assuntoService) {
        this.assuntoService = assuntoService;
    }

    @GetMapping
    public List<AssuntoResponse> listar(){
        return assuntoService.listar();
    }

    @GetMapping("/{id}")
    public AssuntoResponse buscarPorId(@PathVariable Integer id) {
        return assuntoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssuntoResponse criar(@Valid @RequestBody AssuntoRequest request) {
        return assuntoService.criar(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        assuntoService.excluir(id);
    }


    @PutMapping("/{id}")
    public AssuntoResponse atualizar(@PathVariable Integer id, @Valid @RequestBody AssuntoRequest request) {
        return assuntoService.atualizar(id, request);
    }
}
