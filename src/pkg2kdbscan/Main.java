package pkg2kdbscan;

import java.util.LinkedList;

/**
 * @author Marcos
 */
public class Main {

    public static void main(String[] args) {
        
        Data data = new Data();
        
        LinkedList<LinkedList<Double>> readData = new LinkedList<LinkedList<Double>>();
        
        readData = data.readFile("1.in"); //arquivo deve estar na raiz do projeto
        
        
    }
}
