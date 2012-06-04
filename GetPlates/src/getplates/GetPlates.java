/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package getplates;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author madslundt
 */
public class GetPlates {

    public static long update = new Date().getTime();
    public static int countAll = 0;
    public static JFrame frame = new JFrame("Downloading plates");
    public static Container content = frame.getContentPane();
    public static JTextArea textArea = new JTextArea();
    public static JScrollPane scroll = new JScrollPane(textArea);
    public static String platesOldPath = "/Users/madslundt/Desktop/Plates/Plates/old";
    public static String platesPath    = "/Users/madslundt/Desktop/Plates/Plates";

    public static void main(String[] args) throws IOException {
        content.setLayout(new GridLayout(0, 1));
        content.add(scroll);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setEnabled(true);
        textArea.setVisible(true);
        frame.setVisible(true);
        frame.setSize(700, 400);
        frame.setResizable(false);
        textArea.setForeground(Color.white);
        textArea.setBackground(Color.black);
        for (String s: args) {
            if (s.contains("-platesold=")) {
                platesOldPath = s.split("=")[1];
            } else if (s.contains("-plates=")) {
                platesPath = s.split("=")[1];
            }
        }
        System.out.println("Path for old plates: " + platesOldPath);
        System.out.println("Path for new plates: " + platesPath);
        textArea.setText("Path for old plates: " + platesOldPath);
        textArea.append("\nPath for new plates: " + platesPath + "\n\n");
        getPlates();
        /*while (true) {
        // (1000*60*60)
        //    if (update + (1000*60*60) == new Date().getTime()) {
        textArea.append(update+"");
        update = new Date().getTime();
        getPlates();
        //}
        }*/
        //textArea.append(update);
    }

    public static void getPlates() throws IOException {
        File directoryOfPdfs = new File(platesOldPath); // Directory is just a list of files
        String filenames[] = null;
        List<String> list = new ArrayList<String>();
        if (directoryOfPdfs.isDirectory()) { // check to make sure it is a directory
            filenames = directoryOfPdfs.list();
        }
        if (filenames != null)
            for (int j = 0; j < filenames.length; j++) {
                list.add(filenames[j]);
            }
        File directoryOfPdfs2 = new File(platesPath);
        String filenames2[] = null;
        if (directoryOfPdfs2.isDirectory()) { // check to make sure it is a directory
            filenames2 = directoryOfPdfs2.list();
        }
        if (filenames2 != null)
            for (int j = 0; j < filenames2.length; j++) {
                list.add(filenames2[j]);
            }
        textArea.append(list.size() + " plates in folder\n\n");
        scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
        HashMap<String, Integer> bingo = new HashMap<String, Integer>();
        int nb = 9;
        while (true) {
            for (int k = 0; k < 10; k++) {
                String line, temp, script = "";

                try {
                    URL url = new URL("http://bingobanko.tv2.dk/print/?boardCount=" + nb);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));


                    while ((line = reader.readLine()) != null) {
                        if (line.contains("http://bingobankount.tv2.peytz.dk?")) {
                            script = line.split("\\?")[1].toString();
                            //textArea.append(line);
                        }
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(GetPlates.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(0);
                }
                textArea.append((k + 1) + "\t" + script + "\n");
                scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
                String urll;
                boolean down = false;
                for (int i = 0; i < nb; i++) {
                    if (!list.contains(script.split("=")[i + 1].subSequence(0, 39) + "1.png")) {
                        bingo.put("http://bingobanko.tv2.dk/board/" + script.split("=")[i + 1].subSequence(0, 39) + "1", 0);
                        down = true;
                    }
                }
                if (!down) {
                    textArea.append("\t\tNo new plates. Already in folder!\n");
                }
                scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
                sleepRand(3000);

            }

            textArea.append("\n\n\n+------------------------------------------+\n");
            if (bingo.size() > 0) {
                countAll += bingo.size();
                textArea.append("|   " + bingo.size() + " uniques          |\n");
                int count = downloadImage(bingo);
                textArea.append("|   " + count + "    saved out of " + bingo.size() + "   |\n");
                textArea.append("+------------------------------------------+\n");
                scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
                break;
            } else {
                if (countAll > 0) {
                    textArea.append("\tOverall " + countAll + " were downloaded succesfully!\n");
                    scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
                }
                textArea.append("|   Nothing found. Try again in one hour   |\n|   Closing in..:\t\t\t   |\n");
                textArea.append("+------------------------------------------+\n");
                scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
                int sleep = 0;
                for (int i = 10; i >= 0; i--) {
                    textArea.append(i + "\n");
                    scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
                    sleep(1000 - sleep);
                    sleep += 100;
                }
                msg("Downloaded plates", "Downloaded " + countAll + " plates succesfully");
                frame.dispose();
                System.exit(0);
            }
            textArea.append("\n\n");
            scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
        }
    }

    private static int downloadImage(HashMap<String, Integer> bingo) throws IOException {
        int count = 0, j = 1;
        for (String key : bingo.keySet()) {
            textArea.append(j + "\t" + System.getProperty("user.dir") + "/" + key.split("board/")[1] + ".png\n");
            scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
            try {
                URL url = new URL(key);
                BufferedImage bi = ImageIO.read(url);
                File f = new File("/Users/madslundt/Desktop/Plates/Plates/" + key.split("board/")[1] + ".png");
                if (!f.exists()) {
                    ImageIO.write(bi, "png", f);
                    count++;
                }

            } catch (MalformedURLException ex) {
                Logger.getLogger(GetPlates.class.getName()).log(Level.SEVERE, null, ex);
            }
            scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
            sleepRand(3000);
            j++;
        }
        return count;
    }

    private static void sleepRand(int msec) {
        int rand = 1000 + (int) (Math.random() * ((msec - 1000) + 1));
        try {
            Thread.currentThread().sleep(rand);
        } catch (InterruptedException ex) {
            Logger.getLogger(GetPlates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void sleep(int msec) {
        try {
            Thread.currentThread().sleep(msec);
        } catch (InterruptedException ex) {
            Logger.getLogger(GetPlates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void msg(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}