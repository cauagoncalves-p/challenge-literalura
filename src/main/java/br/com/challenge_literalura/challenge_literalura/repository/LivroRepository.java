package br.com.challenge_literalura.challenge_literalura.repository;

import br.com.challenge_literalura.challenge_literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    List<Livro> findAll();

    @Query("SELECT l FROM Livro l WHERE l.idioma = :idiomaEscolhido")
    List<Livro> buscaPorIdioma(String idiomaEscolhido);
}
