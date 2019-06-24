package pkg2kdbscan;

import java.util.LinkedList;

/**
 * @author Marcos
 */
public class Main {

    public static void main(String[] args) {
        
        Edge[] fullEdgeArray;
        
        Data data = new Data();
        Algorithms algorithms;
        
        double readData[][];
        
        readData = data.readFile("7.in"); //arquivo deve estar na raiz do projeto

        //nodeArray tem apenas nós. A primeira linha, informando observações, atributos e arestas não entra
        Node[] nodeArray = data.buildNodeArray(readData);
        
        algorithms = new Algorithms(nodeArray);
        
        int contador = 0;
        
        fullEdgeArray = new Edge[(nodeArray.length*nodeArray.length-nodeArray.length)/2];
        
        System.out.println("size "+fullEdgeArray.length);
        
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = 0; j < i; j++) {
                algorithms = new Algorithms(nodeArray);
                Edge auxEdge = new Edge(nodeArray[j], nodeArray[i], algorithms.euclideanDistanceCalculationForNodes(nodeArray[i], nodeArray[j]));
                fullEdgeArray[contador] = auxEdge;
                contador++;
            }
        }
        
        Agm agm = new Agm(nodeArray, fullEdgeArray);
        

        agm.KruskalMST();
        
    }
}
