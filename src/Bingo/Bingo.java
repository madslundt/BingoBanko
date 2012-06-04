package Bingo;

import java.util.ArrayList;
import java.util.Arrays;
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
public class Bingo {

    // The plate
    private int[] plate = new int[15];
    
    // The Control number
    private String controlNumber;
    
    // MAX and MIN numbers for the plate
    private final int MAX = 90;
    private final int MIN = 1;
    
    // Control number length
    private final int CONTROL_NUMBER_LENGTH = 5;
    
    // Boolean to set if you have 1 row, 2 rows or 3 rows.
    private boolean row1 = false;
    private boolean row2 = false;
    private boolean bingo = false;

    /**
     * CONSTRUCTOR: npthing to set. plate and control number is null
     */
    public Bingo() {
    }

    /**
     * CONSTRUCTOR: set the plate with an array
     * @param plate array
     */
    public Bingo(int[] plate) {
        this.plate = plate;
    }

    /**
     * CONSTRUCTOR: set the plate with an array and control number (int)
     * @param plate array
     * @param cp int
     */
    public Bingo(int[] plate, int cp) {
        this.plate = plate;
        this.controlNumber = cp+"";
    }
    
    /**
     * CONSTRUCTOR: set the plate with an array and control number (String)
     * @param plate array
     * @param cp String
     */
    public Bingo(int[] plate, String cp) {
        this.plate = plate;
        this.controlNumber = cp;
    }

    /**
     * CONSTRUCTOR: set the plate (int)
     * @param a int
     * @param b int
     * @param c int
     * @param d int
     * @param e int
     * @param aa int
     * @param bb int
     * @param cc int 
     * @param dd int
     * @param ee int
     * @param aaa int
     * @param bbb int
     * @param ccc int
     * @param ddd int 
     * @param eee int
     */
    public Bingo(int a, int b, int c, int d, int e,
            int aa, int bb, int cc, int dd, int ee,
            int aaa, int bbb, int ccc, int ddd, int eee) {
        // First row
        plate[0] = a;
        plate[1] = b;
        plate[2] = c;
        plate[3] = d;
        plate[4] = e;

        // Second row
        plate[5] = aa;
        plate[6] = bb;
        plate[7] = cc;
        plate[8] = dd;
        plate[9] = ee;

        // Third row
        plate[10] = aaa;
        plate[11] = bbb;
        plate[12] = ccc;
        plate[13] = ddd;
        plate[14] = eee;
    }

    /**
     * CONSTRUCTOR: set the plate (int) and control number (int)
     * @param a int
     * @param b int
     * @param c int
     * @param d int
     * @param e int
     * @param aa int
     * @param bb int
     * @param cc int
     * @param dd int
     * @param ee int
     * @param aaa int 
     * @param bbb int
     * @param ccc int
     * @param ddd int
     * @param eee int
     * @param cp  int
     */
    public Bingo(int a, int b, int c, int d, int e,
            int aa, int bb, int cc, int dd, int ee,
            int aaa, int bbb, int ccc, int ddd, int eee,
            int cp) {
        // First row
        plate[0] = a;
        plate[1] = b;
        plate[2] = c;
        plate[3] = d;
        plate[4] = e;

        // Second row
        plate[5] = aa;
        plate[6] = bb;
        plate[7] = cc;
        plate[8] = dd;
        plate[9] = ee;

        // Third row
        plate[10] = aaa;
        plate[11] = bbb;
        plate[12] = ccc;
        plate[13] = ddd;
        plate[14] = eee;

        // Control number
        this.controlNumber = cp+"";
    }
    
    /**
     * CONSTRUCTOR: set the plate (int) and control number (String)
     * @param a int
     * @param b int
     * @param c int
     * @param d int
     * @param e int
     * @param aa int
     * @param bb int
     * @param cc int
     * @param dd int
     * @param ee int
     * @param aaa int 
     * @param bbb int
     * @param ccc int
     * @param ddd int
     * @param eee int
     * @param cp  String
     */
    public Bingo(int a, int b, int c, int d, int e,
            int aa, int bb, int cc, int dd, int ee,
            int aaa, int bbb, int ccc, int ddd, int eee,
            String cp) {
        // First row
        plate[0] = a;
        plate[1] = b;
        plate[2] = c;
        plate[3] = d;
        plate[4] = e;

        // Second row
        plate[5] = aa;
        plate[6] = bb;
        plate[7] = cc;
        plate[8] = dd;
        plate[9] = ee;

        // Third row
        plate[10] = aaa;
        plate[11] = bbb;
        plate[12] = ccc;
        plate[13] = ddd;
        plate[14] = eee;

        // Control number
        this.controlNumber = cp;
    }

    /**
     * Sets the next number equals to 0 in the plate (int)
     * @param a int
     */
    public void setPlate(int a) {
        for (int i = 0; i < plate.length; i++) {
            if (plate[i] == 0) {
                plate[i] = a;
                break;
            }
        }
    }

    /**
     * Sets the plate with int and control number (int)
     * @param a int
     * @param b int
     * @param c int
     * @param d int
     * @param e int
     * @param aa int
     * @param bb int
     * @param cc int
     * @param dd int
     * @param ee int
     * @param aaa int
     * @param bbb int
     * @param ccc int
     * @param ddd int
     * @param eee int
     * @param cp int
     */
    public void setPlate(int a, int b, int c, int d, int e,
            int aa, int bb, int cc, int dd, int ee,
            int aaa, int bbb, int ccc, int ddd, int eee,
            int cp) {
        // First row
        plate[0] = a;
        plate[1] = b;
        plate[2] = c;
        plate[3] = d;
        plate[4] = e;

        // Second row
        plate[5] = aa;
        plate[6] = bb;
        plate[7] = cc;
        plate[8] = dd;
        plate[9] = ee;

        // Third row
        plate[10] = aaa;
        plate[11] = bbb;
        plate[12] = ccc;
        plate[13] = ddd;
        plate[14] = eee;

        // Control number
        this.controlNumber = cp+"";
    }
    
    /**
     * Sets the plate with int and control number (String)
     * @param a int
     * @param b int
     * @param c int
     * @param d int
     * @param e int
     * @param aa int
     * @param bb int
     * @param cc int
     * @param dd int
     * @param ee int
     * @param aaa int
     * @param bbb int
     * @param ccc int
     * @param ddd int
     * @param eee int
     * @param cp String
     */
    public void setPlate(int a, int b, int c, int d, int e,
            int aa, int bb, int cc, int dd, int ee,
            int aaa, int bbb, int ccc, int ddd, int eee,
            String cp) {
        // First row
        plate[0] = a;
        plate[1] = b;
        plate[2] = c;
        plate[3] = d;
        plate[4] = e;

        // Second row
        plate[5] = aa;
        plate[6] = bb;
        plate[7] = cc;
        plate[8] = dd;
        plate[9] = ee;

        // Third row
        plate[10] = aaa;
        plate[11] = bbb;
        plate[12] = ccc;
        plate[13] = ddd;
        plate[14] = eee;

        // Control number
        this.controlNumber = cp;
    }

    /**
     * Sets the plate with int
     * @param a int
     * @param b int
     * @param c int
     * @param d int 
     * @param e int
     * @param aa int
     * @param bb int
     * @param cc int
     * @param dd int
     * @param ee int
     * @param aaa int 
     * @param bbb int 
     * @param ccc int
     * @param ddd int
     * @param eee int
     */
    public void setPlate(int a, int b, int c, int d, int e,
            int aa, int bb, int cc, int dd, int ee,
            int aaa, int bbb, int ccc, int ddd, int eee) {
        // First row
        plate[0] = a;
        plate[1] = b;
        plate[2] = c;
        plate[3] = d;
        plate[4] = e;

        // Second row
        plate[5] = aa;
        plate[6] = bb;
        plate[7] = cc;
        plate[8] = dd;
        plate[9] = ee;

        // Third row
        plate[10] = aaa;
        plate[11] = bbb;
        plate[12] = ccc;
        plate[13] = ddd;
        plate[14] = eee;
    }

    /**
     * Sets the plate with an array
     * @param plate array
     */
    public void setPlate(int[] plate) {
        this.plate = plate;
    }

    /**
     * Sets the plate with an array and control number (int)
     * @param plate array
     * @param cp int
     */
    public void setPlate(int[] plate, int cp) {
        this.plate = plate;
        this.controlNumber = cp+"";
    }
    
    /**
     * Sets the plate with an array and control number (String)
     * @param plate array
     * @param cp String
     */
    public void setPlate(int[] plate, String cp) {
        this.plate = plate;
        this.controlNumber = cp;
    }

    /**
     * Sets the first row (int)
     * @param a int
     * @param b int
     * @param c int
     * @param d int
     * @param e int
     */
    public void setRow1(int a, int b, int c, int d, int e) {
        plate[0] = a;
        plate[1] = b;
        plate[2] = c;
        plate[3] = d;
        plate[4] = e;
    }

    /**
     * Sets the second row (int)
     * @param a int
     * @param b int
     * @param c int
     * @param d int
     * @param e int
     */
    public void setRow2(int a, int b, int c, int d, int e) {
        plate[5] = a;
        plate[6] = b;
        plate[7] = c;
        plate[8] = d;
        plate[9] = e;
    }

    /**
     * Sets the third row (int
     * @param a int
     * @param b int
     * @param c int
     * @param d int
     * @param e int
     */
    public void setRow3(int a, int b, int c, int d, int e) {
        plate[10] = a;
        plate[11] = b;
        plate[12] = c;
        plate[13] = d;
        plate[14] = e;
    }

    /**
     * Sets the control number (int)
     * @param cp int
     */
    public void setControlNumber(int cp) {
        this.controlNumber = cp+"";
    }
    
    /**
     * Sets the control number (String)
     * @param cp String
     */
    public void setControlNumber(String cp) {
        this.controlNumber = cp;
    }

    /**
     * Return plate (array)
     * @return array
     */
    public int[] getPlate() {
        return plate;
    }

    /**
     * Return the control number (String)
     * @return int
     */
    public String getControlNumber() {
        return this.controlNumber;
    }

    /**
     * Return first row (array)
     * @return array
     */
    public int[] getRow1() {
        int[] row1 = new int[5];
        for (int i = 0; i < row1.length; i++) {
            row1[i] = plate[i];
        }
        return row1;
    }

    /**
     * Return second row (array)
     * @return array
     */
    public int[] getRow2() {
        int[] row2 = new int[5];
        for (int i = 0; i < row2.length; i++) {
            row2[i] = plate[i + 5];
        }
        return row2;
    }

    /**
     * Return third row (array)
     * @return array
     */
    public int[] getRow3() {
        int[] row3 = new int[5];
        for (int i = 0; i < row3.length; i++) {
            row3[i] = plate[i + 10];
        }
        return row3;
    }

    /**
     * Return one number at index of the plate array
     * @param index int
     * @return int
     * @throws ArrayIndexOutOfBoundsException if index is > than length of plate array
     */
    public int getOneNumber(int index) throws ArrayIndexOutOfBoundsException {
        if (index >= plate.length) {
            throw new StackOverflowError();
        }
        int number = plate[index];

        return number;
    }

    /**
     * Return true if plate is ready: If the plate has full plate and control number
     * @return boolean
     */
    public boolean isReady() {
        boolean tjek = false;
        if (controlNumber.length() == CONTROL_NUMBER_LENGTH) {
            tjek = true;
        } else {
            return false;
        }

        for (int i = 0; i < plate.length; i++) {
            if (plate[i] >= MIN && plate[i] <= MAX) {
                tjek = true;
            } else {
                return false;
            }
        }

        return tjek;
    }

    /**
     * Return true if plate is ready: If the plate has full plate and control number.
     * With debug!
     * @param debug boolean
     * @return boolean with prints
     */
    public boolean isReady(boolean debug) {
        boolean tjek = false;

        if (controlNumber.length() == CONTROL_NUMBER_LENGTH) {
            tjek = true;
        } else {
            System.out.println("Couldn't find Control Number");
            return false;
        }

        for (int i = 0; i < plate.length; i++) {
            if (plate[i] >= MIN && plate[i] <= MAX) {
                tjek = true;
            } else {
                System.out.println("Wrong numbers in plate: " + i + " with number " + plate[i]);
                System.out.println("Next number: " + plate[i + 1]);
                return false;
            }
        }

        return tjek;
    }

    /**
     * Return true if the plate has 1 row
     * @param numbers List<Integer>
     * @return boolean
     */
    public boolean is1Row(List<Integer> numbers) {
        if (checkPlate(numbers) == 1) {
            row1 = true;
        }
        return row1;
    }

    /**
     * Return true if the plate has 2 rows
     * @param numbers List<Integer>
     * @return boolean
     */
    public boolean is2Row(List<Integer> numbers) {
        if (checkPlate(numbers) == 2) {
            row2 = true;
        }
        return row2;
    }

    /**
     * Return true if the plate has Bingo
     * @param numbers List<Integer>
     * @return boolean
     */
    public boolean isBingo(List<Integer> numbers) {
        if (checkPlate(numbers) == 3) {
            bingo = true;
        }
        return bingo;
    }

    /**
     * Check the plate for 1 row, 2 row and Bingo
     * @param numbers List<Bingo>
     * @return int: 1 for 1 row, 2 for 2 rows, 3 for Bingo and 0 for nothing
     */
    public int checkPlate(List<Integer> numbers) {
        if (!isReady()) {
            return -1;
        }

        int rows1 = 0;
        int rows2 = 0;
        int rows3 = 0;
        int[] Row1 = getRow1();
        int[] Row2 = getRow2();
        int[] Row3 = getRow3();
        for (int a = 0; a < Row1.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row1[a]) {
                    rows1++;
                    break;
                }
            }
        }
        for (int a = 0; a < Row2.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row2[a]) {
                    rows2++;
                    break;
                }
            }
        }
        for (int a = 0; a < Row3.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row3[a]) {
                    rows3++;
                    break;
                }
            }
        }
        
        if (rows1 + rows2 + rows3 == 15) {
            bingo = true;
            return 3;
        } else {
            bingo = false;
        }
        if ((rows1 == 5 && rows2 == 5) || (rows1 == 5 && rows3 == 5) || (rows2 == 5 && rows3 == 5)) {
            row2 = true;
            return 2;
        } else {
            row2 = false;
        }
        if (rows1 == 5 || rows2 == 5 || rows3 == 5) {
            row1 = true;
            return 1;
        } else {
            row1 = false;
        }
        return 0;
    }

    /**
     * To get length of an integer
     * @param a int
     * @return int
     */
    private int getIntLength(int a) {
        int length = 0;
        String str = a + "";
        length = str.length();
        return length;
    }

    /**
     * Change number with a new number on index on plate
     * @param number int
     * @param index int
     */
    public void changeNumber(int number, int index) {
        if (index >= plate.length) {
            throw new StackOverflowError();
        }
        plate[index] = number;
    }

    /**
     * Reset number to 0 at index on plate
     * @param index int
     */
    public void resetNumber(int index) {
        plate[index] = 0;
    }

    /**
     * Replace the numbers from a List with 0.
     * @param numbers List<Integer>
     * @return Bingo
     */
    public Bingo replaceNumber(List<Integer> numbers) {
        Bingo newBin = new Bingo();
        newBin = copyBingo();
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < 15; j++) {
                if (numbers.get(i) == newBin.getOneNumber(j)) {
                    newBin.changeNumber(000, j);
                    break;
                }
            }
        }
        return newBin;
    }
    
    /**
     * Print plate but with numbers from a List replaced with ***
     * @param numbers List<Integer>
     * @return String
     */
    public String getNumbers(List<Integer> numbers) {
        Bingo newBin = replaceNumber(numbers);
        String rows = "";
        for (int i = 0; i < 15; i++) {
            if (i == 5 || i == 10) {
                rows += "\n";
            }
            if (newBin.getOneNumber(i) == 0) {
                rows += "***\t";
            } else {
                rows += newBin.getOneNumber(i) + "\t";
            }
        }
        return rows;
    }

    /**
     * Print plate but all numbers equals to 0 print as ***
     * @return String
     */
    public String getNumbers() {
        String rows = "";
        for (int i = 0; i < plate.length; i++) {
            if (i == 5 || i == 10) {
                rows += "\n";
            }
            if (plate[i] == 0) {
                rows += "***\t";
            } else {
                rows += plate[i] + "\t";
            }
        }
        return rows;
    }

    /**
     * Print the plate and control number
     * @return String
     */
    public String plateToString() {
        String rows = "";
        for (int i = 0; i < plate.length; i++) {
            if (i == 5 || i == 10) {
                rows += "\n";
            }
            rows += plate[i] + "\t";
        }
        String cp = controlNumber + "";
        return rows + "\nControl:\t" + cp;
    }

    /**
     * Numbers to get 1 row
     * @param numbers List<Integer>
     * @return int
     */
    public int numbersToRow1(List<Integer> numbers) {
        
        int rows1 = 0;
        int rows2 = 0;
        int rows3 = 0;
        int[] Row1 = getRow1();
        int[] Row2 = getRow2();
        int[] Row3 = getRow3();
        for (int a = 0; a < Row1.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row1[a]) {
                    rows1++;
                    break;
                }
            }
        }
        for (int a = 0; a < Row2.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row2[a]) {
                    rows2++;
                    break;
                }
            }
        }
        for (int a = 0; a < Row3.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row3[a]) {
                    rows3++;
                    break;
                }
            }
        }
        int max = Math.max(rows1, Math.max(rows2, rows3));
        return 5 - max;

    }

    /**
     * Numbers to get 2 row
     * @param numbers List<Integer>
     * @return int
     */
    public int numbersToRow2(List<Integer> numbers) {
        
        int rows1 = 0;
        int rows2 = 0;
        int rows3 = 0;
        int[] Row1 = getRow1();
        int[] Row2 = getRow2();
        int[] Row3 = getRow3();
        for (int a = 0; a < Row1.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row1[a]) {
                    rows1++;
                    break;
                }
            }
        }
        for (int a = 0; a < Row2.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row2[a]) {
                    rows2++;
                    break;
                }
            }
        }
        for (int a = 0; a < Row3.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row3[a]) {
                    rows3++;
                    break;
                }
            }
        }
        int max = Math.max((rows1 + rows2), Math.max((rows1 + rows3), (rows2 + rows3)));
        return 10 - max;
    }

    /**
     * Numbers to get Bingo
     * @param numbers List<Integer>
     * @return int
     */
    public int numbersToBingo(List<Integer> numbers) {
        
        int rows1 = 0;
        int rows2 = 0;
        int rows3 = 0;
        int[] Row1 = getRow1();
        int[] Row2 = getRow2();
        int[] Row3 = getRow3();
        for (int a = 0; a < Row1.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row1[a]) {
                    rows1++;
                    break;
                }
            }
        }
        for (int a = 0; a < Row2.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row2[a]) {
                    rows2++;
                    break;
                }
            }
        }
        for (int a = 0; a < Row3.length; a++) {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i) == Row3[a]) {
                    rows3++;
                    break;
                }
            }
        }
        int max = rows1 + rows2 + rows3;
        return 15 - max;
    }

    /**
     * Generate random plates and control number
     * @return Bingo
     */
    public Bingo generateRandom() {
        Bingo bin = new Bingo();
        int[] array = new int[15];
        ArrayList<Integer> shuffle = new ArrayList<Integer>();
        for (int i = 0; i < 90; i++) {
            shuffle.add(i + 1);
        }
        Collections.shuffle(shuffle);
        for (int i = 0; i < 15; i++) {
            array[i] = shuffle.get(i);
        }
        Arrays.sort(array);
        bin.setPlate(array, (int) ((Math.random() * 99999) + 10000));
        return bin;
    }

    /**
     * Reset plate and control number
     */
    public void reset() {
        plate = new int[15];
        controlNumber = null;
    }

    /**
     * Copy a Bingo to a new Bingo
     * @return Bingo
     */
    public Bingo copyBingo() {
        Bingo newBingo = new Bingo();
        int[] newPlate = new int[15];
        newPlate = this.getPlate().clone();
        newBingo.setPlate(newPlate);
        newBingo.setControlNumber(this.getControlNumber());

        return newBingo;

    }
}