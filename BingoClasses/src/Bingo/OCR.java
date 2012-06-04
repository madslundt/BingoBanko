/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bingo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author madslundt
 */
public class OCR {

    public static List<Bingo> plateListOCR = new ArrayList<Bingo>();
    private static BingoGUI binGUI = new BingoGUI();

    public static List<Bingo> START(String path) {
        String nextInt;
        int number, chars = 0;
        List<Integer> listInt = new ArrayList<Integer>();
        List<String> list = new ArrayList<String>();
        Bingo bin = new Bingo();

        Scanner scan = null;
        File file = new File(path);
        try {
            scan = new Scanner(new File(path));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            // GUI  - ERROR BOX
        }
        System.out.println("Start reading:\n");
        while (scan.hasNext()) {
            nextInt = scan.next();
            // if it contains any number
            if (nextInt.matches(".*[0-9].*")) {
                nextInt = nextInt.toUpperCase();
                list.add(nextInt);
            }

        }
        System.out.println("Ended reading:\n");
        System.out.println("Original Numbers:\n" + list.toString());
        list = sortImportantNumbers(list);
        System.out.println("Numbers:\n" + list.toString());

        for (int i = 0; i < list.size(); i++) {
            try {
                number = Integer.parseInt(list.get(i));
                listInt.add(number);
                chars++;
            } catch (NumberFormatException e) {
                System.out.println("Couldn't parse to Integer:\t" + list.get(i));
                // GUI ERROR - MANUALLY for this number
                continue;
            }
        }

        int plates = copyToBingo(listInt, bin);

        binGUI.setOCR("Found " + plates + " plates out of " + chars + " numbers (" + (chars / 16) + " plates)");
        return plateListOCR;
    }

    private static List<String> sortImportantNumbers(List<String> list) {
        List<String> out = new ArrayList<String>();
        String temp;
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 4 && list.get(i).equalsIgnoreCase("70") && list.get(i + 1).equalsIgnoreCase("11") && list.get(i + 2).equalsIgnoreCase("49") && list.get(i + 3).equalsIgnoreCase("09")) {
                i += 4;
            } else if (list.get(i).contains("NOV") || list.get(i).contains("DEC")
                    || list.get(i).contains("JAN") || list.get(i).contains("FEB")
                    || list.get(i).contains("MAR") || list.get(i).contains("APR")
                    || list.get(i).contains("MAJ") || list.get(i).contains("JUN")
                    || list.get(i).contains("JUL") || list.get(i).contains("AUG")
                    || list.get(i).contains("SEP") || list.get(i).contains("OKT")
                    || list.get(i).equalsIgnoreCase("2011") || list.get(i).equalsIgnoreCase("2012")) {
                continue;
            } else if (list.get(i).contains("GYLDIG")) {
                out.add(list.get(i).substring(0, 5));
                continue;
            }
            temp = list.get(i).replaceAll("\"", "");
            temp = temp.replaceAll("I", "1");
            temp = temp.replaceAll("L", "1");
            temp = temp.replaceAll("S", "5");
            temp = temp.replaceAll("'", "");
            out.add(temp);
        }
        return out;
    }

    private static int copyToBingo(List<Integer> list, Bingo bin) {
        int[] tempPlate = new int[15];
        int count = 0;
        int start = 0;
        int number = 0;
        String tmp;


        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > 90 && count < 15) {
                count = 0;
                System.out.println("Wrong plate: " + number + " (" + list.get(i) + ")");
                continue;
            }
            if (count != 0 && count != 5 && count != 10) {
                if (list.get(i) <= list.get(i-1)) {
                    // ERROR
                    System.out.println("Wrong plate (bigger): " + number + " (" + list.get(i) + ")");
                    while(list.get(i) <= 90) {
                        i++;
                    }
                    count = 0;
                    continue;
                }
            }
            if ((count + 1) % 16 == 0) {
                tmp = "00000" + list.get(i);
                bin = new Bingo();
                bin.setPlate(tempPlate, tmp.substring(tmp.length() - 5, tmp.length()));
                plateListOCR.add(bin);

                // Clearing array - may be optimized
                tempPlate = new int[15];
                count = 0;
                number++;
                continue;
            }
            tempPlate[count] = list.get(i);
            count++;
        }
        System.out.println("Number of plates: " + number);
        return number;
    }
}