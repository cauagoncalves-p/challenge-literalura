package br.com.challenge_literalura.challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record dadosLivros(@JsonAlias("title") String titulo,
                          @JsonAlias("authors") List<dadosAutor> autor,
                          @JsonAlias("languages") List<String> idioma,
                          @JsonAlias("download_count")Integer numeroDownload)
{}
