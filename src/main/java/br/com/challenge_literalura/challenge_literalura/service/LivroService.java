package br.com.challenge_literalura.challenge_literalura.service;
import br.com.challenge_literalura.challenge_literalura.model.Autor;
import br.com.challenge_literalura.challenge_literalura.model.Livro;
import br.com.challenge_literalura.challenge_literalura.model.dadosAutor;
import br.com.challenge_literalura.challenge_literalura.model.dadosLivros;
import br.com.challenge_literalura.challenge_literalura.repository.AutorRepository;
import br.com.challenge_literalura.challenge_literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void salvarLivroNoBanco(dadosLivros dados) {
        dadosAutor autorDados = dados.autor().get(0);

        Autor autor = autorRepository.findAll().stream()
                .filter(a -> a.getNome().equalsIgnoreCase(autorDados.nome()))
                .findFirst()
                .orElseGet(() -> {
                    Autor novoAutor = new Autor();
                    novoAutor.setNome(autorDados.nome());
                    novoAutor.setAnoDeNascimento(autorDados.anoDeNascimento());
                    novoAutor.setAnoDeFalecimento(autorDados.anoDeFalecimento());
                    return autorRepository.save(novoAutor);
                });

        Livro livro = new Livro();
        livro.setTitulo(dados.titulo());
        livro.setIdioma(dados.idioma().isEmpty() ? "desconhecido" : dados.idioma().get(0));
        livro.setNumeroDownload(dados.numeroDownload());
        livro.setAutor(autor);

        livroRepository.save(livro);
        System.out.println("Livro salvo com sucesso! ID do Autor: " + autor.getId());

    }
}
