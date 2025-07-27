package br.com.challenge_literalura.challenge_literalura.principal;
import br.com.challenge_literalura.challenge_literalura.model.Autor;
import br.com.challenge_literalura.challenge_literalura.model.Livro;
import br.com.challenge_literalura.challenge_literalura.model.dadosResposta;
import br.com.challenge_literalura.challenge_literalura.repository.AutorRepository;
import br.com.challenge_literalura.challenge_literalura.repository.LivroRepository;
import br.com.challenge_literalura.challenge_literalura.service.ConsumoAPI;
import br.com.challenge_literalura.challenge_literalura.service.LivroService;
import br.com.challenge_literalura.challenge_literalura.service.ConvertDado;
import br.com.challenge_literalura.challenge_literalura.model.dadosLivros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConvertDado conversor = new ConvertDado();
    private final Scanner leitura = new Scanner(System.in);
    final String URL_BASE = "https://gutendex.com/books";

    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void exibirMenu() {
        Integer escolhaOpcao = -1;
        while (escolhaOpcao != 0) {

            var menu = """
                    Escolha o número de sua opção:
                    
                    1 - Buscar livros pelo titulo
                    2 - listar livros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos em um determinado ano 
                    5 - listar livros em um determinado idioma 
                    0 - sair
                    """;
            System.out.println(menu);
            escolhaOpcao = leitura.nextInt();
            leitura.nextLine();
            switch (escolhaOpcao) {
                case 1:
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
                    break;
                case 2:
                    List<Livro> livros = livroRepository.findAll();
                    for (Livro livro : livros) {
                        System.out.println("Título: " + livro.getTitulo());
                        if (livro.getAutor() != null) {
                            System.out.println("Autor: " + livro.getAutor().getNome());
                        }
                        System.out.println("Idiomas: " + livro.getIdioma());
                        System.out.println("Downloads: " + livro.getNumeroDownload());

                        System.out.println("-------------");
                    }
                    break;

                case 3:
                    List<Autor> autores = autorRepository.buscarTodosComLivros();
                    for (Autor autor : autores) {
                        System.out.println("Autor: " + autor.getNome());
                        System.out.println("Ano de nascimento: " + autor.getAnoDeNascimento());
                        System.out.println("Ano de falecimento: " + autor.getAnoDeFalecimento());
                        autor.getLivros().forEach(livro -> {
                            System.out.println("Livro: " + livro.getTitulo());

                            System.out.println("-------------");
                        });
                    }
                    break;
                case 4:
                    System.out.println("Insira o ano que deseja buscar");
                    Integer anoBusca = leitura.nextInt();
                    List<Autor> autoresVivos = autorRepository.buscarAutoresVivos(anoBusca);

                    if (autoresVivos.isEmpty()){
                        System.out.println("Não há autores vivos no ano de: " + anoBusca);
                    }else{
                        for (Autor autor : autoresVivos) {
                            System.out.println("Autor: " + autor.getNome());
                            System.out.println("Ano de nascimento: " + autor.getAnoDeNascimento());
                            System.out.println("Ano de falecimento: " + autor.getAnoDeFalecimento());
                            autor.getLivros().forEach(livro -> {
                                System.out.println("Livro: " + livro.getTitulo());

                                System.out.println("-------------");
                            });
                        }
                    }
                    break;
                case 5:
                    System.out.println("Insira o idioma para realizar a busca: ");
                    System.out.println("es - espanhol");
                    System.out.println("pt - português");
                    System.out.println("en - inglês");
                    System.out.println("fr - francês");

                    String idiomaEscolhido = leitura.nextLine().toLowerCase().replace(" ", "");

                    List<Livro> buscaIdioma = livroRepository.buscaPorIdioma(idiomaEscolhido);
                    if (buscaIdioma.isEmpty()) {
                        System.out.println("Não ha livros disponiveis para esse idioma");
                    } else {
                        for (Livro livro : buscaIdioma) {
                            System.out.println("Título: " + livro.getTitulo());
                            if (livro.getAutor() != null) {
                                System.out.println("Autor: " + livro.getAutor().getNome());
                            }
                            System.out.println("Idiomas: " + livro.getIdioma());
                            System.out.println("Downloads: " + livro.getNumeroDownload());

                            System.out.println("-------------");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Finalizando programa...");
                    break;
                default:
                    System.out.println("Opção Invalída");
                    break;
            }
        }
    }
}