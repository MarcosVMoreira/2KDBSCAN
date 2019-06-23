package pkg2kdbscan;

import java.util.LinkedList;

/**
 * @author Marcos
 */
public class Main {

    public static void main(String[] args) {
        
        Data data = new Data();
        Algorithms algorithms;
        
        double readData[][];
        
        readData = data.readFile("1.in"); //arquivo deve estar na raiz do projeto
        
        //nodeArray tem apenas nós. A primeira linha, informando observações, atributos e arestas não entra
        Node[] nodeArray = data.buildNodeArray(readData);
        
        algorithms = new Algorithms(nodeArray);
        
        algorithms.euclideanDistanceCalculation(nodeArray[0]);
        
        
        
        
        
        Edge[] edges = new Edge[algorithms.getEdges().length];
        edges = algorithms.getEdges();
        
        for (int i = 0; i < edges.length; i++) {
            System.out.print(" Weight: "+edges[i].getWeight());
            System.out.print(" Start: "+edges[i].getStart().getNode());
            System.out.print(" End: "+edges[i].getEnd().getNode());
            System.out.println("");
        }
        
        /*Node[] nodeArray = data.buildNodeArray(readData);
        
        double[] coords;
        
        for (int i = 0; i < nodeArray.length; i++) {
            coords = nodeArray[i].getCoords();
            int id = nodeArray[i].getNode();
            System.out.println("new coord "+id);
            for (int j = 0; j < coords.length; j++) {
                System.out.print(coords[j]+" ");
            }
            System.out.println("");
        }        
        
        System.out.println("Lido: ");
        for (int i = 0; i <= readData[0][0]; i++) {
            for (int j = 0; j < readData[0][1]; j++) {
                System.out.print(" "+readData[i][j]);
            }
            System.out.println("");
        }*/
        
    }
}
