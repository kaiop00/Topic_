import java.util.List;
import java.util.ArrayList;

public class Topic {
	private int qtdPrioritarios;
    private int quantidade;
    private final int capacidade;
    private int vagas;
    private final List <Passageiro> idoso = new ArrayList<>(); 
    private final List <Passageiro> normais = new ArrayList<>();

    public Topic(int capacidade, int qtdPrioritarios) {
        if (qtdPrioritarios > capacidade)
        this.capacidade = capacidade;
        this.qtdPrioritarios = qtdPrioritarios;
        this.quantidade = capacidade - qtdPrioritarios;
        this.vagas = capacidade;
        criarListaVazia();
    }

    public List<Passageiro> getAssentosNormais(){
        return normais;
    }

    public List<Passageiro> getAssentosPrioritarios(){
        return idoso;
    }

    public int getNumeroAssentosPrioritarios() {
        return qtdPrioritarios;
    }
    
    public int getNumeroAssentosNormais() {
        return quantidade;
    }

    public Passageiro getPassageiroAssentoNormal(int lugar) {
        if(normais.get(lugar).getNome().equals("=")){
            return null;
        }
        return normais.get(lugar);
    }

    public Passageiro getPassageiroAssentoPrioritario(int lugar) {
        if(idoso.get(lugar).getNome().equals("@")){
            return null;
        }
        return idoso.get(lugar);
    }

    public int getVagas() {
        return vagas;
    }

    private void criarListaVazia() {
        for(int i=0; i < qtdPrioritarios; i++){
            idoso.add(new Passageiro("@", 0));
        }
        for (int i = qtdPrioritarios + 1;  i <= capacidade; i++) {
            normais.add(new Passageiro("=", 0));
        }
    }

    private void incluirPrioritarios(Passageiro passageiro){
        for (Passageiro e : idoso) {
            if(e.getNome().equals("@")){
                idoso.set(idoso.indexOf(e), passageiro);
                break;
            }
        }
    }

    private void incluirNormais(Passageiro passageiro){
        for (Passageiro e : normais) {
            //int index = 
            if(e.getNome().equals("=")){
                normais.set(normais.indexOf(e), passageiro);
                break;
            }
        }
    }

    public boolean subir(Passageiro passageiro) {
        if(getVagas() == 0){
            return false;
        }else{
            if(passageiro.ePrioritario()){
                if(getNumeroAssentosPrioritarios() > 0){
                    qtdPrioritarios --;
                    vagas--;
                    incluirPrioritarios(passageiro);
                    return true;
                }else{
                    if(getNumeroAssentosNormais() > 0){
                        quantidade --;
                        vagas--;
                        incluirNormais(passageiro);
                    return true;
                    }
                }
            }else{
                if(getNumeroAssentosNormais() > 0){
                   quantidade --;
                    vagas--;
                    incluirNormais(passageiro);
                    return true;
                }else{
                    if(getNumeroAssentosPrioritarios() > 0){
                        qtdPrioritarios--;
                        vagas--;
                        incluirPrioritarios(passageiro);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean descer(String nome) {
        for (Passageiro passageiro : idoso) {    
            if(passageiro.getNome().equals(nome)){
                idoso.set(idoso.indexOf(passageiro), new Passageiro("@", 0));
                qtdPrioritarios++;
                vagas++;
                return true;
            }
        }
        for (Passageiro passageiro2 : normais) {
            if(passageiro2.getNome().equals(nome)){
                normais.set(normais.indexOf(passageiro2), new Passageiro("=", 0));
                idoso;
                vagas++;
                return true;
            }
        }
        return false;
    }

    public String toString(){
        StringBuilder lista = new StringBuilder(); 

        for (Passageiro passageiro : idoso) {
            if(passageiro.getNome().equals("@")){
                lista.append("@ ");
            }else{
                lista.append("@" + passageiro.getNome() + " ");
            }
        }

        for (Passageiro passageiro : normais) {
            if(passageiro.getNome().equals("=")){
                lista.append("= ");
            }else{
                lista.append("=" + passageiro.getNome() + " ");
            }
        }
        return "[" + lista + "]";
    }

}