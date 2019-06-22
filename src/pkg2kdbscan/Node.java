package pkg2kdbscan;

import java.util.LinkedList;

/**
 * @author Marcos
 */
public class Node {
    
    private LinkedList<Double> coords = new LinkedList<>();
    private LinkedList<Double> edgesWeight = new LinkedList<>();
    
    public Node (LinkedList<Double> coords) {
        this.coords = coords;
    }
    
    public void euclideanDistanceCalculation (Node node) {
        double sum = 0, euclideanDistance = 0;
        for (int i = 0; i < this.coords.size(); i++) {
            sum += Math.pow((this.coords.get(i) - node.getCoords().get(i)), 2);
        }
        euclideanDistance = Math.sqrt(sum);
        this.getEdgesWeight().add(euclideanDistance);
    }
    
    
    public LinkedList<Double> getCoords() {
        return this.coords;
    }

    public LinkedList<Double> getEdgesWeight() {
        return edgesWeight;
    }
    
}
