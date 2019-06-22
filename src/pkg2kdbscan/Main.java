/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2kdbscan;

import java.util.LinkedList;

/**
 *
 * @author Marcos
 */
public class Main {

    public static void main(String[] args) {
        
        Data data = new Data();
        
        LinkedList<LinkedList<Integer>> readData = new LinkedList<LinkedList<Integer>>();
        
        readData = data.readFile("1.in"); //arquivo deve estar na raiz do projeto
        
        System.out.println("Numero de nos: "+readData.get(0).get(0));
        
        System.out.println("Numero de coordenadas: "+readData.get(0).get(1));
        
        System.out.println("Valor de K: "+readData.get(0).get(2));
        
        for (int i = 1; i <= readData.get(0).get(0); i++) {
            for (int j = 0; j < readData.get(0).get(1); j++) {
                System.out.print(readData.get(i).get(j));
            }
            System.out.print("\n");
        }
        
    }
    
}
