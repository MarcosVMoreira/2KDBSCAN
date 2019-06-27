package pkg2kdbscan;

import java.util.LinkedList;

/**
 * @author Marcos
 */

public class Node {
    private int node;
    private double[] coords;
    private boolean visited = false;
    private LinkedList<Integer> connectedNodes = new LinkedList<>();
    
    public Node(int v){
        this.node = v;
    }
    
    public Node(){
    }

    public int id() {
        return getNode();
    }

    public void setarNode(int node) {
        this.setNode(node);
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }
    
    public double[] getCoords() {
        return coords;
    }

    public void setCoords(double[] coords) {
        this.coords = coords;
    }

    /**
     * @return the connectedNodes
     */
    public LinkedList<Integer> getConnectedNodes() {
        return connectedNodes;
    }

    /**
     * @return the visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * @param visited the visited to set
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    
    public void addToConnectedNodes (int edge) {
        this.connectedNodes.add(edge);
    }
    
}
