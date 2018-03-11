package jogoDos8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author
 */
public class BuscaCegaProfundidade {

    EstruturaJogo saida;
    List<String> idEstados = new ArrayList();
    
    public EstruturaJogo busca(EstruturaJogo entrada, List<No> borda) {
        Estado tabuleiroInicialmente = new Estado(entrada.getTabuleiro());
        No raiz = new No(tabuleiroInicialmente, null, "nenhuma", 0, 0);
        borda.add(raiz);
        int primeiroNo = 0; // Controle de profundidade
        long init, end, diff; // Variáveis para cálculo de tempo
        init = System.currentTimeMillis();

        do {
            int tamanhoAnt = borda.size();
            System.out.println(".");
            if (borda.isEmpty()) {
                return null;
            }
            
            No noParaExpandir = borda.get((borda.size()-1));
            
            if(noParaExpandir.getExpandido() == true){
                int aux = (borda.size()-1);
                do{
                    aux--;
                    if(aux == 0)
                        return null;
                    noParaExpandir = borda.get(aux);
                }while(noParaExpandir.getExpandido());
                borda.get(aux).setExpandido(true);
            } else {
                borda.get((borda.size()-1)).setExpandido(true);
            }

            EstruturaJogo problema = new EstruturaJogo();
            problema.setTabuleiro(noParaExpandir.getEstado().getTabuleiro());
            problema.setVazio(problema.posicaoDeValor(0));
            problema.setMovimentos(noParaExpandir.getProfundidade());

            if (problema.ganhou()) { // aplicado ao Estado[nó] - nó do estado atual expandido

                end = System.currentTimeMillis();
                diff = end - init;
                System.out.println("Foram expandidos " + borda.size() + " nós no processo de busca em profundidade!");
                System.out.println("Profundidade da árvore: " + primeiroNo);
                System.out.println("A Solução foi encontrada em: " + (diff / 1000) + " segundos");
                System.out.println("\nDeseja visualizar os movimentos feitos para encontrar a solução? s - Sim / n - Não\n->");
                String op;
                Scanner entry = new Scanner(System.in);
                op = entry.nextLine();

                if (op.equals("s")) {

                    int profundidadeAuxiliar = noParaExpandir.getProfundidade();
                    List<No> listaAuxiliar = new ArrayList();
                    No noAuxiliar = noParaExpandir;

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
            primeiroNo++;
            if(borda.size() == tamanhoAnt){
                System.out.println("nao Mudou de tamanho!");
            }
        } while (true);
    }

    public ArrayList<No> expandir(No d, EstruturaJogo entrada) {

        ArrayList<No> sucessores = new ArrayList();
        //São quatro possibilidades de movimentos por isso quatro if´s
        d.setExpandido(true);
        
        if (entrada.moverBaixo()) {
            String aux = entrada.idEstados();
            if (!idEstados.contains(aux)) {
                Estado estado = new Estado(entrada.getTabuleiro());
                No s = new No(estado, d, "Baixo", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                sucessores.add(s);
                idEstados.add(aux);
            }
            entrada.moverCima();
            entrada.decrementarMovimento();

        }

        if (entrada.moverCima()) {
            String aux = entrada.idEstados();
            if (!idEstados.contains(aux)) {
                Estado estado = new Estado(entrada.getTabuleiro());
                No s = new No(estado, d, "Cima", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                sucessores.add(s);
                idEstados.add(aux);
            }
            entrada.moverBaixo();
            entrada.decrementarMovimento();
        }

        if (entrada.moverDireita()) {
            String aux = entrada.idEstados();
            if (!idEstados.contains(aux)) {
                Estado estado = new Estado(entrada.getTabuleiro());
                No s = new No(estado, d, "Direita", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                sucessores.add(s);
                idEstados.add(aux);
            }
            entrada.moverEsquerda();
            entrada.decrementarMovimento();
        }

        if (entrada.moverEsquerda()) {
            String aux = entrada.idEstados();
            if (!idEstados.contains(aux)) {
                Estado estado = new Estado(entrada.getTabuleiro());
                No s = new No(estado, d, "Esquerda", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                sucessores.add(s);
                idEstados.add(aux);
            }
            entrada.moverDireita();
            entrada.decrementarMovimento();
        }
        return sucessores;
    }
}
