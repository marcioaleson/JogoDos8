package jogoDos8;

/**
 *
 * @author
 */
public class No {

    private Estado estado;
    private No pai;
    private String acao;
    private int custoCaminho;
    private int profundidade;
    private boolean expandido = false;

    No(Estado estado, No pai, String acao, int custoCaminho, int profundidade) {
        this.estado = estado;
        this.custoCaminho = custoCaminho;
        this.acao = acao;
        this.profundidade = profundidade;
        this.pai = pai;
    }
    
    public void exibirNo(){
        
        System.out.println("Estado:             " + this.getEstado());
        System.out.println("Pai:                " + this.getPai());
        System.out.println("Ação:               " + this.getAcao());
        System.out.println("Custo do caminho:   " + this.getCustoCaminho());
        System.out.println("Profundidade:       " + this.getProfundidade());
    
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public void setCustoCaminho(int custoCaminho) {
        this.custoCaminho = custoCaminho;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }

    public String getAcao() {
        return acao;
    }

    public int getCustoCaminho() {
        return custoCaminho;
    }

    public Estado getEstado() {
        return estado;
    }

    public No getPai() {
        return pai;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public void setExpandido(boolean expandido) {
        this.expandido = expandido;
    }
    
    public boolean getExpandido(){
        return this.expandido;
    }
}
