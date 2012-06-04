package Bingo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MadsLundt
 */
public class UserData {

    private static BingoGUI binGUI = new BingoGUI();
    private static int count = 0;

    public static void saveData(String path, List<Bingo> plates) {
        System.out.println(path);
        int platesNb = getNumberPlates(path);
        File file = new File(path);
        file.setWritable(true, true);
        try {
            FileWriter FW = new FileWriter(path, true);
            binGUI.setLoad(true);
            //FW.write("Number of plates: " + (platesNb + plates.size()) + "\n");
            setNumberPlates(platesNb + plates.size(), path);
            for (int i = 0; i < plates.size(); i++) {
                FW.write(plates.get(i).getNumbers() + "\n");
                FW.write(plates.get(i).getControlNumber() + "\n\n");
                binGUI.progressInitialize(i, plates.size());
                if (binGUI.cancelProgress()) {
                        binGUI.setLoad(false);
                        return;
                    }
            }
            FW.close();
        } catch (IOException e) {
            //msg("Writing to file", "Couldn't write to file");
        }
        file.setWritable(false, false);
        binGUI.setLoad(false);
    }

    public static List<Bingo> loadData(String path) {
        Scanner scan = null;
        File file = new File(path);
        int nummer;
        int c = 0;
        int plates = 0;
        int nbPlates = 0;
        
        int[] plateNumbers = new int[15];
        List<Bingo> bingo = new ArrayList<Bingo>();
        if (file.exists() && file.canRead()) {
            try {
                scan = new Scanner(new File(path));
                binGUI.setLoad(true);
            } catch (FileNotFoundException e) {
            }
            while (scan.hasNextLine()) {
                String nextnb = scan.nextLine();
                if (nextnb.contains("Number of plates: ")) {
                    try {
                        nbPlates = Integer.parseInt(nextnb.substring(18, nextnb.length()));
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR!!! " + e);
                    }
                    System.out.println(nbPlates);
                }
                Scanner nextnbsc = new Scanner(nextnb);
                while (nextnbsc.hasNextInt()) {
                    nummer = nextnbsc.nextInt();
                    Bingo bin = new Bingo();
                    if (binGUI.cancelProgress()) {
                        binGUI.setLoad(false);
                        return bingo;
                    }
                    if (nummer > 90) {
                        plates++;
                        bin.setPlate(plateNumbers, nummer);
                        bingo.add(bin);
                        plateNumbers = new int[15];
                        c = 0;
                        binGUI.progressInitialize(plates, nbPlates);
                    } else {
                        plateNumbers[c] = nummer;
                        c++;
                    }
                }

            }
        }
        System.out.println(plates);
        count = nbPlates;
        if (plates != getNumberPlates(path))
            setNumberPlates(plates, path);
        binGUI.setLoad(false);
        return bingo;
    }

    public static int getCount() {
        return count;
    }

    private static int getNumberPlates(String path) {
        Scanner scan = null;
        File file = new File(path);
        int nbPlates = 0;
        if (file.exists() && file.canRead()) {
            try {
                scan = new Scanner(new File(path));
            } catch (FileNotFoundException e) {
            }
            while (scan.hasNextLine()) {
                String nextnb = scan.nextLine();
                if (nextnb.contains("Number of plates: ")) {
                    try {
                        nbPlates = Integer.parseInt(nextnb.substring(18, nextnb.length()));
                        return nbPlates;
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR!!! " + e);
                    }
                }
            }
        }
        file.setWritable(false, false);
        return nbPlates;
    }

    private static void setNumberPlates(int number, String path) {
        try {
            File file = new File(path + "TEMP");
            copyFile(new File(path), file, number);
            copyFile(file, new File(path), number);
            file.delete();
        } catch (IOException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void copyFile(File fromFile, File toFile, int number) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fromFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(toFile));

        //... Loop as long as there are input lines.
        String line = null;
        writer.write("Number of plates: " + number);
        writer.newLine();
        while ((line = reader.readLine()) != null) {
            if (!line.contains("Number of plates: ")) {
                writer.write(line);
                writer.newLine();
            }

        }

        //... Close reader and writer.
        reader.close();  // Close to unlock.
        writer.close();  // Close to unlock and flush to disk.
    }
}
