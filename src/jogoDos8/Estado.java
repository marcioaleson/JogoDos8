
/*
 * Para atender a Especificação do livro de que o nó da árvore tem que ter 
 * o estado, ou seja, a disposição das peças do tabuleiro em determinado tempo.
 */

package jogoDos8;

/**
 *
 * @author
 */
public class Estado {

    private int[][] tabuleiro = new int[3][3];

    Estado(int[][] tabuleiro) {
        for (int i = 0; i <= 8; i++) {
            // x = coluna, y = linha
            int X = i % 3, Y = i / 3;
            this.tabuleiro[X][Y] = tabuleiro[X][Y];
        }
    }
    

    public void setTabuleiro(int[][] tabuleiro) {
    
        for (int i = 0; i <= 8; i++) {
            // x = coluna, y = linha
            int X = i % 3, Y = i / 3;
            this.tabuleiro[X][Y] = tabuleiro[X][Y];
        }
    
    }
    
    public int[][] getTabuleiro() {
    
        return tabuleiro;
    
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
}
