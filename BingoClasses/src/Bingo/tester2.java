/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bingo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author MadsLundt
 */
public class tester2 {
    public static void main(String[] args) {
     List<Bingo> arrayList1 = new ArrayList<Bingo>();

     Bingo bin = new Bingo();
     Bingo bin2 = new Bingo();
     bin = bin2.generateRandom();
     bin2 = bin.copyBingo();
     System.out.println(bin.plateToString());
     System.out.println("-------------");
     System.out.println(bin2.plateToString());
     arrayList1.add(bin);
     arrayList1.add(bin2);
     System.out.print("\n\n\n\n\n\n");
     printAllPlates(arrayList1);
    
  //Create a HashSet which allows no duplicates
  HashSet hashSet = new HashSet(arrayList1);

  //Assign the HashSet to a new ArrayList
  ArrayList arrayList2 = new ArrayList(hashSet);
    printAllPlates(arrayList1);
    }
    private static void printAllPlates(List<Bingo> array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println("Plate: " + (i + 1));
            System.out.println(array.get(i).plateToString() + "\n");
        }
    }
}
