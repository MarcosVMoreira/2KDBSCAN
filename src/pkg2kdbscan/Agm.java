package pkg2kdbscan;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Marcos
 */
public class Agm {
    
    private Node[] nodeArray;
    private Edge[] notRepeatingEdges;
    private int agmSize;
    private Edge[] agm;
    
    public Agm(Node[] nodeArray, Edge[] edgeArray) {
        this.nodeArray = nodeArray;
        this.notRepeatingEdges = edgeArray;
    }
    
    void KruskalMST() { 
        this.setAgm(new Edge[this.nodeArray.length]);  
        int e = 0;  
        int i = 0;  
        for (i=0; i<this.nodeArray.length; ++i) 
            getAgm()[i] = new Edge(); 
  
        Arrays.sort(notRepeatingEdges, new edgeComparator()); 

        subset subsets[] = new subset[this.nodeArray.length]; 
        for(i=0; i<this.nodeArray.length; ++i) 
            subsets[i]=new subset(); 
  
        for (int v = 0; v < this.nodeArray.length; ++v) 
        { 
            subsets[v].parent = v; 
            subsets[v].rank = 0; 
        } 
  
        i = 0;  
  
        while (e < this.nodeArray.length - 1) 
        { 
            Edge next_edge = new Edge(); 
            next_edge = this.notRepeatingEdges[i++];
  
            int x = find(subsets, next_edge.getStart().getNode()); 
            int y = find(subsets, next_edge.getEnd().getNode()); 
            
           

  
            if (x != y) 
            { 
                getAgm()[e++] = next_edge; 
                
                nodeArray[getAgm()[e-1].getStart().getNode()].addToConnectedNodes(getAgm()[e-1].getEnd().getNode());
                
                Union(subsets, x, y); 
                
            } 

        } 
        
        
//        System.out.println("AGM de dentro do metodo ");
//        for (int j = 0; j < agm.length-1; j++) {
//            System.out.println(" "+agm[j].getStart().getNode()+" "+ agm[j].getEnd().getNode());
//        }
        
        this.agmSize = this.agm.length;
    } 
    
    class edgeComparator implements Comparator<Edge> 
    { 
        @Override
        public int compare(Edge firstEdge, Edge secondEdge) {
            return Double.compare(firstEdge.getWeight(), secondEdge.getWeight());
        }
    } 
    
    class subset 
    { 
        int parent, rank; 
    }; 
    
    int find(subset subsets[], int i) 
    { 
        if (subsets[i].parent != i) 
            subsets[i].parent = find(subsets, subsets[i].parent); 
  
        return subsets[i].parent; 
    } 
    
    void Union(subset subsets[], int x, int y) 
    { 
        int xroot = find(subsets, x); 
        int yroot = find(subsets, y); 
  
        if (subsets[xroot].rank < subsets[yroot].rank) 
            subsets[xroot].parent = yroot; 
        else if (subsets[xroot].rank > subsets[yroot].rank) 
            subsets[yroot].parent = xroot; 
        else
        { 
            subsets[yroot].parent = xroot; 
            subsets[xroot].rank++; 
        } 
        
    } 


    /**
     * @return the agmSize
     */
    public int getAgmSize() {
        return agmSize;
    }

    /**
     * @return the agm
     */
    public Edge[] getAgm() {
        return agm;
    }

    /**
     * @param agm the agm to set
     */
    public void setAgm(Edge[] agm) {
        this.agm = agm;
    }
    
    
}
