/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bingo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author madslundt
 */
public class OCR2 {
    
    private static List<Bingo> plateList = new ArrayList<Bingo>();
    private static String debug = "src/Bingo/ORCdebug";
    private static FileWriter FW = null;

    public static void main(String[] args) throws IOException {        
        File debugf = new File(debug);
        if (!debugf.exists()) {
            try {
                debugf.createNewFile();
            } catch (IOException ex) {
                debugPrint("Couldn't create file");
            }
        }
        if (debugf.canWrite()) {
            System.out.println("Debug file ready to write");
            try {
                FW = new FileWriter(debug, true);
            } catch (IOException ex) {
                debugPrint("Couldn't write to created file");
            }
        } else {
            debugPrint("Debug file error (write)");
        }
        Date d = new Date();
        String date = d.getDate() + "/" + (d.getMonth()+1) + " - " + d.getYear() + " : " + d.getHours() + "." + d.getMinutes();
        FW.write("File created and writing succesfully (" + date + ")\n---------------------------\n\n");
        String path = "src/Bingo/plates", nextInt;
        int number, chars = 0;
        List<Integer> listInt = new ArrayList<Integer>();
        List<String> list = new ArrayList<String>();
        Bingo bin = new Bingo();

        Scanner scan = null;
        File file = new File(path);
        try {
            scan = new Scanner(new File(path));
        } catch (FileNotFoundException ex) {
            debugPrint("File not found");
            FW.write("File not found");
            // GUI  - ERROR BOX
        }
        debugPrint("Start reading:\n");
        while (scan.hasNext()) {
            nextInt = scan.next();
            // if it contains any number
            if (nextInt.matches(".*[0-9].*")) {
                list.add(nextInt);
            }

        }
        debugPrint("Ended reading:\n");
        debugPrint("Original Numbers:\n" + list.toString());
        list = sortImportantNumbers(list);
        debugPrint("Numbers:\n" + list.toString());

        for (int i = 0; i < list.size(); i++) {
            try {
                number = Integer.parseInt(list.get(i));
                listInt.add(number);
                chars++;
            } catch (NumberFormatException e) {
                debugPrint("Couldn't parse to Integer:\t" + list.get(i));
                // GUI ERROR - MANUALLY for this number
                continue;
            }
        }
        
        copyToBingo(listInt, bin);
        debugPrint("Plates: ");
        for (int i = 0; i < plateList.size(); i++) {
            debugPrint(plateList.get(i).plateToString()+"\n");
        }
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
                    || list.get(i).equalsIgnoreCase("2011") || list.get(i).equalsIgnoreCase("2012") || list.get(i).equalsIgnoreCase("2013")) {
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
                debugPrint("Wrong plate: " + number + " (" + list.get(i) + ")");
                continue;
            }
            if (count != 0 && count != 5 && count != 10) {
                if (list.get(i) <= list.get(i-1)) {
                    // ERROR
                    debugPrint("Wrong plate (bigger): " + number + " (" + list.get(i) + ")");
                    while(list.get(i) <= 90) {
                        i++;
                    }
                    count = 0;
                    continue;
                }
            }
            if ((count + 1) % 16 == 0) {
                tmp = "00000" + list.get(i);
                bin.setPlate(tempPlate, tmp.substring(tmp.length() - 5, tmp.length()));
                plateList.add(bin);

                // Clearing array - may be optimized
                bin = new Bingo();
                tempPlate = new int[15];
                count = 0;
                number++;
                continue;
            }
            tempPlate[count] = list.get(i);
            count++;
        }
        debugPrint("Number of plates: " + number);
        return number;
    }
    
    private static void debugPrint(String s) {
        try {
            FW.write(s + "\n");
        } catch (IOException ex) {
            System.out.println("Couldn't write string: " + s);
        }
        System.out.println(s);
        
    }
}