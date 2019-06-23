package pkg2kdbscan;

import java.util.LinkedList;

/**
 * @author Marcos
 */
public class Main {

    public static void main(String[] args) {
        
        LinkedList<Edge> agmList = new LinkedList<>();
        LinkedList<Edge> fullEdgeArray = new LinkedList<>();
        
        Data data = new Data();
        Algorithms algorithms;
        
        double readData[][];
        
        readData = data.readFile("3.in"); //arquivo deve estar na raiz do projeto
        
        /*System.out.println("Lido: ");
        for (int i = 0; i <= readData[0][0]; i++) {
            for (int j = 0; j < readData[0][1]; j++) {
                System.out.print(" "+readData[i][j]);
            }
            System.out.println("");
        }*/
        
        //nodeArray tem apenas nós. A primeira linha, informando observações, atributos e arestas não entra
        Node[] nodeArray = data.buildNodeArray(readData);
        
        /*for (int i = 0; i < nodeArray.length; i++) {
            algorithms.euclideanDistanceCalculation(nodeArray[i]);
        
            edgeArray = new Edge[algorithms.getEdges().length];
            edgeArray = algorithms.getEdges();

            for (int j = 0; j < edgeArray.length; j++) {
                fullEdgeArray[((i)*(int)readData[0][0]+j)] = edgeArray[j];
            }
        }*/
        
        /*System.out.println("full aray size: "+fullEdgeArray.length);
        for (int i = 0; i < fullEdgeArray.length; i++) {
            System.out.println(fullEdgeArray[i].getStart().getNode()+" "+fullEdgeArray[i].getEnd().getNode());
        }*/
        
        
        algorithms = new Algorithms(nodeArray);
        
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = 0; j < i; j++) {
                algorithms = new Algorithms(nodeArray);
                Edge auxEdge = new Edge(nodeArray[i], nodeArray[j], algorithms.euclideanDistanceCalculationForNodes(nodeArray[i], nodeArray[j]));
                fullEdgeArray.add(auxEdge);
            }
        }
        
        
        Agm agm = new Agm(nodeArray, fullEdgeArray);
        
        agmList = agm.agmUsandoKruskall();
        
        System.out.println("Agm gerada");
        for (int i = 0; i < agmList.size(); i++) {
            System.out.println(agmList.get(i).getStart().getNode()+" "+agmList.get(i).getEnd().getNode()+" "+agmList.get(i).getWeight());
        }
            
        /*for (int i = 0; i < edges.length; i++) {
            System.out.print(" Weight: "+edges[i].getWeight());
            System.out.print(" Start: "+edges[i].getStart().getNode());
            System.out.print(" End: "+edges[i].getEnd().getNode());
            System.out.println("");
        }
        
        Node[] nodeArray = data.buildNodeArray(readData);
        
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
