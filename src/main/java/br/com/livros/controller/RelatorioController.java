package br.com.livros.controller;

import br.com.livros.dto.RelatorioLivroPorAutorResponse;
import br.com.livros.service.RelatorioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;
    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/livros-por-autor")
    public List<RelatorioLivroPorAutorResponse> livrosPorAutor() {
        return relatorioService.livrosPorAutor();
    }
}

