package br.com.challenge_literalura.challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record dadosAutor(
      @JsonAlias("name") String nome,
      @JsonAlias("birth_year") Integer anoDeNascimento,
      @JsonAlias("death_year") Integer anoDeFalecimento
) {
}
