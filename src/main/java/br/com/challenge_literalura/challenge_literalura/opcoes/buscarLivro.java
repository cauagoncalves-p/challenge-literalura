package br.com.challenge_literalura.challenge_literalura.opcoes;

import br.com.challenge_literalura.challenge_literalura.model.dadosLivros;
import br.com.challenge_literalura.challenge_literalura.model.dadosResposta;
import br.com.challenge_literalura.challenge_literalura.service.ConsumoAPI;
import br.com.challenge_literalura.challenge_literalura.service.ConvertDado;
import br.com.challenge_literalura.challenge_literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class buscarLivro {
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConvertDado conversor = new ConvertDado();
    private final Scanner leitura = new Scanner(System.in);
    final String URL_BASE = "https://gutendex.com/books";

    @Autowired
    private LivroService livroService;

    public void buscar(){
        System.out.println("Qual o nome do livro? ");
        String escolhaLivro = leitura.nextLine().toLowerCase().replace(" ", "%20");
        String buscaURL = URL_BASE + "?search=" + escolhaLivro;
        var json = consumo.obterDados(buscaURL);
        dadosResposta resposta = conversor.obterDados(json, dadosResposta.class);

        // Verifica se veio algo
        if (resposta.getResults() != null && !resposta.getResults().isEmpty()) {
            dadosLivros livro = resposta.getResults().get(0);
            System.out.println("Título: " + livro.titulo());
            if (livro.autor() != null && !livro.autor().isEmpty()) {
                System.out.println("Autor: " + livro.autor().get(0).nome());
            }
            System.out.println("Idiomas: " + livro.idioma());
            System.out.println("Downloads: " + livro.numeroDownload());
            livroService.salvarLivroNoBanco(livro);
        } else {
            System.out.println("Nenhum livro encontrado na resposta.");
        }
    }
}
