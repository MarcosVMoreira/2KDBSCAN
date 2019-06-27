package pkg2kdbscan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Marcos
 */
public class Data {
    
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
    
    public double [][] readInput (String filePath) throws IOException {
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
