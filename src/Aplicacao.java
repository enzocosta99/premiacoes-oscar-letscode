import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Aplicacao {
	
	public static LeituraArquivo leituraArquivoAtor;
	public static LeituraArquivo leituraArquivoAtriz;
	
	public static void main(String[] args) {
		
		// Leitura do arquivo
		leituraArquivoAtor = new LeituraArquivo("oscar_age_male.csv");
		leituraArquivoAtriz = new LeituraArquivo("oscar_age_female.csv");
		List<Premiacoes> H = leituraArquivoAtor.getPremiacoesList();
		List<Premiacoes> M = leituraArquivoAtriz.getPremiacoesList();
		
		// Questão 1:
		System.out.println(linha());
		System.out.println("Quem foi o ator mais jovem a ganhar um Oscar?\n");
		atorMaisJovem(H);
		
		// Questão 2:
		System.out.println(linha());
		System.out.println("Quem foi a atriz que mais vezes foi premiada?\n");
		atrizMaisPremiada(M);
		
		// Questão 3:
		//System.out.println(linha());
		//System.out.println("3. Qual atriz entre 20 e 30 anos que mais vezes foi vencedora?\n");
		//atrizEntre20E30(M);
		
		// Questão 4:
		List<Premiacoes> atoresEAtrizes = new ArrayList<>();
		atoresEAtrizes.addAll(H);
		atoresEAtrizes.addAll(M);
		System.out.println(linha());
		System.out.println("Quais atores ou atrizes receberam mais de um Oscar?\n");
		maisDeUmOscar(atoresEAtrizes);
		
		// Questão 5:
		System.out.println(linha());
		resumo("Leonardo DiCaprio", atoresEAtrizes);
		System.out.println(linha());
		resumo("Tom Hanks", atoresEAtrizes);
		System.out.println(linha());
		resumo("Katharine Hepburn", atoresEAtrizes);
		System.out.println(linha());
		resumo("Enzo Costa", atoresEAtrizes);
		System.out.println(linha());
	}
	
	private static void atorMaisJovem(List<Premiacoes> premiacoesList) {
		Optional<Premiacoes> atorMaisJovem = premiacoesList
				.stream()
				.min(Comparator.comparing(Premiacoes::getIdade));
		atorMaisJovem.ifPresent(it -> System.out.println(""
				+ "O ator mais jovem a ganhar um Oscar foi" 
				+ it.getNome() + ", em" + it.getAno() + " aos"
				+ it.getIdade() + " anos de idade, no filme"
				+ it.getFilme() + "."));
	}
	
	private static void atrizMaisPremiada(List<Premiacoes> premiacoesList) {
		Map<String, Long> numeroPremios = premiacoesList
				.stream()
				.map(Premiacoes::getNome)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		numeroPremios.entrySet()
		.stream()
		.max(Comparator.comparingLong(Map.Entry::getValue))
		.ifPresent(premio -> System.out.println("A atriz que mais vezes foi premiada foi" 
		+ premio.getKey() + " com um total de " + premio.getValue() + " premiações."));
	}
	
	/*private static void atrizEntre20E30(List<Premiacoes> premiacoesList) {
		Map<String, Long> atriz = premiacoesList
				.stream()
				.filter(premiada -> premiada.getIdade() >= 20 && premiada.getIdade() <= 30)
				.map(Premiacoes::getNome)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		atriz.entrySet()
		.stream()
		.max(Comparator.comparing(Map.Entry::getValue))
		.ifPresent(premio -> System.out.println("A atriz entre 20 e 30 anos mais vezes premiada foi"
				+ premio.getKey()));
	}*/
	
	private static void maisDeUmOscar(List<Premiacoes> premiacoesList) {
		Map<String, Long> vencedores = premiacoesList
				.stream()
				.map(Premiacoes::getNome)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println("Atores e atrizes que receberam mais de um Oscar: ");
		vencedores.entrySet()
		.stream()
		.filter(it -> it.getValue() > 1)
		.forEach(it -> System.out.println(" -" + it.getKey()));
	}
	
	private static void resumo(String nome, List<Premiacoes> listaCompleta) {
		List<Premiacoes> resumoArtista = listaCompleta.stream()
				.filter(artista -> artista.getNome().trim().equals(nome.trim()))
				.collect(Collectors.toList());
		if(resumoArtista != null && resumoArtista.size() > 0) {
			resumoArtista.forEach(analise -> System.out.println(analise.getAnalise()));
		} else {
			System.out.println(nome + " não encontrado!");
		}
	}
	
	private static String linha() {
		return "------------------------------------------------------------------------------------------------------------";
	}
}
