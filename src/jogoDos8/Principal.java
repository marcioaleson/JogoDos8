	package jogoDos8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author
 */
public class Principal {

    public static void main(String[] args) {

        exibirMenu();

        String opcao;
        do {
            System.out.print("\nSua opção é: ");
            Scanner entrada = new Scanner(System.in);
            opcao = entrada.nextLine();
            if (!opcao.equals("q")) {
                switch (opcao) {
                    case "a":
                        System.out.println("\nBUSCA CEGA EM LARGURA ESCOLHIDO!");
                        System.out.print("\nTabuleiro do professor ou aleatório? 1 - padrão / 2 - aleatório\n->");
                        String i = entrada.nextLine();

                        if (i.equals("1")) {

                            EstruturaJogo tab = new EstruturaJogo();
                            tab.preencherTabuleiroPadrao();
                            System.out.println("\nTabuleiro inicialmente:");
                            tab.exibirTabuleiro();
                            BuscaCegaLargura a = new BuscaCegaLargura();
                            List<No> borda = new ArrayList();
                            System.out.println("\nBuscando ...");
                            EstruturaJogo saida = a.busca(tab, borda);
                            System.out.println("\nO algoritmo Busca em Largura resolveu o problema com custo: " + saida.getMovimentos() + " passos!");

                        } else {

                            EstruturaJogo tab = new EstruturaJogo();
                            tab.embaralharTabuleiro();
                            System.out.println("\nTabuleiro inicialmente:");
                            tab.exibirTabuleiro();
                            BuscaCegaLargura a = new BuscaCegaLargura();
                            List<No> borda = new ArrayList();
                            System.out.println("\nBuscando ...");
                            EstruturaJogo saida = a.busca(tab, borda);
                            System.out.println("\nO algoritmo Busca em Largura resolveu o problema com custo: " + saida.getMovimentos() + " passos!");

                        }

                        break;
                    case "b":
                        System.out.println("\nBUSCA CEGA EM PROFUNDIDADE ESCOLHIDO!");
                        System.out.print("\nTabuleiro do professor ou aleatorio? 1 - padrão / 2 - aleatório\n->");
                        String k = entrada.nextLine();

                        if (k.equals("1")) {

                            EstruturaJogo tab = new EstruturaJogo();
                            tab.preencherTabuleiroPadrao();
                            System.out.println("\nTabuleiro inicialmente:");
                            tab.exibirTabuleiro();
                            BuscaCegaProfundidade a = new BuscaCegaProfundidade();
                            List<No> borda = new ArrayList();
                            System.out.println("\nBuscando ...");
                            EstruturaJogo saida = a.busca(tab, borda);
                            System.out.println("\nO algoritmo Busca em Profundidade resolveu o problema com custo: " + saida.getMovimentos());

                        } else {

                            EstruturaJogo tab = new EstruturaJogo();
                            tab.embaralharTabuleiro();
                            System.out.println("\nTabuleiro inicialmente:");
                            tab.exibirTabuleiro();
                            BuscaCegaProfundidade a = new BuscaCegaProfundidade();
                            List<No> borda = new ArrayList();
                            System.out.println("\nBuscando ...");
                            EstruturaJogo saida = a.busca(tab, borda);
                            System.out.println("\nO algoritmo Busca em Profundidade resolveu o problema com custo: " + saida.getMovimentos());

                        }
                        break;
                    case "c":
                        System.out.println("\nBUSCA GULOSA COM HEURÍSTICA ESCOLHIDO!");
                        System.out.print("\nTabuleiro do professor ou aleatorio? 1 - padrão / 2 - aleatório\n->");
                        String p = entrada.nextLine();

                        if (p.equals("1")) {

                            EstruturaJogo tab = new EstruturaJogo();
                            tab.preencherTabuleiroPadrao();
                            System.out.println("\nTabuleiro inicialmente:");
                            tab.exibirTabuleiro();
                            BuscaHeuristicaGulosa a = new BuscaHeuristicaGulosa();
                            List<NoGuloso> borda = new ArrayList();
                            System.out.println("\nBuscando ...");
                            EstruturaJogo saida = a.busca(tab, borda);
                            System.out.println("\nO algoritmo Busca Heuristica Gulosa resolveu o problema com custo: " + saida.getMovimentos());

                        } else {

                            EstruturaJogo tab = new EstruturaJogo();
                            tab.embaralharTabuleiro();
                            System.out.println("\nTabuleiro inicialmente:");
                            tab.exibirTabuleiro();
                            BuscaHeuristicaGulosa a = new BuscaHeuristicaGulosa();
                            List<NoGuloso> borda = new ArrayList();
                            System.out.println("\nBuscando ...");
                            EstruturaJogo saida = a.busca(tab, borda);
                            System.out.println("\nO algoritmo Busca Heurística Gulosa resolveu o problema com custo: " + saida.getMovimentos());

                        }
                        break;
                    case "d":
                        System.out.println("\nA* ESCOLHIDO!");
                        System.out.print("\nTabuleiro do professor ou aleatório? 1 - padrão / 2 - aleatório\n->");
                        String g = entrada.nextLine();

                        if (g.equals("1")) {

                            EstruturaJogo tab = new EstruturaJogo();
                            tab.preencherTabuleiroPadrao();
                            System.out.println("\nTabuleiro inicialmente:");
                            tab.exibirTabuleiro();
                            BuscaHeuristicaA a = new BuscaHeuristicaA();
                            List<NoGuloso> borda = new ArrayList();
                            System.out.println("\nBuscando ...");
                            EstruturaJogo saida = a.busca(tab, borda);
                            System.out.println("\nO algoritmo Busca A* resolveu o problema com custo: " + saida.getMovimentos());

                        } else {

                            EstruturaJogo tab = new EstruturaJogo();
                            tab.embaralharTabuleiro();
                            System.out.println("\nTabuleiro inicialmente:");
                            tab.exibirTabuleiro();
                            BuscaHeuristicaA a = new BuscaHeuristicaA();
                            List<NoGuloso> borda = new ArrayList();
                            System.out.println("\nBuscando ...");
                            EstruturaJogo saida = a.busca(tab, borda);
                            System.out.println("\nO algoritmo Busca A* resolveu o problema com custo: " + saida.getMovimentos());
                        }
                        break;
                    case "e":
                        System.out.println("\nMODO DIVIRTA-SE ESCOLHIDO!\n");
                        JogoDosOito s = new JogoDosOito();
                        s.divirtaSe();
                        break;
                    default:
                        System.out.println("\nOção digitada naoo é reconhecida!");
                }

                System.out.print("\n\nEscolher outro modo ou deseja sair? n - Novo modo / q - Sair\n->");
                opcao = entrada.nextLine();

                if (opcao.equals("q")) {
                    System.out.println("FIM DE EXECUCÃO!");
                } else {
                    exibirMenu();
                }
            }
        } while (!opcao.equals("q"));
    }

    public static void exibirMenu() {
        System.out.println("\t :: Bem-vindo ao fantástico mundo do JOGO DOS 8 ::\n\n");
        System.out.println("Qual a opção voçê deseja:");
        System.out.println("\n a - Busca Cega em largura");
        System.out.println(" b - Busca Cega em profundidade");
        System.out.println(" c - Busca gulosa com heurística");
        System.out.println(" d - A*");
        System.out.println(" e - Modo divirta-se!");
    }
}
