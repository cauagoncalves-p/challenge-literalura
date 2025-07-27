package br.com.challenge_literalura.challenge_literalura.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate anoDeNascimento;

    private LocalDate anoDeFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Livro> livros;

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getAnoDeNascimento() { return anoDeNascimento; }
    public void setAnoDeNascimento(LocalDate anoDeNascimento) { this.anoDeNascimento = anoDeNascimento; }

    public LocalDate getAnoDeFalecimento() { return anoDeFalecimento; }
    public void setAnoDeFalecimento(LocalDate anoDeFalecimento) { this.anoDeFalecimento = anoDeFalecimento; }

    public List<Livro> getLivros() { return livros; }
    public void setLivros(List<Livro> livros) { this.livros = livros; }
}
