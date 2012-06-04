/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bingo;

import java.util.*;

/**
 *
 * @author MadsLundt
 */
public class test {
    public static void main(String[] args) {
        Bingo bin = new Bingo();
        List<Bingo> plates = new ArrayList<Bingo>();
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i=0; i<5000; i++) {
            plates.add(bin.generateRandom());
        }
        //printAllPlates(plates);
        System.out.println("Saving..");
        UserData.saveData("src/Bingo/first", plates);
        System.out.println("\n\n\n\n\n\n");
        System.out.println("Loading..");
        List<Bingo> plates2 = new ArrayList<Bingo>();
        plates2 = UserData.loadData("src/Bingo/first");
        printAllPlates(plates2);
        System.exit(0);
        
    }
    
    private static void printAllPlates(List<Bingo> array) {
        int count = 1;
        for (int i = 0; i < array.size(); i++) {
            System.out.println("Plate: " + count);
            System.out.println(array.get(i).plateToString() + "\n");
            count++;
        }
    }
}
