package br.com.livros.controller;

import br.com.livros.dto.AssuntoResponse;
import br.com.livros.entity.Assunto;
import br.com.livros.service.AssuntoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
