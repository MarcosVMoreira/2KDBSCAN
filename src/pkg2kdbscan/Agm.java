package pkg2kdbscan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author Marcos
 */
public class Agm {
    
    private Node[] nodeArray;
    private Edge[] notRepeatingEdges;
    
    public Agm(Node[] nodeArray, Edge[] edgeArray) {
        this.nodeArray = nodeArray;
        this.notRepeatingEdges = edgeArray;
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
        // find root and make root as parent of i (path compression) 
        if (subsets[i].parent != i) 
            subsets[i].parent = find(subsets, subsets[i].parent); 
  
        return subsets[i].parent; 
    } 
    
    void Union(subset subsets[], int x, int y) 
    { 
        int xroot = find(subsets, x); 
        int yroot = find(subsets, y); 
  
        // Attach smaller rank tree under root of high rank tree 
        // (Union by Rank) 
        if (subsets[xroot].rank < subsets[yroot].rank) 
            subsets[xroot].parent = yroot; 
        else if (subsets[xroot].rank > subsets[yroot].rank) 
            subsets[yroot].parent = xroot; 
  
        // If ranks are same, then make one as root and increment 
        // its rank by one 
        else
        { 
            subsets[yroot].parent = xroot; 
            subsets[xroot].rank++; 
        } 
    } 
    
    void KruskalMST() 
    { 
        Edge result[] = new Edge[this.nodeArray.length];  // Tnis will store the resultant MST 
        int e = 0;  // An index variable, used for result[] 
        int i = 0;  // An index variable, used for sorted edges 
        for (i=0; i<this.nodeArray.length; ++i) 
            result[i] = new Edge(); 
  
        // Step 1:  Sort all the edges in non-decreasing order of their 
        // weight.  If we are not allowed to change the given graph, we 
        // can create a copy of array of edges 
        Arrays.sort(notRepeatingEdges, new edgeComparator()); 

        // Allocate memory for creating V subsets 
        subset subsets[] = new subset[this.nodeArray.length]; 
        for(i=0; i<this.nodeArray.length; ++i) 
            subsets[i]=new subset(); 
  
        // Create V subsets with single elements 
        for (int v = 0; v < this.nodeArray.length; ++v) 
        { 
            subsets[v].parent = v; 
            subsets[v].rank = 0; 
        } 
  
        i = 0;  // Index used to pick next edge 
  
        // Number of edges to be taken is equal to V-1 
        while (e < this.nodeArray.length - 1) 
        { 
            // Step 2: Pick the smallest edge. And increment  
            // the index for next iteration 
            Edge next_edge = new Edge(); 
            next_edge = this.notRepeatingEdges[i++];
  
            int x = find(subsets, next_edge.getStart().getNode()); 
            int y = find(subsets, next_edge.getEnd().getNode()); 
  
            // If including this edge does't cause cycle, 
            // include it in result and increment the index  
            // of result for next edge 
            if (x != y) 
            { 
                result[e++] = next_edge; 
                Union(subsets, x, y); 
            } 
            // Else discard the next_edge 
        } 
  
        // print the contents of result[] to display 
        // the built MST 
        System.out.println("Following are the edges in " +  
                                     "the constructed MST"); 
        for (i = 0; i < e; ++i) 
            System.out.println(result[i].getStart().getNode()+" -- " +  
                   result[i].getEnd().getNode()+" == " + result[i].getWeight()); 
    } 
    
    
}
