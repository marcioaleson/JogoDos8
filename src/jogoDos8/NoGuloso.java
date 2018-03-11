package jogoDos8;

/**
 *
 * @author
 */
public class NoGuloso {

    private Estado estado;
    private NoGuloso pai;
    private String acao;
    private int custoCaminho;
    private int profundidade;
    private int pontos; // Diferença do No normal, pois para o algoritmo guloso é nescessario se guardar a pontuação do nó
    private boolean expandido = false;

    NoGuloso(Estado estado, NoGuloso pai, String acao, int custoCaminho, int profundidade) {
        this.estado = estado;
        this.custoCaminho = custoCaminho;
        this.acao = acao;
        this.profundidade = profundidade;
        this.pai = pai;
        pontos = 1000;
    }

    public void exibirNo() {

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

    public void setPai(NoGuloso pai) {
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

    public NoGuloso getPai() {
        return pai;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int[][] tabuleiro) {
        int ponto = 0, X, Y;
        int referencia[][] = new int[3][3];
        // o cálculo do nó será eito apartir desta matriz
        referencia[0][0] = 1;
        referencia[1][0] = 2;
        referencia[2][0] = 3;
        referencia[0][1] = 4;
        referencia[1][1] = 5;
        referencia[2][1] = 6;
        referencia[0][2] = 7;
        referencia[1][2] = 8;
        referencia[2][2] = 0;

        for (int i = 0; i <= 8; i++) {
            X = i % 3;
            Y = i / 3;
            if (tabuleiro[X][Y] != referencia[X][Y]) {
                ponto++;
            }
        }
        this.pontos = ponto;
    }

    public void setPontosA(int[][] tabuleiro) {

        int distanciaMan = 0;
        int referencia[][] = new int[3][3];

        // o cálculo do nó será eito apartir desta matriz
        referencia[0][0] = 1;
        referencia[1][0] = 2;
        referencia[2][0] = 3;
        referencia[0][1] = 4;
        referencia[1][1] = 5;
        referencia[2][1] = 6;
        referencia[0][2] = 7;
        referencia[1][2] = 8;
        referencia[2][2] = 0;     
        
        for (int i = 0; i <= 8; i++) {
            int[] recebido = this.posicaoDeValor(i, tabuleiro);
            int[] referen = this.posicaoDeValor(i, referencia);
            distanciaMan += (Math.abs(recebido[0] - referen[0]) + Math.abs(recebido[1] - referen[1]));

        }
        this.pontos = distanciaMan;
    }

    public void setPontos(int ponto) {
        this.pontos = ponto;
    }

    public void setExpandido(boolean expandido) {
        this.expandido = expandido;
    }

    public boolean getExpandido() {
        return this.expandido;
    }

    //retorna a posição de uma valor
    public int[] posicaoDeValor(int valor, int[][] tabuleiro) {
        int posicao[] = {-1, -1};
        if (valor >= 0 && valor <= 8) {
            for (int i = 0; i <= 8; i++) {
                // x = coluna, y = linha
                int X = i % 3, Y = i / 3;
                if (tabuleiro[X][Y] == valor) {
                    posicao[0] = X;
                    posicao[1] = Y;
                    return posicao;
                }
            }
            System.out.println("Valor passado não encontrado -> " + valor);
        } else {
            System.out.println("Erro: Valor passado é maior que 8 ou menor que 1!");
        }
        //caso valor passado não seja encontrado no tabuleiro
        return posicao;
    }
}
