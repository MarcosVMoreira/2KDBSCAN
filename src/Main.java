import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * @author Marcos
 */
class Main {
    
    private static Edge[] fullEdgeArray;
    private static Edge[] agmArray;
    private static Node[] nodeArray;
    private static Agm agm;
    private static LinkedList<Double> removedEdges = new LinkedList<>();
    private static int aux = 0;

    public static void main(String[] args) throws IOException {
        
        Data data = new Data();
        Algorithms algorithms;
        
        double readData[][];
        
        readData = data.readFile("6.in");
        //readData = data.readInput();
        
        
        nodeArray = data.buildNodeArray(readData);
        
        algorithms = new Algorithms(nodeArray);
        
        int contador = 0;
        
        fullEdgeArray = new Edge[(nodeArray.length*nodeArray.length-nodeArray.length)/2];
        
        
        //esse n² aqui ta lento. MELHORAR ELE
        for (int i = 0; i < nodeArray.length; i++) {
            for (int j = 0; j < i; j++) {
                algorithms = new Algorithms(nodeArray);
                Edge auxEdge = new Edge(nodeArray[j], nodeArray[i], algorithms.euclideanDistanceCalculationForNodes(nodeArray[i], nodeArray[j]));
                fullEdgeArray[contador] = auxEdge;
                contador++;
            }
        }
//        
//        
//        agm = new Agm(nodeArray, fullEdgeArray);
//        
//        agm.KruskalMST();
        
        //agmArray = new Edge[agm.getAgmSize()];
        
        //agmArray = agm.getAgm();


        
        //removeEdge((int)readData[0][2]);        
    }
    
    private static void removeEdge (int numberOfEdgesToRemove) {
        
        int firstToBeRemoved = agmArray.length-numberOfEdgesToRemove-1;
        int groupNumber = 0, noiseNumber = 0, groupCounter = 0;
        
        //removo as ultimas arestas, dependendo do valor de K
        for (int j = firstToBeRemoved; j < agmArray.length-1; j++) {
            agmArray[j].setRemoved(true);
            if (!removedEdges.contains(agmArray[j].getWeight())) {
                removedEdges.add(agmArray[j].getWeight());
            }
        }
        
        //seto as conexoes dos nós de acordo com a AGM
        for (int i = 0; i < agmArray.length-1; i++) {
            if (!agmArray[i].isRemoved()) {
                nodeArray[agmArray[i].getStart().getNode()].addToConnectedNodes(agmArray[i].getEnd().getNode());
                nodeArray[agmArray[i].getEnd().getNode()].addToConnectedNodes(agmArray[i].getStart().getNode());
            }
        }

        
        
        for (int i = 0; i < nodeArray.length; i++) {
            if (!nodeArray[i].isVisited()) {
                int aux = dfs(nodeArray[i].getNode());
                if (aux == 1) {
                    noiseNumber++;
                }
                groupCounter++;
            }
        }
        
        groupNumber = groupCounter - noiseNumber;
       
        for (int i = removedEdges.size()-1; i >= 0; i--) {
            System.out.printf("%.2f\n", removedEdges.get(i));
            
        }
        
        System.out.println("Numero de grupos = "+groupNumber);
        System.out.println("Numero de ruidos = "+noiseNumber);

        
    }
    
    
    private static int dfs (int node) {
        
        Node root = nodeArray[node];
        
//        System.out.println("node "+root.getNode());
        
        if (!root.isVisited()) {
            int counter = 1;
            nodeArray[root.getNode()].setVisited(true);
            for (int i = 0; i < root.getConnectedNodes().size(); i++) {
//                System.out.println("conection "+root.getConnectedNodes().get(i));
                dfs(root.getConnectedNodes().get(i));
                counter++;
            }
            return counter;
        }
        
        return 0;
    }
}


class Data {
    
    private FileReader fileReader;
    private double numberOfNodes, numberOfCoord, numberOfEdgesToRemove;
    private double coords[];
    
    public double[][] readFile (String filePath) {
        
        double data[][];
        int counter = 1;
        
        try {
            fileReader = new FileReader(filePath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            
            String split[] = line.split(" ");

            this.numberOfNodes = Double.parseDouble(split[0]);
            this.numberOfCoord = Double.parseDouble(split[1]);
            this.numberOfEdgesToRemove = Double.parseDouble(split[2]);
            
            data = new double[(int) numberOfNodes+1][(int) numberOfCoord];
            
            coords = new double[3];
            
            coords[0] = this.numberOfNodes;
            coords[1] = this.numberOfCoord;
            coords[2] = this.numberOfEdgesToRemove;
            
            data[0] = coords;
            
            while ((line = bufferedReader.readLine()) != null) {
                
                coords = new double[(int) numberOfCoord];
                
                split = line.split(" ");
              
                for (int i = 0; i < this.numberOfCoord; i++) {
                    coords[i] = Double.parseDouble(split[i]);
                }
                
                data[counter] = coords;
                counter++;
            }

            return data;
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao ler o arquivo.");
        } catch (IOException ex) {
            System.out.println("Falha ao ler uma nova linha.");
        }
        return null;
    }
    
    public double [][] readInput () throws IOException {
        double data[][];
        int counter = 1;
        
        BufferedReader reader =  
                   new BufferedReader(new InputStreamReader(System.in)); 
        
        String firstDataLine = reader.readLine(); 


        String split[] = firstDataLine.split(" ");

        this.numberOfNodes = Double.parseDouble(split[0]);
        this.numberOfCoord = Double.parseDouble(split[1]);
        this.numberOfEdgesToRemove = Double.parseDouble(split[2]);

        data = new double[(int) numberOfNodes+1][(int) numberOfCoord];

        coords = new double[3];

        coords[0] = this.numberOfNodes;
        coords[1] = this.numberOfCoord;
        coords[2] = this.numberOfEdgesToRemove;

        data[0] = coords;
        
        
        for (int i = 0; i < numberOfNodes; i++) {
            
            String line = reader.readLine(); 
            
            coords = new double[(int) numberOfCoord];
                
            split = line.split(" ");

            for (int j = 0; j < this.numberOfCoord; j++) {
                coords[j] = Double.parseDouble(split[j]);
            }

            data[counter] = coords;
            counter++;
        }
        
        return data;
    }
    
    
    public Node[] buildNodeArray (double[][] data) {
        
        Node nodeArray[] = new Node[(int) data[0][0]];
        Node aux;
        double coords[] = new double[(int) data[0][1]];
        
        for (int i = 1; i <= data[0][0]; i++) {
            coords = new double[(int) data[0][1]];
            aux = new Node(i-1);
            for (int j = 0; j < data[0][1]; j++) {
                coords[j] = data[i][j];
            }
            aux.setCoords(coords);
            nodeArray[i-1] = aux;
        }
        
        return nodeArray;
    }
    
}


/**
 *
 * @author Marcos
 */
class Edge {
    
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


/**
 * @author Marcos
 */

class Node {
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


/**
 * @author Marcos
 */
class Algorithms {
    
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


/**
 *
 * @author Marcos
 */
class Agm {
    
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
                
                //nodeArray[getAgm()[e-1].getStart().getNode()].addToConnectedNodes(getAgm()[e-1].getEnd().getNode());
                
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
