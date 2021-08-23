public class Passageiro {
	
	private int idade;
	private String nome = "";
    
	public Passageiro(String nome, int idade) {
    	this.idade=idade;
    	this.nome=nome;
    }
    public boolean ePrioritario() {
        if(idade<64) {
        	return false;
        }else return true;
    }

    String getNome() {
        return nome;
    }
}