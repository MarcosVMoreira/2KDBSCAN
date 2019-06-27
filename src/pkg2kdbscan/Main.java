package pkg2kdbscan;

import java.util.LinkedList;

/**
 * @author Marcos
 */
public class Main {
    
    private static Edge[] fullEdgeArray;
    private static Edge[] agmArray;
    private static Node[] nodeArray;
    private static Agm agm;
    private static LinkedList<Double> removedEdges = new LinkedList<>();

    public static void main(String[] args) {
        
        Data data = new Data();
        Algorithms algorithms;
        
        double readData[][];
        
        readData = data.readFile("2.in");

        nodeArray = data.buildNodeArray(readData);
        
        algorithms = new Algorithms(nodeArray);
        
        int contador = 0;
        
        fullEdgeArray = new Edge[(nodeArray.length*nodeArray.length-nodeArray.length)/2];
        
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = 0; j < i; j++) {
                algorithms = new Algorithms(nodeArray);
                Edge auxEdge = new Edge(nodeArray[j], nodeArray[i], algorithms.euclideanDistanceCalculationForNodes(nodeArray[i], nodeArray[j]));
                fullEdgeArray[contador] = auxEdge;
                contador++;
            }
        }
        
        System.out.println("Node array antes: ");
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = 0; j < nodeArray[i].getConnectedNodes().size(); j++) {
                System.out.println(" "+nodeArray[i].getConnectedNodes().get(j));
            }
        }
        
        agm = new Agm(nodeArray, fullEdgeArray);
        
        agm.KruskalMST();
        
        agmArray = new Edge[agm.getAgmSize()];
        
        agmArray = agm.getAgm();

        System.out.println("AGM fora do metodo ");
        for (int j = 0; j < agmArray.length-1; j++) {
            System.out.println(" "+agmArray[j].getStart().getNode()+" "+ agmArray[j].getEnd().getNode()+" "+agmArray[j].getWeight());
        }
        
//        System.out.println("Node array depois: ");
//        for (int i = 0; i < nodeArray.length; i++) {
//            for (int j = 0; j < nodeArray[i].getConnectedNodes().size(); j++) {
//                System.out.println("esse no: "+nodeArray[i].getNode()+" ta ligado no nó "+nodeArray[i].getConnectedNodes().get(j));
//            }
//        }
        
        
        removeEdge((int)readData[0][2]);        
    }
    
    private static void removeEdge (int numberOfEdgesToRemove) {
        
        int firstToBeRemoved = agmArray.length-numberOfEdgesToRemove-1;
        int groupNumber = 0;
        
        //removo as ultimas arestas, dependendo do valor de K
        for (int j = firstToBeRemoved; j < agmArray.length-1; j++) {
            agmArray[j].setRemoved(true);
        }
        
        //seto as conexoes dos nós de acordo com a AGM
        for (int i = 0; i < agmArray.length-1; i++) {
            if (!agmArray[i].isRemoved()) {
                nodeArray[agmArray[i].getStart().getNode()].addToConnectedNodes(agmArray[i].getEnd().getNode());
            }
        }
        
        
        
        System.out.println("Node array depois: ");
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = 0; j < nodeArray[i].getConnectedNodes().size(); j++) {
                System.out.println("esse no: "+nodeArray[i].getNode()+" ta ligado no nó "+nodeArray[i].getConnectedNodes().get(j));
            }
        }
        
        bfs();
        
        System.out.println("ue");
        
        
//        int groupCounter = 0; 
//        int noiseNumber = 0;
//
//        groupNumber = groupCounter - noiseNumber;
//        
//        
//        System.out.println("removed edges: ");
//        for (int i = removedEdges.size()-1; i >= 0; i--) {
//            System.out.println(removedEdges.get(i));
//        }
//        
//        System.out.println("Numero de grupos = "+groupNumber);
//        System.out.println("Numero de ruidos = "+noiseNumber);

        
    }
    
    
    private static int bfs () {
        int counter = 1;
        for (int i = 0; i < agmArray.length-1; i++) {
            
            int node = agmArray[i].getStart().getNode();
            
            if (!nodeArray[node].isVisited()) {
                //pegando todas as conexões do vertice que to analisando atualmente
                for (int j = 0; j < nodeArray[node].getConnectedNodes().size(); j++) {
                    //se o vértice de destino nao foi visitado ainda
                    if (!nodeArray[nodeArray[node].getConnectedNodes().get(j)].isVisited()) {
                        System.out.println("saindo do nó "+node+" pro nó "+nodeArray[nodeArray[node].getConnectedNodes().get(j)].getNode());
                        nodeArray[nodeArray[node].getConnectedNodes().get(j)].setVisited(true);
                        counter++;
                    }
                }
            }
        }
        
        return counter;
    }
}
