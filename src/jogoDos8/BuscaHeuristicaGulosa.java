package jogoDos8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author
 */
public class BuscaHeuristicaGulosa {

    EstruturaJogo saida;
    int menorPontuacao = 500; // menorPontuacao ser igual a 500 esta no início da execução, ser igual a 10000 não é possível expandir
    List<String> idEstados = new ArrayList();
    NoGuloso noEscolhido;

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
            
            NoGuloso noParaExpandir = borda.get(0);//primeiro caso
                    
            if(this.menorPontuacao != 10000 && this.menorPontuacao != 500){ // situação normal para encontrar a menor pontuação e o nó assim ser escolhido
                
                int aux23 = (borda.size()-1);
                do{
                    noParaExpandir = borda.get(aux23);
                    aux23--;
                } while(!noEscolhido.getAcao().equals(noParaExpandir.getAcao()));
            }
            
            if(noParaExpandir.getExpandido() == true){ // se o nó de menor custo já foi expandido, um nó irmão é então escolhido
                int aux = (borda.size()-1);
                do{
                    aux--;
                    if(aux == 0)
                        return null;
                    noParaExpandir = borda.get(aux);
                }while(noParaExpandir.getExpandido());
                borda.get(aux).setExpandido(true);
            }         
            
            EstruturaJogo problema = new EstruturaJogo();
            problema.setTabuleiro(noParaExpandir.getEstado().getTabuleiro());
            problema.setVazio(problema.posicaoDeValor(0));
            problema.setMovimentos(noParaExpandir.getProfundidade());

            if (problema.ganhou()) { // aplicado ao Estado[nó] - nó do estado atual expandido

                end = System.currentTimeMillis();
                diff = end - init;
                System.out.println("Foram expandidos " + borda.size() + " nós no processo de busca Heurística Gulosa!");
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

    public ArrayList<NoGuloso> expandir(NoGuloso d, EstruturaJogo entrada) {

        ArrayList<NoGuloso> sucessores = new ArrayList();
        this.menorPontuacao = 10000; // valor alto para saber se algum no foi expandido, existe a possilidade de todos os nós forem repetidos e assim o nó não ser expandido
        noEscolhido = null;
        d.setExpandido(true);
        
        //São quatro possibilidades de movimentos por isso quatro ifÂ´s
        if (entrada.moverBaixo()) {
            String aux = entrada.idEstados();
            if (!idEstados.contains(aux)) {
                Estado estado = new Estado(entrada.getTabuleiro());
                NoGuloso s = new NoGuloso(estado, d, "Baixo", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                s.setPontos(entrada.getTabuleiro());                
                if(s.getPontos() <= this.menorPontuacao){
                    this.menorPontuacao = s.getPontos();
                    noEscolhido = null;
                    noEscolhido = new NoGuloso(estado, d, "Baixo", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                    noEscolhido.setPontos(s.getPontos());
                }
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
                NoGuloso s = new NoGuloso(estado, d, "Cima", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                s.setPontos(entrada.getTabuleiro());                
                if(s.getPontos() <= this.menorPontuacao){
                    this.menorPontuacao = s.getPontos();
                    noEscolhido = null;
                    noEscolhido = new NoGuloso(estado, d, "Cima", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                    noEscolhido.setPontos(s.getPontos());
                }
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
                NoGuloso s = new NoGuloso(estado, d, "Direita", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                s.setPontos(entrada.getTabuleiro());
                if(s.getPontos() <= this.menorPontuacao){
                    this.menorPontuacao = s.getPontos();
                    noEscolhido = null;
                    noEscolhido = new NoGuloso(estado, d, "Direita", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                    noEscolhido.setPontos(s.getPontos());
                }
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
                NoGuloso s = new NoGuloso(estado, d, "Esquerda", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                s.setPontos(entrada.getTabuleiro());
                if(s.getPontos() <= this.menorPontuacao){
                    this.menorPontuacao = s.getPontos();
                    noEscolhido = null;
                    noEscolhido = new NoGuloso(estado, d, "Esquerda", (d.getCustoCaminho() + 1), (d.getProfundidade() + 1));
                    noEscolhido.setPontos(s.getPontos());
                }
                sucessores.add(s);
                idEstados.add(aux);
            }
            entrada.moverDireita();
            entrada.decrementarMovimento();
        }
        return sucessores;
    }
}