package br.com.livros.repository;

import br.com.livros.entity.Livro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RelatorioRepository extends Repository<Livro, Integer> {

    @Query(value = """
            SELECT
                autor_id AS autorId,
                nome_autor AS nomeAutor,
                livro_id AS livroId,
                titulo,
                editora,
                edicao,
                ano_publicacao AS anoPublicacao,
                valor,
                assuntos
            FROM vm_livros_por_autor
            ORDER BY nome_autor, titulo
            """, nativeQuery = true)
    List<RelatorioLivroPorAutorProjection> buscarLivrosPorAutor();
}
