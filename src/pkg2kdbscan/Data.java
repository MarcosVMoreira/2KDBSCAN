package pkg2kdbscan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Marcos
 */
public class Data {
    
    private FileReader fileReader;
    private double numberOfNodes, numberOfCoord, numberOfEdgesToRemove;
    private LinkedList<Double> coords;
    
    public LinkedList<LinkedList<Double>> readFile (String filePath) {
        
        LinkedList<LinkedList<Double>> data = new LinkedList<LinkedList<Double>>();
        
        this.coords = new LinkedList<>();
                
        try {
            fileReader = new FileReader(filePath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            
            String split[] = line.split(" ");

            this.numberOfNodes = Double.parseDouble(split[0]);
            this.numberOfCoord = Double.parseDouble(split[1]);
            this.numberOfEdgesToRemove = Double.parseDouble(split[2]);
            
            coords = new LinkedList<Double>();
            
            coords.add(this.numberOfNodes);
            coords.add(this.numberOfCoord);
            coords.add(this.numberOfEdgesToRemove);
            
            //data.add(coords);
            
            while ((line = bufferedReader.readLine()) != null) {
                coords = new LinkedList<Double>();
                
                split = line.split(" ");
              
                for (int i = 0; i < this.numberOfCoord; i++) {
                    coords.add(Double.parseDouble(split[i]));
                }
                
                data.add(coords);
            }

            return data;
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao ler o arquivo.");
        } catch (IOException ex) {
            System.out.println("Falha ao ler uma nova linha.");
        }
        return null;
    }
    
}
