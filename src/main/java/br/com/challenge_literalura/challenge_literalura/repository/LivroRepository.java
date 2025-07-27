package br.com.challenge_literalura.challenge_literalura.repository;

import br.com.challenge_literalura.challenge_literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LivroRepository extends JpaRepository<Livro, Long> {
}
