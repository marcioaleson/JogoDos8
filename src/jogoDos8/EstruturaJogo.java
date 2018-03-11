package jogoDos8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author
 */
public class EstruturaJogo {

    private static final int x = 0;
    private static final int y = 1;
    private int[][] tabuleiro = new int[3][3];
    private int[] vazio = new int[2]; // coodenada [x][y]
    private int movimentos = 0;

    public void embaralharTabuleiro() {

        List<Integer> valores = new ArrayList<Integer>();
        movimentos = 0;

        //Popula o vetor valores com 0 até 8
        for (int i = 0; i <= 8; i++) {
            valores.add(i);
        }

        //embaralha valores do vetor
        Collections.shuffle(valores);

        //Adiciona valores no tabuleiro
        for (int i = 0; i <= 8; i++) {
            // x = coluna, y = linha
            int X = i % 3, Y = i / 3;
            this.tabuleiro[X][Y] = valores.get(i);
            //verifica se essa posicao é a vazia, se sim salva essa posià§ào
            if (valores.get(i) == 0) {
                vazio[x] = X;
                vazio[y] = Y;
            }
        }
    }

    // Faz com que todos os valores da matriz sejam 0
    public void limparTabuleiro() {

        movimentos = 0;
        vazio[x] = 0;
        vazio[y] = 0;
        for (int i = 1; i <= 8; i++) {
            // x = coluna, y = linha
            int X = i % 3, Y = i / 3;
            this.tabuleiro[X][Y] = 0;
        }
    }

    public void exibirTabuleiro() {

        int i = 0;
        System.out.print("_____________\n| ");
        do {
            // X = coluna, Y = linha
            int X = i % 3, Y = i / 3;
            System.out.print(this.tabuleiro[X][Y] + " | ");
            i++;
            if (X == 2) {
                System.out.print("\n_____________\n| ");
            }

        } while (i <= 8);
    }

    //Preenche o tabuleiro com os valores que o professor passou
    public void preencherTabuleiroPadrao() {

        List<Integer> valores = new ArrayList<Integer>();
        movimentos = 0;
       
        //Popula o vetor valores com 2 5 1 4 8 7 3 6
        valores.add(2); valores.add(5); valores.add(1);
        valores.add(4); valores.add(0); valores.add(8);
        valores.add(7); valores.add(3); valores.add(6);//espaço em branco é representado por zero

        //Adiciona valores no tabuleiro
        for (int i = 0; i <= 8; i++) {
            // x = coluna, y = linha
            int X = i % 3, Y = i / 3;
            this.tabuleiro[X][Y] = valores.get(i);
        }

        vazio[x] = 1;
        vazio[y] = 1;
    }

    public boolean moverEsquerda() {
        //se nào for movimento inválido, ou seja branco nào estiver na primeira coluna
        if (vazio[x] != 2) {

            movimentos++;
            //pega o valor da variável à  direta do vazio
            int val = this.valorDePosicao((vazio[x] + 1), vazio[y]);
            //atualizar o valor do vazio para valor mudado
            this.atribuirValorEmPosicao(vazio[x], vazio[y], val);
            //Atribui 0 a nova posicao vazia
            this.atribuirValorEmPosicao((vazio[x] + 1), vazio[y], 0);
            //atualiza a posicao do vazio
            vazio[x] = vazio[x] + 1;
            //this.ganhou(); // Para obter um melhor resultado nos algoritmos de busca comente esta linha
            return true;
        } else {
            //System.out.println("Movimento para esquerda inválido");
        }
        return false;
    }

    public boolean moverDireita() {
        //se nào for movimento inválido, ou seja branco nào estiver na àºltima coluna
        if (vazio[x] != 0) {

            movimentos++;
            //pega o valor da variável à  direta do vazio
            int val = this.valorDePosicao((vazio[x] - 1), vazio[y]);
            //atualizar o valor do vazio para valor mudado
            this.atribuirValorEmPosicao(vazio[x], vazio[y], val);
            //Atribui 0 a nova posicao vazia
            this.atribuirValorEmPosicao((vazio[x] - 1), vazio[y], 0);
            //atualiza a posicao do vazio
            vazio[x] = vazio[x] - 1;
            //this.ganhou();// para obter um melhor resultado nos algoritmos de busca comente esta linha
            return true;
        } else {
            //System.out.println("Movimento para direita inválido");
        }
        return false;
    }

    public boolean moverCima() {
        //se nào for movimento inválido, ou seja branco nào estiver na àºltima linha
        if (vazio[y] != 2) {

            movimentos++;
            //pega o valor da variável à  direta do vazio
            int val = this.valorDePosicao(vazio[x], (vazio[y] + 1));
            //atualizar o valor do vazio para valor mudado
            this.atribuirValorEmPosicao(vazio[x], vazio[y], val);
            //Atribui 0 a nova posicao vazia
            this.atribuirValorEmPosicao(vazio[x], (vazio[y] + 1), 0);
            //atualiza a posicao do vazio
            vazio[y] = vazio[y] + 1;
            //this.ganhou();// para obter um melhor resultado nos algoritmos de busca comente esta linha
            return true;
        } else {
            //System.out.println("Movimento para cima inválido");
        }
        return false;

    }

    public boolean moverBaixo() {
        //se nào for movimento inválido, ou seja branco nào estiver na primeira linha
        if (vazio[y] != 0) {
            movimentos++;
            //pega o valor da variável à  direta do vazio
            int val = this.valorDePosicao(vazio[x], (vazio[y] - 1));
            //atualizar o valor do vazio para valor mudado
            this.atribuirValorEmPosicao(vazio[x], vazio[y], val);            
            //Atribui 0 a nova posicao vazia
            this.atribuirValorEmPosicao(vazio[x], (vazio[y] - 1), 0);
            //atualiza a posicao do vazio
            vazio[y] = vazio[y] - 1;
            //this.ganhou();
            return true;
        } else {
            //System.out.println("Movimento para baixo inválido");
        }
        
        return false;
    }

    public void atribuirValorEmPosicao(int X, int Y, int valor) {
        this.tabuleiro[X][Y] = valor;
    }

    public int valorDePosicao(int X, int Y) {
        return this.tabuleiro[X][Y];
    }

    //retorna a posià§ào de uma valor
    public int[] posicaoDeValor(int valor) {
        int posicao[] = {-1, -1};
        if (valor >= 0 && valor <= 8) {
            for (int i = 0; i <= 8; i++) {
                // x = coluna, y = linha
                int X = i % 3, Y = i / 3;
                if (this.tabuleiro[X][Y] == valor) {
                    posicao[0] = X;
                    posicao[1] = Y;
                    return posicao;
                }
            }
            System.out.println("Valor passado nào encontrado");
        } else {
            System.out.println("Erro: Valor passado é maior que 8 ou menor que 1!");
        }
        //caso valor passado nào seja encontrado no tabuleiro
        return posicao;
    }

    public int[] getVazio() {
        return vazio;
    }

    public void setVazio(int[] vazio) {
        this.vazio[x] = vazio[0];
        this.vazio[y] = vazio[1];
    }

    public boolean ganhou() {

        for (int i = 0; i < 8; i++) {
            int X = i % 3, Y = i / 3;
            
            if (tabuleiro[X][Y] != (i + 1)) {
                return false;
            }
        }
        System.out.println("\nPARABéNS SOLUÇAO ENCONTRADA ! ! !\n");
        return true;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(int[][] tabuleiro) {
        
        for (int i = 0; i <= 8; i++) {
            // x = coluna, y = linha
            int X = i % 3, Y = i / 3;
            this.tabuleiro[X][Y] = tabuleiro[X][Y];
        }
    }
    
    public String idEstados(){
    
        String id = "";
        for (int i = 0; i <= 8; i++) {
            // x = coluna, y = linha
            int X = i % 3, Y = i / 3;
            id = id + tabuleiro[X][Y];
        }
        return id;
    }
    
    public void decrementarMovimento(){
        this.movimentos-=2;
    }

    public int getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(int movimentos) {
        this.movimentos = movimentos;
    }
}
