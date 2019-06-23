package pkg2kdbscan;

/**
 * @author Marcos
 */

public class Node {
    private int node;
    private double[] coords;
    
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
}
