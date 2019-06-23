package pkg2kdbscan;

import java.util.LinkedList;

/**
 * @author Marcos
 */
public class Algorithms {
    
    private Node[] nodes;
    private Edge[] edges;
    
    public Algorithms (Node[] nodes) {
        this.nodes = nodes;
        edges = new Edge[nodes.length];
    }
    
    public void euclideanDistanceCalculation (Node node) {
        double sum = 0, euclideanDistance = 0;
        Edge auxEdge;
        
        for (int i = 0; i < this.nodes.length; i++) {
            for (int j = 0; j < this.nodes[i].getCoords().length; j++) {
                sum += Math.pow((this.nodes[i].getCoords()[j] - node.getCoords()[j]), 2);
            }
            
            euclideanDistance = Math.sqrt(sum);
            auxEdge = new Edge(node, this.nodes[i], euclideanDistance);
            
            this.edges[i] = auxEdge;
            
            sum = 0;
        }
    }

    /**
     * @return the edges
     */
    public Edge[] getEdges() {
        return edges;
    }
}
