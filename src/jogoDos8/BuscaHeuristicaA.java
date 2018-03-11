package jogoDos8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author
 */
public class BuscaHeuristicaA {

    EstruturaJogo saida;
    List<String> idEstados = new ArrayList<String>();

    public EstruturaJogo busca(EstruturaJogo entrada, List<NoGuloso> borda) {

        Estado tabuleiroInicialmente = new Estado(entrada.getTabuleiro());
        NoGuloso raiz = new NoGuloso(tabuleiroInicialmente, null, "nenhuma", 0, 0);
        borda.add(raiz);
        long init, end, diff;
        init = System.currentTimeMillis();

        do {

            if (borda.isEmpty()) {
                return null;
            }

            NoGuloso noParaExpandir = borda.get(0);

            if (!borda.get(0).getExpandido()) {
                borda.get(0).setExpandido(true);
            }

            if (borda.size() >= 2) { // se não for o primeiro nó - nó raiz
                int idx = -1, aux2 = 500;
                for (int aux = 0; aux < borda.size(); aux++) { // Percorrer a lista
                    if (!borda.get(aux).getExpandido() && borda.get(aux).getCustoCaminho() < aux2) {
                        aux2 = borda.get(aux).getCustoCaminho();
                        idx = aux;
                    }
                }
                noParaExpandir = borda.get(idx);
                borda.get(idx).setExpandido(true);
            }

            EstruturaJogo problema = new EstruturaJogo();
            problema.setTabuleiro(noParaExpandir.getEstado().getTabuleiro());
            problema.setVazio(problema.posicaoDeValor(0));
            problema.setMovimentos(noParaExpandir.getProfundidade());

            if (problema.ganhou()) { // aplicado ao Estado[nó] - nó do estado atual expandido

                end = System.currentTimeMillis();
                diff = end - init;
                System.out.println("Foram expandidos " + borda.size() + " nós no processo de busca A*!");
                System.out.println("Profundidade da árvore: " + noParaExpandir.getProfundidade());
                System.out.println("A Solução foi encontrada em: " + (diff / 1000) + " segundos");
                System.out.println("\nDeseja visualizar os movimentos feitos para encontrar a solução? s - Sim / n - Não\n->");
                String op;
                Scanner entry = new Scanner(System.in);
                op = entry.nextLine();

                if (op.equals("s")) {

                    int profundidadeAuxiliar = noParaExpandir.getProfundidade();
                    List<NoGuloso> listaAuxiliar = new ArrayList();
                    NoGuloso noAuxiliar = noParaExpandir;

                    for (int i = 0; i < profundidadeAuxiliar; i++) {
                        listaAuxiliar.add(noAuxiliar);
                        noAuxiliar = noAuxiliar.getPai();
                    }

                    entrada.exibirTabuleiro();

                    for (int i = (profundidadeAuxiliar - 1); i >= 0; i--) {
                        System.out.println("\n\nMovimento para " + listaAuxiliar.get(i).getAcao() + ":");
                        listaAuxiliar.get(i).getEstado().exibirTabuleiro();
                    }
                }
                return saida = problema;
            }
            borda.addAll(expandir(noParaExpandir, problema));
        } while (true);
    }

    public ArrayList<NoGuloso> expandir(NoGuloso pai, EstruturaJogo entrada) {

        ArrayList<NoGuloso> sucessores = new ArrayList();
        pai.setExpandido(true);

        //São quatro possibilidades de movimentos por isso quatro if´s
        if (!pai.getAcao().equals("Cima")) {
            if (entrada.moverBaixo()) {
                Estado estado = new Estado(entrada.getTabuleiro());
                NoGuloso s = new NoGuloso(estado, pai, "Baixo", 0, (pai.getProfundidade() + 1));
                s.setPontosA(entrada.getTabuleiro());
                s.setCustoCaminho(s.getPontos() + s.getProfundidade());
                sucessores.add(s);
                entrada.moverCima();// retornado movimento
                entrada.decrementarMovimento();
            }
        }

        if (!pai.getAcao().equals("Baixo")) {
            if (entrada.moverCima()) {
                Estado estado = new Estado(entrada.getTabuleiro());
                NoGuloso s = new NoGuloso(estado, pai, "Cima", 0, (pai.getProfundidade() + 1));
                s.setPontosA(entrada.getTabuleiro());
                s.setCustoCaminho(s.getProfundidade() + s.getPontos());
                sucessores.add(s);
                entrada.moverBaixo();
                entrada.decrementarMovimento();
            }
        }

        if (!pai.getAcao().equals("Esquerda")) {
            if (entrada.moverDireita()) {
                Estado estado = new Estado(entrada.getTabuleiro());
                NoGuloso s = new NoGuloso(estado, pai, "Direita", 0, (pai.getProfundidade() + 1));
                s.setPontosA(entrada.getTabuleiro());
                s.setCustoCaminho(s.getProfundidade() + s.getPontos());
                sucessores.add(s);
                entrada.moverEsquerda();
                entrada.decrementarMovimento();
            }
        }

        if (!pai.getAcao().equals("Direita")) {
            if (entrada.moverEsquerda()) {
                Estado estado = new Estado(entrada.getTabuleiro());
                NoGuloso s = new NoGuloso(estado, pai, "Esquerda", 0, (pai.getProfundidade() + 1));
                s.setPontosA(entrada.getTabuleiro());
                s.setCustoCaminho(s.getProfundidade() + s.getPontos());
                sucessores.add(s);
                entrada.moverDireita();
                entrada.decrementarMovimento();
            }
        }
        return sucessores;
    }
}