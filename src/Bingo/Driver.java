/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bingo;

/**
 *
 * @author MadsLundt
 */
public class Driver {
    public static void main(String[] args) {
        BingoGUI binGUI = new BingoGUI();
        Bingo bin = new Bingo();
        binGUI.setEnabled(true);
        binGUI.setVisible(true);
    }
}
