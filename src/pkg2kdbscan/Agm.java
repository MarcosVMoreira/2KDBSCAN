package pkg2kdbscan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Marcos
 */
public class Agm {
    
    private Node[] nodeArray;
    private Edge[] edgeArray;
    private ArrayList<Node> nodes[];
    private LinkedList<Edge> notRepeatingEdges;
    
    public Agm(Node[] nodeArray, Edge[] edgeArray) {
        this.nodeArray = nodeArray;
        this.edgeArray = edgeArray;
        this.notRepeatingEdges = new LinkedList<>();
    }
    
    public Edge[] agmUsandoKruskall() {
        
        Edge[] agm = null;
        
        this.nodes = new ArrayList[this.nodeArray.length];

        for (int i = 0; i < this.nodes.length; i++) {
            this.nodes[i] = new ArrayList<Node>();
            this.nodes[i].add(this.nodeArray[i]);
        }

        
        /*criando uma nova lista de arestas sem arestas repetidas. Ex.: aresta 1-0 e 0-1 são repetidas. Portanto, são removidas
        Lógica: começo pegando o primeiro valor de aresta e salvando na lista de arestas nao repetidas.
        Pego o X valor de aresta e vejo se ele é igual a algum vamos da lista de arestas nao repetidas
        Depois de percorrer todaa a lista de arestas nao repetidas verificando se ele é repetido, se não for, eu adiciono ele a essa lista
        Se ele for, eu ja quebro o for e pulo pro próximo valor de aresta
        */
        this.notRepeatingEdges.add(this.edgeArray[0]);
        
        for (int i = 1; i < this.edgeArray.length; i++) {
            for (int j = 0; j < this.notRepeatingEdges.size(); j++) {
                
                if (!((this.edgeArray[i].getStart().getNode() == this.notRepeatingEdges.get(j).getEnd().getNode()) &&
                    (this.edgeArray[i].getEnd().getNode() == this.notRepeatingEdges.get(j).getStart().getNode()))) {
                    //encontramos diferentes
                    
                    /*se for a ultima aresta da lista sem arestas repetidas e nao saiu do desse for ainda,
                    quer dizer que ela é uma aresta nao repetida, entao adiciono*/
                    if (j+1 == this.notRepeatingEdges.size()) {
                        this.notRepeatingEdges.add(this.edgeArray[i]);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        
        
        
        
        //continuar implementando a agm
        
        
        /*
        
        LinkedList<LinkedList<Vertice>> conjunto = new LinkedList<LinkedList<Vertice>>();
        LinkedList<Aresta> arestasOrdenadas = new LinkedList<>();
        LinkedList<Integer> aux = new LinkedList<>();
        LinkedList<Aresta> agm = new LinkedList<>();

        for (int i = 0; i < g.numeroDeVertices(); i++) {
            conjunto.add(new LinkedList<Vertice>());
            conjunto.get(i).add(listaVertice.get(i));
            aux.add(listaVertice.get(i).id());
        }

        arestasOrdenadas.addAll(listaAresta);

        Comparador comparador = new Comparador();

        Collections.sort(arestasOrdenadas, comparador);

        for (int i = 0; i < arestasOrdenadas.size(); i++) {

            if (aux.get(arestasOrdenadas.get(i).origem().id()) != aux.get(arestasOrdenadas.get(i).destino().id())) {

                int u = aux.get(arestasOrdenadas.get(i).origem().id());

                int v = aux.get(arestasOrdenadas.get(i).destino().id());

                for (int j = 0; j < conjunto.get(v).size(); j++) {

                    conjunto.get(u).add(conjunto.get(v).get(j));

                    aux.set(conjunto.get(v).get(j).id(), u);

                }
                conjunto.get(v).clear();

                agm.add(arestasOrdenadas.get(i));

            }
        }*/

        return agm;
    }



}
