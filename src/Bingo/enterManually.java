/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * enterManually.java
 *
 * Created on 10-04-2011, 15:35:46
 */
package Bingo;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author madssx
 */
public class enterManually extends javax.swing.JFrame {

    private List<Bingo> savePlates = new ArrayList<Bingo>();
    private String language = "english";
    private String strSaveFileTitle = "Save file";
    private String strSaveFileMsg = "The file already exist.\nDo you want to overwrite?";
    private String strCreateFileTitle = "Create file";
    private String strCreateFileMsg = "Couldn't create file!";
    private String strAddPlatesTitle = "Add plates";
    private String strAddPlatesMsg = "Numbers must be between 1-90";
    private String strDeleteTitle = "Delete";
    private String strDeleteMsg = "You are going to delete all your plates.\n\n Are you sure?";
    private String strWritingFileTitle = "Writing to file";
    private String strWritingFileMsg = "Couldn't write to file";
    private String strAllDeletedTitle = "Add Manually";
    private String strAllDeletedMsg = "All plates were deleted";
    private String strNoDeleteTitle = "Add Manually";
    private String strNoDeleteMsg = "No plates were deleted";
    private String strNoPlatesTitle = "No plates entered";
    private String strNoPlatesMsg = "No plates entered.\nPlease enter some plates before saving.";

    /** Creates new form enterManually */
    public enterManually() {
        initComponents();
        loadLanguage();
    }
    private int count = 0;
    private boolean haveSetted = false;
    private String old;

    private void loadLanguage() {
        if (language.equalsIgnoreCase("danish")) {
            jLabel1.setText("1. række");
            jLabel2.setText("2. række");
            jLabel3.setText("3. række");
            jLabel4.setText("Plade nummer");
            jButton1.setText("Tilføj");
            saveButtonManually.setText("Gem");
            jButton2.setText("Ryd pladen");
            jMenu1.setText("Filer");
            jMenu2.setText("Rediger");
            jMenuItem1.setText("Åbn");
            jMenuItem3.setText("Ny plade");
            jMenuItem4.setText("Luk");
            jMenuItem5.setText("Ryd pladen");
            jMenuItem2.setText("Slet alle plader");
            this.setTitle("Indtast selv");
            strSaveFileTitle = "Gem fil";
            strSaveFileMsg = "Filen eksisterer allerede.\nVil du overskrive filen?";
            strCreateFileTitle = "Ny fil";
            strCreateFileMsg = "Kunne ikke oprette fil!";
            strAddPlatesTitle = "Tilføj plader";
            strAddPlatesMsg = "Numrene skal være mellem 1-90";
            strDeleteTitle = "Slet";
            strDeleteMsg = "Du er på vej til at slette alle pladerne.\n Er du sikker?";
            strWritingFileTitle = "Skrivning til fil";
            strWritingFileMsg = "Kunne ikke skrive til fil";
            strAllDeletedTitle = "Indtast selv";
            strAllDeletedMsg = "Alle pladerne er slettet";
            strNoDeleteTitle = "Indtast selv";
            strNoDeleteMsg = "Ingen plader er slettet";
            strNoPlatesTitle = "Ingen plader er indtastet";
            strNoPlatesMsg = "Ingen plader er indtastet.\nIndtast nogle plader inden du gemmer";
        } else {
            jLabel1.setText("1. row");
            jLabel2.setText("2. row");
            jLabel3.setText("3. row");
            jLabel4.setText("Plate number");
            jButton1.setText("Add");
            saveButtonManually.setText("Save");
            jButton2.setText("Clear");
            jMenu1.setText("File");
            jMenu2.setText("Edit");
            jMenuItem1.setText("Load");
            jMenuItem3.setText("Create new");
            jMenuItem4.setText("Exit");
            jMenuItem5.setText("Clear");
            jMenuItem2.setText("Delete all");
            this.setTitle("Add Manually");
            strSaveFileTitle = "Save file";
            strSaveFileMsg = "The file already exist.\nDo you want to overwrite?";
            strCreateFileTitle = "Create file";
            strCreateFileMsg = "Couldn't create file!";
            strAddPlatesTitle = "Add plates";
            strAddPlatesMsg = "Numbers must be between 1-90";
            strDeleteTitle = "Delete";
            strDeleteMsg = "You are going to delete all your plates.\n\n Are you sure?";
            strWritingFileTitle = "Writing to file";
            strWritingFileMsg = "Couldn't write to file";
            strAllDeletedTitle = "Add Manually";
            strAllDeletedMsg = "All plates were deleted";
            strNoDeleteTitle = "Add Manually";
            strNoDeleteMsg = "No plates were deleted";
            strNoPlatesTitle = "No plates entered";
            strNoPlatesMsg = "No plates entered.\nPlease enter some plates before saving.";
        }
    }

    private boolean confirm(String title, String message) {
        int ask = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return (ask == JOptionPane.YES_OPTION);
    }

    private static void msg(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    private String path = "src/Bingo/first.en";
    private File f = new File(path);

    private boolean containsOnlyNumbers(String str) {
        if (!str.isEmpty()) {

            int a;
            try {
                a = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return false;
            }
            if (!containsOnlyControlNumbers(str) || a < 1 || a > 90) {
                return false;
            }
            return true;
        }
        return false;

    }

    private boolean containsOnlyControlNumbers(String str) {
        int a;
        try {
            a = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {

            //If we find a non-digit character we return false.
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private boolean plateOnlyNumbers() {
        return (containsOnlyNumbers(jTextField1.getText()) && containsOnlyNumbers(jTextField2.getText()) && containsOnlyNumbers(jTextField3.getText())
                && containsOnlyNumbers(jTextField4.getText()) && containsOnlyNumbers(jTextField5.getText())
                && containsOnlyNumbers(jTextField6.getText()) && containsOnlyNumbers(jTextField7.getText()) && containsOnlyNumbers(jTextField8.getText())
                && containsOnlyNumbers(jTextField9.getText()) && containsOnlyNumbers(jTextField10.getText())
                && containsOnlyNumbers(jTextField11.getText()) && containsOnlyNumbers(jTextField12.getText()) && containsOnlyNumbers(jTextField13.getText())
                && containsOnlyNumbers(jTextField14.getText()) && containsOnlyNumbers(jTextField15.getText()) && containsOnlyControlNumbers(jTextField16.getText()));
    }

    private boolean plateMaxLength() {
        return (jTextField1.getText().length() <= 2 && jTextField2.getText().length() <= 2 && jTextField3.getText().length() <= 2 && jTextField4.getText().length() <= 2
                && jTextField5.getText().length() <= 2
                && jTextField6.getText().length() <= 2 && jTextField7.getText().length() <= 2 && jTextField8.getText().length() <= 2 && jTextField9.getText().length() <= 2
                && jTextField10.getText().length() <= 2
                && jTextField11.getText().length() <= 2 && jTextField12.getText().length() <= 2 && jTextField13.getText().length() <= 2 && jTextField14.getText().length() <= 2
                && jTextField15.getText().length() <= 2
                && jTextField1.getText().length() > 0 && jTextField2.getText().length() > 0 && jTextField3.getText().length() > 0 && jTextField4.getText().length() > 0
                && jTextField5.getText().length() > 0
                && jTextField6.getText().length() > 0 && jTextField7.getText().length() > 0 && jTextField8.getText().length() > 0 && jTextField9.getText().length() > 0
                && jTextField10.getText().length() > 0
                && jTextField11.getText().length() > 0 && jTextField12.getText().length() > 0 && jTextField13.getText().length() > 0 && jTextField14.getText().length() > 0
                && jTextField15.getText().length() > 0 && jTextField16.getText().length() > 0);
    }

    private void fileChooserOpen() {
        BingoGUI bin = new BingoGUI();
        JFileChooser fc = new JFileChooser(f);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath();
            bin.setPath(path);
            UserData.loadData(path);
            count = UserData.getCount();
        }

        if (new File(path).getName().contains(".da")) {
            setLanguage("danish");
        } else {
            setLanguage("english");
        }
    }

    private void fileChooserSave() {
        String lang = ".en";
        if (strDeleteTitle.equalsIgnoreCase("slet")) {
            lang = ".da";
        }
        JFileChooser fc = new JFileChooser(f);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (fc.getSelectedFile().getPath().substring(fc.getSelectedFile().getPath().length() - 4, fc.getSelectedFile().getPath().length()).equals(".txt")) {
                path = fc.getSelectedFile().getPath() + lang;
                if (fc.getSelectedFile().getName().contains(".da") || fc.getSelectedFile().getName().contains(".en")) {
                    path = fc.getSelectedFile().getPath();
                }
            } else {
                path = fc.getSelectedFile().getPath() + lang;
                if (fc.getSelectedFile().getName().contains(".da") || fc.getSelectedFile().getName().contains(".en")) {
                    path = fc.getSelectedFile().getPath();
                }
            }

            try {
                f = new File(path);

                if (!f.exists()) {
                    f.createNewFile();
                    FileWriter FW = new FileWriter(path, false);
                    FW.write("");
                    FW.close();
                } else {
                    f.setWritable(true, true);
                    if (confirm(strSaveFileTitle, strSaveFileMsg)) {
                        f.createNewFile();
                        FileWriter FW = new FileWriter(path, false);
                        FW.write("");
                        FW.close();
                    }
                }
            } catch (IOException e) {
                msg(strCreateFileTitle, strCreateFileMsg);
            }
            count = 0;
        }
        if (f.getName().contains(".da")) {
            setLanguage("danish");
        } else {
            setLanguage("english");
        }
        f.setWritable(false, false);
    }

    private boolean savePlate() {
        if (plateOnlyNumbers()) {
            if (plateMaxLength()) {
                Bingo bin = new Bingo();
                int[] tempPlate = new int[15];
                tempPlate[0] = Integer.parseInt(jTextField1.getText());
                tempPlate[1] = Integer.parseInt(jTextField2.getText());
                tempPlate[2] = Integer.parseInt(jTextField3.getText());
                tempPlate[3] = Integer.parseInt(jTextField4.getText());
                tempPlate[4] = Integer.parseInt(jTextField5.getText());
                tempPlate[5] = Integer.parseInt(jTextField6.getText());
                tempPlate[6] = Integer.parseInt(jTextField7.getText());
                tempPlate[7] = Integer.parseInt(jTextField8.getText());
                tempPlate[8] = Integer.parseInt(jTextField9.getText());
                tempPlate[9] = Integer.parseInt(jTextField10.getText());
                tempPlate[10] = Integer.parseInt(jTextField11.getText());
                tempPlate[11] = Integer.parseInt(jTextField12.getText());
                tempPlate[12] = Integer.parseInt(jTextField13.getText());
                tempPlate[13] = Integer.parseInt(jTextField14.getText());
                tempPlate[14] = Integer.parseInt(jTextField15.getText());
                bin.setPlate(tempPlate);
                bin.setControlNumber(Integer.parseInt(jTextField16.getText()));
                savePlates.add(bin);
            } else {
                msg(strAddPlatesTitle, strAddPlatesMsg);
                return false;
            }
        } else {
            msg(strAddPlatesTitle, strAddPlatesMsg);
            return false;
        }
        return true;
    }

    private void resetNumbers() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField16 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        addInfo = new javax.swing.JLabel();
        numberOfPlates = new javax.swing.JLabel();
        saveButtonManually = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        label1.setText("label1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Manually");
        setMaximumSize(new java.awt.Dimension(400, 330));
        setMinimumSize(new java.awt.Dimension(400, 330));
        setPreferredSize(new java.awt.Dimension(400, 330));
        setResizable(false);
        setSize(new java.awt.Dimension(400, 330));

        jTextField1.setNextFocusableComponent(jTextField2);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jTextField2.setNextFocusableComponent(jTextField3);
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jTextField3.setNextFocusableComponent(jTextField4);
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jTextField4.setNextFocusableComponent(jTextField5);
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jTextField5.setNextFocusableComponent(jTextField6);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        jTextField6.setNextFocusableComponent(jTextField7);
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });

        jTextField7.setNextFocusableComponent(jTextField8);
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });

        jTextField8.setNextFocusableComponent(jTextField9);
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField8FocusLost(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });

        jTextField9.setNextFocusableComponent(jTextField10);
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });

        jTextField10.setNextFocusableComponent(jTextField11);
        jTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField10FocusLost(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField10KeyTyped(evt);
            }
        });

        jTextField11.setNextFocusableComponent(jTextField12);
        jTextField11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField11FocusLost(evt);
            }
        });
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField11KeyTyped(evt);
            }
        });

        jTextField12.setNextFocusableComponent(jTextField13);
        jTextField12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField12FocusLost(evt);
            }
        });
        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField12KeyTyped(evt);
            }
        });

        jTextField13.setNextFocusableComponent(jTextField14);
        jTextField13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField13FocusLost(evt);
            }
        });
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField13KeyTyped(evt);
            }
        });

        jTextField14.setNextFocusableComponent(jTextField15);
        jTextField14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField14FocusLost(evt);
            }
        });
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField14KeyTyped(evt);
            }
        });

        jTextField15.setNextFocusableComponent(jTextField16);
        jTextField15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField15FocusLost(evt);
            }
        });
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField15KeyTyped(evt);
            }
        });

        jButton1.setText("Add");
        jButton1.setNextFocusableComponent(jTextField1);
        jButton1.setRequestFocusEnabled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jTextField16.setNextFocusableComponent(jButton1);
        jTextField16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField16KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField16KeyPressed(evt);
            }
        });

        jLabel1.setText("1. row");

        jLabel2.setText("2. row");

        jLabel3.setText("3. row");

        jButton2.setText("Clear");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Plate number");

        numberOfPlates.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        saveButtonManually.setText("Save");
        saveButtonManually.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonManuallyActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Open");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Create new");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseReleased(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem5.setText("Clear");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem2.setText("Delete all");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseReleased(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jLabel3)
                            .add(layout.createSequentialGroup()
                                .add(jTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel4)
                                    .add(layout.createSequentialGroup()
                                        .add(21, 21, 21)
                                        .add(jTextField16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 98, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(addInfo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(saveButtonManually, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(8, 8, 8))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel1)
                                    .add(layout.createSequentialGroup()
                                        .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(jLabel2))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 68, Short.MAX_VALUE)
                                .add(jButton1)))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .add(18, 18, 18)
                        .add(numberOfPlates, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 142, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(117, 117, 117))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(4, 4, 4)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel2)
                        .add(2, 2, 2)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel4)
                                .add(5, 5, 5)
                                .add(jTextField16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(addInfo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(saveButtonManually, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(numberOfPlates, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
    }//GEN-LAST:event_jButton2MouseReleased

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
    }//GEN-LAST:event_jButton1MouseReleased

    private void jTextField16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyPressed
        if (!haveSetted) {
            haveSetted = true;
        }
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            if (savePlate()) {
                count++;
                addInfo.setText("Plate " + jTextField16.getText() + " were added!");
                if (language.equalsIgnoreCase("danish")) {
                    addInfo.setText("Plade " + jTextField16.getText() + " tilføjet!");
                }
                if (count < 2) {
                    numberOfPlates.setText(count + " plate");
                    if (language.equalsIgnoreCase("danish")) {
                        numberOfPlates.setText(count + " plade");
                    }
                } else {
                    numberOfPlates.setText(count + " plates");
                    if (language.equalsIgnoreCase("danish")) {
                        numberOfPlates.setText(count + " plader");
                    }
                }

                resetNumbers();
            }
            jTextField1.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField16KeyPressed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        setTextFieldFocus(jTextField1);
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        if (jTextField1.getText().length() > 1) {
            jTextField1.selectAll();
        }

        if (jTextField1.getText().length() == 1) {
            jTextField2.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        if (jTextField2.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField2.getText().length() == 1) {
            jTextField3.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        if (jTextField3.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField3.getText().length() == 1) {
            jTextField4.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        if (jTextField4.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField4.getText().length() == 1) {
            jTextField5.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        if (jTextField5.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField5.getText().length() == 1) {
            jTextField6.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        if (jTextField6.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField6.getText().length() == 1) {
            jTextField7.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        if (jTextField7.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField7.getText().length() == 1) {
            jTextField8.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        if (jTextField8.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField8.getText().length() == 1) {
            jTextField9.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
        if (jTextField9.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField9.getText().length() == 1) {
            jTextField10.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped
        if (jTextField10.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField10.getText().length() == 1) {
            jTextField11.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField10KeyTyped

    private void jTextField11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyTyped
        if (jTextField11.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField11.getText().length() == 1) {
            jTextField12.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField11KeyTyped

    private void jTextField12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyTyped
        if (jTextField12.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField12.getText().length() == 1) {
            jTextField13.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField12KeyTyped

    private void jTextField13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyTyped
        if (jTextField13.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField13.getText().length() == 1) {
            jTextField14.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField13KeyTyped

    private void jTextField14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyTyped
        if (jTextField14.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField14.getText().length() == 1) {
            jTextField15.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField14KeyTyped

    private void jTextField15KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyTyped
        if (jTextField15.getText().length() > 1) {
            evt.consume();
        }
        if (jTextField15.getText().length() == 1) {
            jTextField16.requestFocusInWindow();
        }
    }//GEN-LAST:event_jTextField15KeyTyped

    private void jTextField16KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField16KeyTyped
        if (jTextField16.getText().length() > 4) {
            evt.consume();
            //jTextField16.setText(jTextField16.getText().substring(0, 4));
        }
    }//GEN-LAST:event_jTextField16KeyTyped

    private void jMenuItem2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseReleased
    }//GEN-LAST:event_jMenuItem2MouseReleased

    private void jMenuItem1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseReleased
    }//GEN-LAST:event_jMenuItem1MouseReleased

    private void jMenuItem3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseReleased
    }//GEN-LAST:event_jMenuItem3MouseReleased

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField1.requestFocusInWindow();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (!haveSetted) {
            haveSetted = true;
        }
        if (savePlate()) {
            count++;
            addInfo.setText("Plate " + jTextField16.getText() + " were added!");
            if (language.equalsIgnoreCase("danish")) {
                addInfo.setText("Plade " + jTextField16.getText() + " tilføjet!");
            }
            if (count < 2) {
                numberOfPlates.setText(count + " plate");
                if (language.equalsIgnoreCase("danish")) {
                    numberOfPlates.setText(count + " plade");
                }
            } else {
                numberOfPlates.setText(count + " plates");
                if (language.equalsIgnoreCase("danish")) {
                    numberOfPlates.setText(count + " plader");
                }
            }

            resetNumbers();
        }
        jTextField1.requestFocusInWindow();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        fileChooserOpen();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        fileChooserSave();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (confirm(strDeleteTitle, strDeleteMsg)) {
            f.delete();
            f.renameTo(new File(path));
            count = 0;
            numberOfPlates.setText("No plates added!");
            if (language.equalsIgnoreCase("danish")) {
                numberOfPlates.setText("Ingen plader tilføjet!");
            }
            try {
                f.createNewFile();
            } catch (IOException ex) {
                msg(strWritingFileTitle, strWritingFileMsg);
            }
            msg(strAllDeletedTitle, strAllDeletedMsg);
        } else {
            msg(strNoDeleteTitle, strNoDeleteMsg);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void setTextFieldFocus(JTextField field) {
        if (field.getText().length() == 1) {
            String text = field.getText();
            field.setText(0 + text);
        }
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField1.requestFocusInWindow();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        setTextFieldFocus(jTextField2);
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        setTextFieldFocus(jTextField3);
    }//GEN-LAST:event_jTextField3FocusLost

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        setTextFieldFocus(jTextField4);
    }//GEN-LAST:event_jTextField4FocusLost

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        setTextFieldFocus(jTextField5);
    }//GEN-LAST:event_jTextField5FocusLost

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost
        setTextFieldFocus(jTextField6);
    }//GEN-LAST:event_jTextField6FocusLost

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
        setTextFieldFocus(jTextField7);
    }//GEN-LAST:event_jTextField7FocusLost

    private void jTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusLost
        setTextFieldFocus(jTextField8);
    }//GEN-LAST:event_jTextField8FocusLost

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost
        setTextFieldFocus(jTextField9);
    }//GEN-LAST:event_jTextField9FocusLost

    private void jTextField10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusLost
        setTextFieldFocus(jTextField10);
    }//GEN-LAST:event_jTextField10FocusLost

    private void jTextField11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField11FocusLost
        setTextFieldFocus(jTextField11);
    }//GEN-LAST:event_jTextField11FocusLost

    private void jTextField12FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField12FocusLost
        setTextFieldFocus(jTextField12);
    }//GEN-LAST:event_jTextField12FocusLost

    private void jTextField13FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField13FocusLost
        setTextFieldFocus(jTextField13);
    }//GEN-LAST:event_jTextField13FocusLost

    private void jTextField14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusLost
        setTextFieldFocus(jTextField14);
    }//GEN-LAST:event_jTextField14FocusLost

    private void jTextField15FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField15FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15FocusLost

    private void saveButtonManuallyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonManuallyActionPerformed
        String lang;
        if (!path.contains(".da") && !path.contains(".en")) {
            lang = ".en";
            if (strDeleteTitle.equalsIgnoreCase("slet")) {
                lang = ".da";
            }
            path += lang;
        }
        File file = new File(path);

        if (savePlates.isEmpty()) {
            msg(strNoPlatesTitle, strNoPlatesMsg);
            return;
        } else {
            if (file.exists() && file.canRead()) {
                UserData.saveData(path, savePlates);
            } else {
                fileChooserSave();
                file = new File(path);
                if (file.exists() && file.canRead() && file.canWrite()) {
                    UserData.saveData(path, savePlates);
                }
            }
        }
    }//GEN-LAST:event_saveButtonManuallyActionPerformed

    public void setLanguage(String lang) {
        this.language = lang;
        loadLanguage();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new enterManually().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addInfo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private java.awt.Label label1;
    private javax.swing.JLabel numberOfPlates;
    private javax.swing.JButton saveButtonManually;
    // End of variables declaration//GEN-END:variables
}