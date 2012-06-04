package Bingo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MadsLundt
 */
public class tester {
    public static void main(String[] args) {
        int max = 90;
        int min = 1;
        Bingo bin = new Bingo();
        Bingo print = new Bingo();
        List<Integer> numbers = new ArrayList<Integer>();
        int[] plateNumbers = new int[15];
        for (int i = 0; i < 15; i++) {
            int rand = (int) ((int) (Math.random() * max) + 1);
            plateNumbers[i] = rand;
        }
        for (int i = 0; i < 20; i++) {
            int rand = (int) ((int) (Math.random() * max) + 1);
            numbers.add(rand);
        }

        System.out.println(bin.plateToString() + "\n");
        bin.setPlate(plateNumbers, 23132);
        print.setPlate(plateNumbers, 23132);
        System.out.println(bin.plateToString() + "\n");

        String str = "";
        bin.isReady(true);
        int check = bin.checkPlate(numbers);
        if (check == 1) {
            str = "1 row";
            System.out.println(str + " on plate " + bin.getControlNumber());
        } else if (check == 2) {
            str = "2 rows";
            System.out.println(str + " on plate " + bin.getControlNumber());
        } else if (check == 3) {
            str = "BINGO";
            System.out.println(str + " on plate " + bin.getControlNumber());
        } else if (check == -1) {
            str = "ERROR";
            System.out.println(str + " on plate " + bin.getControlNumber());
        }

        System.out.println();
        System.out.println("----------"+bin.checkPlate(numbers));
        System.out.println("Numbers to get one row: " + bin.numbersToRow1(numbers));
        System.out.println("Numbers to get two rows: " + bin.numbersToRow2(numbers));
        System.out.println("Numbers to get BINGO: " + bin.numbersToBingo(numbers));
        System.out.println();
        print.replaceNumber(numbers);
        System.out.println(print.plateToString());
        System.out.println(print.getNumbers());
        System.out.println();
        Collections.sort(numbers);
        System.out.println("Numbers: " + numbers.toString());
        
        
        numbers.add(55);
        numbers.add(73);
        numbers.add(74);
        Collections.sort(numbers);
        System.out.println(bin.plateToString() + "\n");
        bin.isReady(true);
        check = bin.checkPlate(numbers);
        System.out.println("---------- "+check);
        //print.replaceNumber(numbers);
        //System.out.println(print.getNumbers());
        System.out.println("Numbers to get one row: " + bin.numbersToRow1(numbers));
        System.out.println("Numbers to get two rows: " + bin.numbersToRow2(numbers));
        System.out.println("Numbers to get BINGO: " + bin.numbersToBingo(numbers));
        System.out.println("Numbers: " + numbers.toString());


        List<Bingo> array = new ArrayList<Bingo>();
        Bingo copy = new Bingo();
        for (int i = 0; i < 10; i++) {
            array.add(copy.generateRandom());
        }

        //printAllPlates(array);
        //printAllPlates(array, numbers);
                
        
    }

    private static void printAllPlates(List<Bingo> array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println("Plate: " + (i + 1));
            System.out.println(array.get(i).plateToString() + "\n");
        }
    }

    private static void printAllPlates(List<Bingo> plates, List<Integer> numbers) {
        for (int i = 0; i < plates.size(); i++) {
            System.out.println("Plate: " + (i + 1));
            System.out.println(plates.get(i).plateToString());
            System.out.println("Numbers to get one row: " + plates.get(i).numbersToRow1(numbers));
            System.out.println("Numbers to get two rows: " + plates.get(i).numbersToRow2(numbers));
            System.out.println("Numbers to get BINGO: " + plates.get(i).numbersToBingo(numbers) + "\n");
        }
    }

    
}
