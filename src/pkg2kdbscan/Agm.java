package pkg2kdbscan;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Marcos
 */
public class Agm {
    
    
    public Agm () {
        
    }
    
    
    public LinkedList<Double> agmUsandoKruskall() {
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
        }

        return agm;
    }
    
}
