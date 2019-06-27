package pkg2kdbscan;

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
    
    public void euclideanDistanceCalculationForArray (Node node) {
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
    
    public double euclideanDistanceCalculationForNodes (Node node1, Node node2) {
        double sum = 0, euclideanDistance = 0;

        for (int j = 0; j < node1.getCoords().length; j++) {
            sum += Math.pow((node1.getCoords()[j] - node2.getCoords()[j]), 2);
        }

        euclideanDistance = Math.sqrt(sum);

        return euclideanDistance;
    }
    

    /**
     * @return the edges
     */
    public Edge[] getEdges() {
        return edges;
    }
}
