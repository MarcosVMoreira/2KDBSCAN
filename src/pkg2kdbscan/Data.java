package pkg2kdbscan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcos
 */
public class Data {
    
    private FileReader fileReader;
    private int numberOfNodes, numberOfCoord, numberOfEdgesToRemove;
    private LinkedList<Integer> coords;
    
    public LinkedList<LinkedList<Integer>> readFile (String filePath) {
        
        LinkedList<LinkedList<Integer>> data = new LinkedList<LinkedList<Integer>>();
        
        this.coords = new LinkedList<>();
                
        try {
            fileReader = new FileReader(filePath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            
            String split[] = line.split(" ");

            this.numberOfNodes = Integer.parseInt(split[0]);
            this.numberOfCoord = Integer.parseInt(split[1]);
            this.numberOfEdgesToRemove = Integer.parseInt(split[2]);
            
            coords = new LinkedList<Integer>();
            
            coords.add(this.numberOfNodes);
            coords.add(this.numberOfCoord);
            coords.add(this.numberOfEdgesToRemove);
            
            data.add(coords);
            
            while ((line = bufferedReader.readLine()) != null) {
                coords = new LinkedList<Integer>();
                
                split = line.split(" ");
              
                for (int i = 0; i < this.numberOfCoord; i++) {
                    coords.add(Integer.parseInt(split[i]));
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
