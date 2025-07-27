package br.com.challenge_literalura.challenge_literalura.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class dadosResposta {
    private List<dadosLivros> results;

    public List<dadosLivros> getResults() {
        return results;
    }

    public void setResults(List<dadosLivros> results) {
        this.results = results;
    }
}