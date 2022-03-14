
public class Premiacoes {

	public String index;
	public String ano;
	public String idade;
	public String nome;
	public String filme;
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFilme() {
		return filme;
	}

	public void setFilme(String filme) {
		this.filme = filme;
	}

	public Premiacoes(String index, String ano, String idade, String nome, String filme) {
		this.index = index;
		this.ano = ano;
		this.idade = idade;
		this.nome = nome;
		this.filme = filme;
	}
	
	public static Premiacoes of (String linha) {
		String[] split = linha.split(";");
		return new Premiacoes(
				split[0],
				split[1],
				split[2],
				split[3],
				split[4]);
	}
	
	public String getAnalise() {
        return  this.getNome().trim() + " ganhou o oscar em"
                + this.getAno()
                + " com" + this.getIdade()
                +" anos de idade, com o filme"
                + this.getFilme() + ".";
    }
	
	 @Override
	 public String toString() {		 
		 return "Premiacoes {" +
	                "index = " + index +
	                ", ano = " + ano +
	                ", idade = " + idade +
	                ", nome = " + nome +
	                ", filme = " + filme +
	                '}';
	 }
}
