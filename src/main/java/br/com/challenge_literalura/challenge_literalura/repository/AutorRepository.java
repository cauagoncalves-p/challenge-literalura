package br.com.challenge_literalura.challenge_literalura.repository;
import br.com.challenge_literalura.challenge_literalura.model.Autor;
import br.com.challenge_literalura.challenge_literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long>{

    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.livros")
    List<Autor> buscarTodosComLivros();

    @Query("""
    SELECT DISTINCT a
    FROM Autor a
    LEFT JOIN FETCH a.livros
    WHERE a.anoDeNascimento <= :anoBusca
      AND (a.anoDeFalecimento IS NULL OR a.anoDeFalecimento > :anoBusca)
    """)
    List<Autor> buscarAutoresVivos(int anoBusca);
}

