package jogoDos8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author
 */
public class BuscaCegaLargura {

    EstruturaJogo saida;
    List<String> idEstados = new ArrayList();

    
    
    public EstruturaJogo busca(EstruturaJogo entrada, List<No> borda) {
        Estado tabuleiroInicialmente = new Estado(entrada.getTabuleiro());
        No raiz = new No(tabuleiroInicialmente, null, "nenhuma", 0, 0);
        borda.add(raiz);
        int primeiroNo = 0; // Controle de profundidade
        long init; 
        long end;  
        long diff;  
        init = System.currentTimeMillis(); 

        do {
            
            if (borda.isEmpty()) {
                return null;
            }

            No noParaExpandir = borda.get(primeiroNo);

            EstruturaJogo problema = new EstruturaJogo();
            problema.setTabuleiro(noParaExpandir.getEstado().getTabuleiro());
            problema.setVazio(problema.posicaoDeValor(0));
            problema.setMovimentos(noParaExpandir.getProfundidade());

            if (problema.ganhou()) { // aplicado ao Estado[nó] - nó do estado atual expandido
                
                end = System.currentTimeMillis();  
                diff = end - init;
                System.out.println("Foram expandidos "+borda.size()+" nós no processo de busca em largura!");
                System.out.println("Profundidade da árvore: "+noParaExpandir.getProfundidade());
                System.out.println("A Solução foi encontrada em: " + (diff / 1000) + " segundos");
                System.out.println("\nDeseja visualizar os movimentos feitos para encontrar a solução? s - Sim / n - NÃ£o\n->");
                String op;
                Scanner entry = new Scanner(System.in);
                op = entry.nextLine();
                
                if(op.equals("s")){
                    
                    int profundidadeAuxiliar = noParaExpandir.getProfundidade();
                    List<No> listaAuxiliar = new ArrayList();
                    No noAuxiliar = noParaExpandir;
                    
                    for(int i = 0 ; i < profundidadeAuxiliar; i++){
                        listaAuxiliar.add(noAuxiliar);
                        noAuxiliar = noAuxiliar.getPai();                        
                    }
                    
                    entrada.exibirTabuleiro();
                    
                    for(int i = (profundidadeAuxiliar-1); i >= 0 ; i--){
                        System.out.println("\n\nMovimento para "+listaAuxiliar.get(i).getAcao()+":");
                        listaAuxiliar.get(i).getEstado().exibirTabuleiro();
                    } 
                }
                return saida = problema;
            }

            borda.addAll(expandir(noParaExpandir, problema));
            primeiroNo++;
        } while (true);
    }

    
    public ArrayList<No> expandir(No d, EstruturaJogo entrada) {

        ArrayList<No> sucessores = new ArrayList();
        //SÃ£o quatro possibilidades de movimentos por isso quatro ifÂ´s
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