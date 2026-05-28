package br.com.livros.repository;

import br.com.livros.entity.Livro;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    @Override
    @EntityGraph(attributePaths = {"autores", "assuntos"})
    List<Livro> findAll();

    @Override
    @EntityGraph(attributePaths = {"autores", "assuntos"})
    Optional<Livro> findById(Integer id);
}
