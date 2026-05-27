package br.com.livros.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "titulo", nullable = false, length = 40)
    private String titulo;

    @Column(name = "editora", nullable = false, length = 40)
    private String editora;

    @Column(name = "edicao")
    private Integer edicao;

    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;

    @Column(name = "valor", nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_cod"),
            inverseJoinColumns = @JoinColumn(name = "autor_cod")
    )
    private Set<Autor> autores = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "livro_assunto",
            joinColumns = @JoinColumn(name = "livro_cod"),
            inverseJoinColumns = @JoinColumn(name = "assunto_cod")
    )
    private Set<Assunto> assuntos = new HashSet<>();

}
