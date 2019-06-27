package pkg2kdbscan;

/**
 *
 * @author Marcos
 */
public class Edge {
    
    private Node start;
    private Node end;
    private double weight;
    private boolean removed = false;
    
    public Edge( Node start, Node end ){
        this.start = start;
        this.end = end;
        this.weight = 1;
    }
    
    public Edge( Node start, Node end, double weight ){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    
    public Edge() {
        
    }


    public void setarStart(Node start) {
        this.start = start;
    }


    public void setarEnd(Node end) {
        this.end = end;
    }


    public void setarWieght(double weight) {
        this.weight = weight;
    }

    /**
     * @return the start
     */
    public Node getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public Node getEnd() {
        return end;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @return the removed
     */
    public boolean isRemoved() {
        return removed;
    }

    /**
     * @param removed the removed to set
     */
    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

}
