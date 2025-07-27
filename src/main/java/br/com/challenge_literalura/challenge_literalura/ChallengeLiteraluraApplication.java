package br.com.challenge_literalura.challenge_literalura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.challenge_literalura.challenge_literalura.principal.Principal;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {
	@Autowired
	private Principal principal; // Injetado pelo Spring

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.exibirMenu();  // usa a instância gerenciada pelo Spring
	}
}
