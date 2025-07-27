package br.com.challenge_literalura.challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record dadosAutor(
      @JsonAlias("name") String nome,
      @JsonAlias("birth_year") LocalDate anoDeNascimento,
      @JsonAlias("death_year") LocalDate anoDeFalecimento
) {
}
