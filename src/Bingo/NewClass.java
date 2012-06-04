/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bingo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author MadsLundt
 */
public class NewClass {

    public static void main(String args[]) {
        List<Bingo> bingoPlates = new ArrayList<Bingo>();
        List<Integer> numbers = new ArrayList<Integer>();
        Bingo bin = new Bingo();
        int[] plate = new int[15];

        for (int i = 0; i < 15; i++) {
            plate[i] = i + 1;
        }
        bin.setPlate(plate, 23232);
        bingoPlates.add(bin);
        
        bingoPlates.add(bin);
        System.out.println(bingoPlates.size());
        System.out.println("\n\n");
        numbers.add(1);
        for (int i = 0; i < bingoPlates.size(); i++) {
            bingoPlates.get(i).checkPlate(numbers);
            System.out.println("Numbers to get one row: " + bingoPlates.get(i).numbersToRow1(numbers));
            System.out.println("Numbers to get two rows: " + bingoPlates.get(i).numbersToRow2(numbers));
            System.out.println("Numbers to get BINGO: " + bingoPlates.get(i).numbersToBingo(numbers));
            System.out.println();
            
        }
        System.out.println(bingoPlates.get(0).getNumbers(numbers));
        System.out.println("\n\n");
        
        
        numbers.add(2);
        for (int i = 0; i < bingoPlates.size(); i++) {
            bingoPlates.get(i).checkPlate(numbers);
            System.out.println("Numbers to get one row: " + bingoPlates.get(i).numbersToRow1(numbers));
            System.out.println("Numbers to get two rows: " + bingoPlates.get(i).numbersToRow2(numbers));
            System.out.println("Numbers to get BINGO: " + bingoPlates.get(i).numbersToBingo(numbers));
            System.out.println();
        }
        System.out.println("Numbers: " + numbers.toString());
    }

    public static List<Bingo> copy(List<Bingo> list) {
        List<Bingo> newList = new ArrayList<Bingo>();
        for (int i = 0; i < list.size(); i++) {
            Bingo inte = list.get(i);
            newList.add(inte);
        }

        return newList;
    }
}
