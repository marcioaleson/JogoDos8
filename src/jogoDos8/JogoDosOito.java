package jogoDos8;

import java.util.Scanner;

/**
 * @author
 */
public class JogoDosOito {

    /**
     * @param args the command line arguments
     */
    public void divirtaSe() {

        EstruturaJogo jogo = new EstruturaJogo();
        jogo.limparTabuleiro();
        jogo.embaralharTabuleiro();
        System.out.println("Para sair pressione: \"q\"");
        System.out.println("Para novo tabueliro pressione: \"n\"");
        System.out.println("\nMover para esquerda: \"a\"");
        System.out.println("Mover para direita: \"d\"");
        System.out.println("Mover para baixo: \"s\"");
        System.out.println("Mover para cima: \"w\"");

        String opcao = "z";

        System.out.println("\nNovo Jogo:");
        jogo.exibirTabuleiro();
        do {
            System.out.print("\nSua opçao é: ");
            Scanner entrada = new Scanner(System.in);
            opcao = entrada.nextLine();
            if (!opcao.equals("q")) {
                switch (opcao) {
                    case "a":
                        System.out.println("Movimento para esquerda!");
                        jogo.moverEsquerda();
                        jogo.exibirTabuleiro();
                        break;
                    case "d":
                        System.out.println("Movimento para direita!");
                        jogo.moverDireita();
                        jogo.exibirTabuleiro();
                        break;
                    case "s":
                        System.out.println("Movimento para baixo!");
                        jogo.moverBaixo();
                        jogo.exibirTabuleiro();
                        break;
                    case "w":
                        System.out.println("Movimento para cima!");
                        jogo.moverCima();
                        jogo.exibirTabuleiro();
                        break;
                    case "n":
                        System.out.println("\nNovo Jogo:");
                        jogo.embaralharTabuleiro();
                        jogo.exibirTabuleiro();
                        break;
                    default:
                        System.out.println("Opcao digitada nao é reconhecida!");

                }
                if(jogo.ganhou()){
                    System.out.println("Jogar novamente? n - sim / q - nao:");
                    opcao = entrada.nextLine();
                }
            }
        } while (!opcao.equals("q"));
        System.out.println("Fim de jogo!");
    }
}
