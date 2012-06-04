/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BingoGUI.java
 *
 * Created on 14-07-2011, 18:39:57
 */
package Bingo;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author MadsLundt
 */
public class BingoGUI extends javax.swing.JFrame {

    private List<Integer> numbers = new ArrayList<Integer>();
    private List<Bingo> plateList = new ArrayList<Bingo>();
    // Input default text
    private String def = "";
    private String path = "src/Bingo/first";
    private static String OCRtxt;
    private boolean isGameOn = false;
    private boolean loadOCR = false;
    private File file;
    private int inputNumber;
    private int get1Row = 5;
    private int get2Row = 10;
    private int getBingo = 15;
    private int bingoPlate;
    private boolean is1Row = false;
    private boolean is2Row = false;
    private boolean isBingo = false;
    private boolean autoRound = false;
    public List<Bingo> plateListTemp = new ArrayList<Bingo>();
    private String strExitTitle = "Exit game";
    private String strExitMsg = "Game is already started.\nSure you want to quit?";
    private String strNewTitle = "New game";
    private String strNewMsg = "Game is already started.\nSure you want to start a new game?";
    private String strPlate = "Plate: ";
    private String strGenerateRandom = "How many plates do you like to generate?";
    private int countPlates = 0;
    private boolean cancelProgress = false;

    /** Creates new form BingoGUI */
    public BingoGUI() {
        initComponents();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BingoGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BingoGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BingoGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BingoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        autoRoundCheck.setState(false);
        this.loadingFrame.setEnabled(true);
        loadDefault();
        if (strPlate.equalsIgnoreCase("Plate: ")) {
            danishMenu.setEnabled(true);
            englishMenu.setEnabled(false);
        } else {
            englishMenu.setEnabled(true);
            danishMenu.setEnabled(false);
        }

    }

    private void loadDefault() {
        label1Row.setVisible(false);
        label1RowNb.setVisible(false);
        label2Row.setVisible(false);
        label2RowNb.setVisible(false);
        labelBingo.setVisible(false);
        labelBingoNb.setVisible(false);
        labelGot1Row.setVisible(false);
        labelGot1RowNb.setVisible(false);
        labelGot2Row.setVisible(false);
        labelGot2RowNb.setVisible(false);
        labelGotBingo.setVisible(false);
        labelGotBingoNb.setVisible(false);
        jSeparator1.setVisible(false);
        inputField.setEnabled(false);
        inputField.setEditable(false);
        yourNumber.setEditable(false);
        yourNumber.setEnabled(false);
        plateCount.setVisible(false);
        loadingProgress.setMinimum(0);
        loadingProgress.setValue(0);
        loadingProgress.setMaximum(100);
        loadingLabel.setText("");
        cancelProgress = false;
    }

    private boolean confirm(String title, String message) {
        int ask = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return (ask == JOptionPane.YES_OPTION);
    }

    private static void msg(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadPlates() {
        file = new File(path);
        if (!file.exists() || !file.canRead()) {
            msg("Can't load file", "Can't load file.\nCheck that file exists");
            return;
        }
        if (file.getName().contains(".da")) {
            setLanguage("danish");
        } else {
            setLanguage("english");
        }
        plateList = UserData.loadData(path);
        inputField.setEnabled(true);
        inputField.setEditable(true);
        reading();
    }

    private void reading() {
        jSeparator1.setVisible(true);
        int check = -1;
        get1Row = 5;
        get2Row = 10;
        getBingo = 15;
        for (int i = 0; i < plateList.size(); i++) {
            if (!is1Row && plateList.get(i).numbersToRow1(numbers) <= get1Row) {
                get1Row = plateList.get(i).numbersToRow1(numbers);
            }
            if (!is2Row && plateList.get(i).numbersToRow2(numbers) <= get2Row) {
                get2Row = plateList.get(i).numbersToRow2(numbers);
            }
            if (!isBingo && plateList.get(i).numbersToBingo(numbers) <= getBingo) {
                getBingo = plateList.get(i).numbersToBingo(numbers);
            }
            if (!is1Row && plateList.get(i).is1Row(numbers)) {
                is1Row = true;
                labelGot1RowNb.setText(plateList.get(i).getControlNumber() + "");
                labelGot1Row.setVisible(true);
                labelGot1RowNb.setVisible(true);
            }
            if (!is2Row && plateList.get(i).is2Row(numbers)) {
                is2Row = true;
                labelGot2RowNb.setText(plateList.get(i).getControlNumber() + "");
                labelGot2Row.setVisible(true);
                labelGot2RowNb.setVisible(true);
            }
            if (!isBingo && plateList.get(i).isBingo(numbers)) {
                isBingo = true;
                labelGotBingoNb.setText(plateList.get(i).getControlNumber() + "");
                labelGotBingo.setVisible(true);
                labelGotBingoNb.setVisible(true);
                bingoPlate = i + 1;
            }
        }

        label1Row.setVisible(true);
        label1RowNb.setText(get1Row + "");
        label1RowNb.setVisible(true);
        label2Row.setVisible(true);
        label2RowNb.setText(get2Row + "");
        label2RowNb.setVisible(true);
        labelBingo.setVisible(true);
        labelBingoNb.setText(getBingo + "");
        labelBingoNb.setVisible(true);

        if (!is1Row) {
            labelGot1Row.setVisible(false);
            labelGot1RowNb.setVisible(false);
        } else {
            label1RowNb.setText("-");
        }
        if (!is2Row) {
            labelGot2Row.setVisible(false);
            labelGot2RowNb.setVisible(false);
        } else {
            label2RowNb.setText("-");
        }
        if (!isBingo) {
            labelGotBingo.setVisible(false);
            labelGotBingoNb.setVisible(false);
        } else {
            label1RowNb.setText("BINGO");
            label2RowNb.setText("BINGO");
            labelBingoNb.setText("BINGO");
        }

        printAllPlates(plateList);
        //Collections.sort(numbers);
        yourNumber.setText("Numbers: " + numbers.toString().substring(1, numbers.toString().length() - 1));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadingFrame = new javax.swing.JFrame();
        cancelProgressButton = new javax.swing.JButton();
        loadingLabel = new javax.swing.JLabel();
        loadingProgress = new javax.swing.JProgressBar();
        ocrFrame = new javax.swing.JFrame();
        cancelOCRButton = new javax.swing.JButton();
        loadOCRButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bingoDetails = new javax.swing.JTextArea();
        inputField = new javax.swing.JTextField();
        label1Row = new javax.swing.JLabel();
        label2Row = new javax.swing.JLabel();
        labelBingo = new javax.swing.JLabel();
        label1RowNb = new javax.swing.JLabel();
        label2RowNb = new javax.swing.JLabel();
        labelBingoNb = new javax.swing.JLabel();
        labelGot1Row = new javax.swing.JLabel();
        labelGot2Row = new javax.swing.JLabel();
        labelGotBingo = new javax.swing.JLabel();
        createdBy = new javax.swing.JLabel();
        labelGot1RowNb = new javax.swing.JLabel();
        labelGot2RowNb = new javax.swing.JLabel();
        labelGotBingoNb = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        yourNumber = new javax.swing.JTextArea();
        plateCount = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadPlatesMenu = new javax.swing.JMenu();
        fromFileMenu = new javax.swing.JMenuItem();
        manuallyMenu = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        newGameMenu = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitMenu = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        languageMenu = new javax.swing.JMenu();
        danishMenu = new javax.swing.JMenuItem();
        englishMenu = new javax.swing.JMenuItem();
        generateRandomMenu = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        autoRoundCheck = new javax.swing.JCheckBoxMenuItem();

        loadingFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        loadingFrame.setTitle("Loading plates..");
        loadingFrame.setEnabled(false);
        loadingFrame.setFocusCycleRoot(false);
        loadingFrame.setFocusable(false);
        loadingFrame.setMinimumSize(new java.awt.Dimension(280, 130));
        loadingFrame.setResizable(false);
        loadingFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                loadingFrameWindowClosing(evt);
            }
        });

        cancelProgressButton.setText("Cancel");
        cancelProgressButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelProgressButtonActionPerformed(evt);
            }
        });

        loadingLabel.setFont(new java.awt.Font("Lucida Grande", 2, 12));
        loadingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loadingLabel.setLabelFor(loadingProgress);
        loadingLabel.setText("23 out of 56");
        loadingLabel.setEnabled(false);
        loadingLabel.setFocusable(false);
        loadingLabel.setInheritsPopupMenu(false);

        org.jdesktop.layout.GroupLayout loadingFrameLayout = new org.jdesktop.layout.GroupLayout(loadingFrame.getContentPane());
        loadingFrame.getContentPane().setLayout(loadingFrameLayout);
        loadingFrameLayout.setHorizontalGroup(
            loadingFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(loadingFrameLayout.createSequentialGroup()
                .addContainerGap()
                .add(loadingFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(loadingFrameLayout.createSequentialGroup()
                        .add(loadingProgress, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, loadingFrameLayout.createSequentialGroup()
                        .add(cancelProgressButton)
                        .add(96, 96, 96))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, loadingFrameLayout.createSequentialGroup()
                        .add(loadingLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 206, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(40, 40, 40))))
        );
        loadingFrameLayout.setVerticalGroup(
            loadingFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(loadingFrameLayout.createSequentialGroup()
                .addContainerGap()
                .add(loadingLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(loadingProgress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 20, Short.MAX_VALUE)
                .add(cancelProgressButton)
                .addContainerGap())
        );

        ocrFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ocrFrame.setTitle("OCR");
        ocrFrame.setMinimumSize(new java.awt.Dimension(276, 110));
        ocrFrame.setResizable(false);

        cancelOCRButton.setText("Cancel");
        cancelOCRButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelOCRButtonActionPerformed(evt);
            }
        });

        loadOCRButton.setText("Load");
        loadOCRButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadOCRButtonActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Loaded 40 plates out of 521 numbers");

        org.jdesktop.layout.GroupLayout ocrFrameLayout = new org.jdesktop.layout.GroupLayout(ocrFrame.getContentPane());
        ocrFrame.getContentPane().setLayout(ocrFrameLayout);
        ocrFrameLayout.setHorizontalGroup(
            ocrFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(ocrFrameLayout.createSequentialGroup()
                .addContainerGap()
                .add(ocrFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(ocrFrameLayout.createSequentialGroup()
                        .add(cancelOCRButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 75, Short.MAX_VALUE)
                        .add(loadOCRButton)))
                .addContainerGap())
        );
        ocrFrameLayout.setVerticalGroup(
            ocrFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, ocrFrameLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(ocrFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(loadOCRButton)
                    .add(cancelOCRButton))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Bingo Banko");
        setMinimumSize(new java.awt.Dimension(860, 450));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bingoDetails.setColumns(20);
        bingoDetails.setEditable(false);
        bingoDetails.setRows(5);
        bingoDetails.setFocusable(false);
        bingoDetails.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(bingoDetails);

        inputField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputFieldMouseClicked(evt);
            }
        });
        inputField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputFieldFocusLost(evt);
            }
        });
        inputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputFieldKeyPressed(evt);
            }
        });

        label1Row.setText("To get 1 row: ");

        label2Row.setText("To get 2 rows: ");

        labelBingo.setText("To get BINGO: ");

        label1RowNb.setFont(new java.awt.Font("Lucida Grande", 3, 18));
        label1RowNb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1RowNb.setText("14");

        label2RowNb.setFont(new java.awt.Font("Lucida Grande", 3, 18));
        label2RowNb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label2RowNb.setText("14");

        labelBingoNb.setFont(new java.awt.Font("Lucida Grande", 3, 18));
        labelBingoNb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBingoNb.setText("14");

        labelGot1Row.setText("Plate for 1 row: ");

        labelGot2Row.setText("Plate for 2 rows: ");

        labelGotBingo.setText("Plate for BINGO: ");

        createdBy.setFont(new java.awt.Font("Arial", 2, 10));
        createdBy.setText("Made by Mads Engel Lundt");

        labelGot1RowNb.setFont(new java.awt.Font("Lucida Grande", 2, 18));
        labelGot1RowNb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGot1RowNb.setText("56782");

        labelGot2RowNb.setFont(new java.awt.Font("Lucida Grande", 2, 18));
        labelGot2RowNb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGot2RowNb.setText("56782");

        labelGotBingoNb.setFont(new java.awt.Font("Lucida Grande", 2, 18));
        labelGotBingoNb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGotBingoNb.setText("56782");

        yourNumber.setColumns(20);
        yourNumber.setEditable(false);
        yourNumber.setLineWrap(true);
        yourNumber.setRows(5);
        yourNumber.setAutoscrolls(false);
        yourNumber.setBorder(null);
        yourNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        yourNumber.setEnabled(false);
        yourNumber.setFocusable(false);
        yourNumber.setOpaque(false);
        yourNumber.setRequestFocusEnabled(false);
        yourNumber.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(yourNumber);

        plateCount.setFont(new java.awt.Font("Lucida Grande", 2, 11));
        plateCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        plateCount.setText("7 plates");

        fileMenu.setText("File");

        loadPlatesMenu.setText("Load plates");

        fromFileMenu.setText("From file..");
        fromFileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromFileMenuActionPerformed(evt);
            }
        });
        loadPlatesMenu.add(fromFileMenu);

        manuallyMenu.setText("Enter manually");
        manuallyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuallyMenuActionPerformed(evt);
            }
        });
        loadPlatesMenu.add(manuallyMenu);

        jMenuItem1.setText("Text OCR");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        loadPlatesMenu.add(jMenuItem1);

        fileMenu.add(loadPlatesMenu);

        jMenuItem2.setText("Save plates");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem2);

        newGameMenu.setText("New game");
        newGameMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameMenuActionPerformed(evt);
            }
        });
        fileMenu.add(newGameMenu);
        fileMenu.add(jSeparator2);

        exitMenu.setText("Exit");
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenu);

        jMenuBar1.add(fileMenu);

        editMenu.setText("Edit");

        languageMenu.setText("Language");

        danishMenu.setText("Danish");
        danishMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                danishMenuActionPerformed(evt);
            }
        });
        languageMenu.add(danishMenu);

        englishMenu.setText("English");
        englishMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                englishMenuActionPerformed(evt);
            }
        });
        languageMenu.add(englishMenu);

        editMenu.add(languageMenu);

        generateRandomMenu.setText("Generate random plates");
        generateRandomMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateRandomMenuActionPerformed(evt);
            }
        });
        editMenu.add(generateRandomMenu);

        jMenuItem3.setText("Remove duplicates");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem3);

        autoRoundCheck.setSelected(true);
        autoRoundCheck.setText("Auto round");
        autoRoundCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoRoundCheckActionPerformed(evt);
            }
        });
        editMenu.add(autoRoundCheck);

        jMenuBar1.add(editMenu);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 596, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(inputField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 277, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(createdBy))
                        .add(18, 18, 18)
                        .add(jScrollPane2)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(label2Row)
                            .add(label1Row)
                            .add(labelBingo))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, label1RowNb, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, label2RowNb, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .add(labelBingoNb, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(labelGot1Row)
                            .add(labelGot2Row)
                            .add(labelGotBingo))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(labelGotBingoNb, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                            .add(labelGot2RowNb, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(labelGot1RowNb, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(plateCount, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {label1Row, label2Row, labelBingo, labelGot1Row, labelGot2Row, labelGotBingo}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 297, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, label1RowNb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, label1Row))
                        .add(22, 22, 22)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(label2Row)
                            .add(label2RowNb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(27, 27, 27)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(labelBingo)
                            .add(labelBingoNb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(14, 14, 14)
                        .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(labelGot1Row, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelGot1RowNb))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(labelGot2Row, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelGot2RowNb))
                        .add(9, 9, 9)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(labelGotBingo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(labelGotBingoNb))))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(49, 49, 49)
                        .add(plateCount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane2, 0, 0, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(inputField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(createdBy, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        layout.linkSize(new java.awt.Component[] {label1RowNb, label2RowNb, labelBingoNb}, org.jdesktop.layout.GroupLayout.VERTICAL);

        layout.linkSize(new java.awt.Component[] {label1Row, label2Row, labelBingo, labelGot1Row, labelGot2Row, labelGotBingo}, org.jdesktop.layout.GroupLayout.VERTICAL);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputFieldFocusGained
        if (def.isEmpty()) {
            inputField.setText(null);
        } else {
            inputField.setText(def);
        }
    }//GEN-LAST:event_inputFieldFocusGained

    private void inputFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputFieldFocusLost
        if (!inputField.getText().isEmpty()) {
            def = inputField.getText();
        }
        inputField.setText("Enter numbers between 1-90");
    }//GEN-LAST:event_inputFieldFocusLost

    private void fromFileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromFileMenuActionPerformed
        fileChooser();
    }//GEN-LAST:event_fromFileMenuActionPerformed

    private void inputFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputFieldKeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            if (!inputField.getText().isEmpty()) {
                if (containsOnlyNumbers(inputField.getText())) {
                    inputNumber = Integer.parseInt(inputField.getText());
                    inputField.setText(null);
                    if (inputNumber > 0 && inputNumber <= 90 && !numbers.contains(inputNumber)) {
                        numbers.add(inputNumber);
                        isGameOn = true;
                        reading();
                    } else if (autoRound) {
                        int temp = inputNumber;
                        while (true) {
                            inputNumber++;
                            if (inputNumber > 90) {
                                inputNumber = 1;
                            }
                            if (inputNumber > 0 && inputNumber <= 90 && !numbers.contains(inputNumber)) {
                                numbers.add(inputNumber);
                                isGameOn = true;
                                reading();
                                break;
                            }
                        }

                        inputField.setText("Nummeret " + temp + " blev rundet op til " + inputNumber);
                        inputField.selectAll();
                    }
                }
            }
        }
    }//GEN-LAST:event_inputFieldKeyPressed

    private void newGameMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameMenuActionPerformed
        if (isGameOn) {
            if (confirm(strNewTitle, strNewMsg)) {
                isGameOn = false;
                loadPlates();
            }
        } else {
            loadPlates();
        }
    }//GEN-LAST:event_newGameMenuActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        if (isGameOn) {
            if (confirm(strExitTitle, strExitMsg)) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_exitMenuActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (isGameOn) {
            if (confirm(strExitTitle, strExitMsg)) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void danishMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_danishMenuActionPerformed
        setLanguage("danish");
    }//GEN-LAST:event_danishMenuActionPerformed

    private void englishMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_englishMenuActionPerformed
        setLanguage("english");
    }//GEN-LAST:event_englishMenuActionPerformed

    private void setLanguage(String lang) {
        if (lang.equalsIgnoreCase("danish")) {
            label1Row.setText("For at få 1 række:");
            label2Row.setText("For at få 2 rækker:");
            labelBingo.setText("For at få BINGO:");
            labelGot1Row.setText("Plade med 1 række:");
            labelGot2Row.setText("Plade med 2 rækker:");
            labelGotBingo.setText("Plade med BINGO:");
            strExitTitle = "Slut spil";
            strExitMsg = "Et spil er allerede igang.\nEr du sikker på at du vil afslutte spillet?";
            strNewTitle = "Nyt spil";
            strNewMsg = "Et spil er allerede igang.\nEr du sikker på at du vil starte et nyt?";
            createdBy.setText("Lavet af Mads Engel Lundt");
            fileMenu.setText("Filer");
            editMenu.setText("Rediger");
            loadPlatesMenu.setText("Hent plader");
            fromFileMenu.setText("Hent fra fil");
            manuallyMenu.setText("Indtast selv");
            newGameMenu.setText("Nyt spil");
            exitMenu.setText("Luk");
            languageMenu.setText("Sprog");
            danishMenu.setText("Dansk");
            danishMenu.setEnabled(false);
            englishMenu.setText("Engelsk");
            englishMenu.setEnabled(true);
            strPlate = "Plade: ";
            generateRandomMenu.setText("Lav tilfældige plader");
            strGenerateRandom = "Hvor mange tilfældige plader vil du generere?";
            autoRoundCheck.setText("Automatisk rund op");
        } else {
            label1Row.setText("To get 1 row:");
            label2Row.setText("To get 2 rows:");
            labelBingo.setText("To get BINGO:");
            labelGot1Row.setText("Plate for 1 row:");
            labelGot2Row.setText("Plate for 2 rows:");
            labelGotBingo.setText("Plate for BINGO:");
            strExitTitle = "Exit game";
            strExitMsg = "Game is already started.\nSure you want to quit?";
            strNewTitle = "New game";
            strNewMsg = "Game is already started.\nSure you want to start a new game?";
            createdBy.setText("Made by Mads Engel Lundt");
            fileMenu.setText("File");
            editMenu.setText("Edit");
            loadPlatesMenu.setText("Load plates");
            fromFileMenu.setText("From file");
            manuallyMenu.setText("Enter manually");
            newGameMenu.setText("New game");
            exitMenu.setText("Exit");
            languageMenu.setText("Language");
            danishMenu.setText("Danish");
            englishMenu.setEnabled(false);
            englishMenu.setText("English");
            danishMenu.setEnabled(true);
            strPlate = "Plate: ";
            generateRandomMenu.setText("Generate random plates");
            strGenerateRandom = "How many plates do you like to generate?";
            autoRoundCheck.setText("Auto round");
        }
        printAllPlates(plateList);
    }

    private void cancelProgressButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelProgressButtonActionPerformed
        cancelProgress = true;
    }//GEN-LAST:event_cancelProgressButtonActionPerformed

    private void loadingFrameWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_loadingFrameWindowClosing
        cancelProgress = true;
    }//GEN-LAST:event_loadingFrameWindowClosing

    private void manuallyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuallyMenuActionPerformed
        enterManually eM = new enterManually();
        eM.setEnabled(true);
        eM.setVisible(true);
        if (strPlate.equalsIgnoreCase("Plate: ")) {
            eM.setLanguage("english");
        } else {
            eM.setLanguage("danish");
        }
    }//GEN-LAST:event_manuallyMenuActionPerformed

    private void generateRandomMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateRandomMenuActionPerformed

        if (isGameOn) {
            if (confirm(strNewTitle, strNewMsg)) {
                isGameOn = false;
                generateRandomPlates();
                inputField.setEnabled(true);
                inputField.setEditable(true);
            }
        } else {
            generateRandomPlates();
            inputField.setEnabled(true);
            inputField.setEditable(true);
        }
    }//GEN-LAST:event_generateRandomMenuActionPerformed

    private void autoRoundCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoRoundCheckActionPerformed
        if (autoRoundCheck.getState()) {
            autoRound = true;
        } else {
            autoRound = false;
        }
    }//GEN-LAST:event_autoRoundCheckActionPerformed

    private void inputFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputFieldMouseClicked
        inputField.setText(null);
    }//GEN-LAST:event_inputFieldMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // LOAD OCR
        if (isGameOn) {
            if (!confirm(strNewTitle, strNewMsg)) {
                return;
            }
        }
        isGameOn = false;
        JFileChooser fc = new JFileChooser(file);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath();
            plateListTemp.clear();
            plateListTemp = OCR.START(path);
            if (confirm("OCR", OCRtxt + "\nSure you want to load them?")) {
                plateList.clear();
                plateList.addAll(plateListTemp);
                inputField.setEditable(true);
                inputField.setEnabled(true);
                reading();
            } else {
                plateListTemp.clear();
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void setOCR(String txt) {
        OCRtxt = txt;
    }

    private void loadOCRButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadOCRButtonActionPerformed
        loadOCR = true;
        plateList.clear();
        plateList.addAll(plateListTemp);
        System.out.println("\n\nPlateList:");
        for (int i = 0; i < plateList.size(); i++) {
            System.out.println("\t" + plateListTemp.get(i).plateToString());
        }
        this.ocrFrame.setEnabled(false);
        this.ocrFrame.setVisible(false);
        this.ocrFrame.setAlwaysOnTop(false);
        inputField.setEnabled(true);
        inputField.setEditable(true);
        reading();
    }//GEN-LAST:event_loadOCRButtonActionPerformed

    private void cancelOCRButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelOCRButtonActionPerformed
        loadOCR = false;
        plateListTemp.clear();
        this.ocrFrame.setEnabled(false);
        this.ocrFrame.setVisible(false);
        this.ocrFrame.setAlwaysOnTop(false);
        plateList.clear();
    }//GEN-LAST:event_cancelOCRButtonActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (plateList.isEmpty()) {
            msg("Save plates", "No plates to save");
            return;
        }
        JFileChooser fc = new JFileChooser(file);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath();
            file = new File(path);
            if (file.exists()) {
                if (confirm("Save plate", "File "+file.getName()+" already exists.\nDo you want to overwrite?")) {
                    file.setWritable(true);
                    file.delete();
                    try {
                        file.createNewFile();
                    } catch (IOException ex) {
                        msg("Error", "Error 44");
                    }
                } else {            
                    return;
                }
            }
            UserData.saveData(path, plateList);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (plateList.isEmpty()) {
            msg("Remove duplicates", "No plates to edit");
            return;
        }
            int dupes = removeDuplicates();
            if (dupes > 0) {
            msg("Remove duplicates", "Removed "+dupes+" duplicates successfully");
            reading();
            } else {
            msg("Remove duplicates", "No duplicates");
            }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private int removeDuplicates() {
        int dupes = 0;
        for (int i = 0; i < (plateList.size()-1); i++) {
            for (int j = (i+1); j < plateList.size(); j++) {
                if (plateList.get(i).getControlNumber().equalsIgnoreCase(plateList.get(j).getControlNumber())) {
                    dupes++;
                    plateList.remove(j);
                    i--;
                    break;
                }
            }
        }
        return dupes;
    }
    
    private void generateRandomPlates() {
        String ans;
        ans = JOptionPane.showInputDialog(null, strGenerateRandom);
        inputField.setEditable(true);
        inputField.setEnabled(true);
        try {
            int a = Integer.parseInt(ans);
            generateRandom(a);
        } catch (NumberFormatException e) {
            // ERROR
        }
    }

    private void generateRandom(int max) {
        if (max > 100000) {
            max = 100000;
        } else if (max < 1) {
            max = 0;
        }
        Bingo bin = new Bingo();
        plateList.clear();
        setLoad(true);
        for (int i = 0; i < max; i++) {
            plateList.add(bin.generateRandom());
            progressInitialize(i, max);
        }
        reading();
        setLoad(false);
    }

    private void fileChooser() {
        if (isGameOn) {
            if (!confirm(strNewTitle, strNewMsg)) {
                return;
            }
        }
        isGameOn = false;
        JFileChooser fc = new JFileChooser(file);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath();
            loadPlates();
        }
    }

    private void printAllPlates(List<Bingo> array) {
        int count = 1;
        bingoDetails.setText("");
        for (int i = 0; i < array.size(); i++) {
            bingoDetails.append(strPlate + count + "\n");
            bingoDetails.append(array.get(i).getNumbers(numbers) + "\n\n");
            count++;
        }
        plateCount.setVisible(true);
        countPlates = count - 1;
        if (strPlate.equalsIgnoreCase("Plate: ")) {
            plateCount.setText(countPlates + " Plates  ");
        } else {
            plateCount.setText(countPlates + " Spilleplader  ");
        }
        if (isBingo) {
            if (strPlate.equalsIgnoreCase("Plate: ")) {
                plateCount.setText("BINGO on plate: " + bingoPlate);
            } else {
                plateCount.setText("BINGO på plade: " + bingoPlate);
            }
        }
    }

    public void progressInitialize(int loadInt, int finishInt) {
        if (loadInt > finishInt || finishInt == 0 || finishInt > 100000 || loadInt > 100000) {
            loadInt = finishInt;
            finishInt++;
        }
        int procent = 100 * loadInt / finishInt;
        this.loadingLabel.setText(loadInt + " plates loaded of " + finishInt);
        this.loadingProgress.setString(loadInt + " plates loaded of " + finishInt + "\t" + procent + " %");
        this.loadingProgress.setValue(procent);
        this.loadingProgress.paintImmediately(0, 0, loadingProgress.getWidth(), loadingProgress.getHeight());
        loadingProgress.repaint();

    }

    public void setLoad(boolean setLoading) {
        this.loadingFrame.setEnabled(setLoading);
        this.loadingFrame.setVisible(setLoading);
        this.loadingFrame.setAlwaysOnTop(setLoading);
        this.loadingFrame.requestFocusInWindow();
        this.loadingLabel.setVisible(setLoading);
        this.cancelProgressButton.setVisible(setLoading);
        this.loadingProgress.setValue(0);
        this.loadingProgress.setStringPainted(setLoading);
        if (!setLoading) {
            this.requestFocusInWindow();
        }
        this.loadingFrame.setLocation(this.getLocation().x + (this.getWidth() / 2) - (loadingFrame.getWidth() / 2), this.getLocation().y + (this.getHeight() / 2) - (loadingFrame.getHeight() / 2));
    }

    public boolean cancelProgress() {
        return cancelProgress;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private boolean containsOnlyNumbers(String str) {

        if (str.contains("-")) {
            str = str.substring(1, str.length());
            System.out.println(str);
            if (containsOnlyNumbers(str)) {
                if (numbers.contains(Integer.parseInt(str))) {
                    numbers.remove(numbers.indexOf(Integer.parseInt(str)));
                    inputField.setText(null);
                    is1Row = false;
                    is2Row = false;
                    isBingo = false;
                    reading();
                    return false;
                }
                return false;
            }
            return false;
        }
        int a;
        try {
            a = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0 || a < 1 || a > 90) {
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
    /**
     * @param args the command line arguments
     */
    // public static void main(String args[]) {
    //   java.awt.EventQueue.invokeLater(new Runnable() {
//
    //          @Override
    //        public void run() {
    //          BingoGUI bingoGUI = new BingoGUI();
    //        bingoGUI.setEnabled(true);
    //      bingoGUI.setVisible(true);
    //new BingoGUI().setVisible(true);
    //}
    //});
    // }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem autoRoundCheck;
    private javax.swing.JTextArea bingoDetails;
    private javax.swing.JButton cancelOCRButton;
    private javax.swing.JButton cancelProgressButton;
    private javax.swing.JLabel createdBy;
    private javax.swing.JMenuItem danishMenu;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem englishMenu;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem fromFileMenu;
    private javax.swing.JMenuItem generateRandomMenu;
    private javax.swing.JTextField inputField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel label1Row;
    private javax.swing.JLabel label1RowNb;
    private javax.swing.JLabel label2Row;
    private javax.swing.JLabel label2RowNb;
    private javax.swing.JLabel labelBingo;
    private javax.swing.JLabel labelBingoNb;
    private javax.swing.JLabel labelGot1Row;
    private javax.swing.JLabel labelGot1RowNb;
    private javax.swing.JLabel labelGot2Row;
    private javax.swing.JLabel labelGot2RowNb;
    private javax.swing.JLabel labelGotBingo;
    private javax.swing.JLabel labelGotBingoNb;
    private javax.swing.JMenu languageMenu;
    private javax.swing.JButton loadOCRButton;
    private javax.swing.JMenu loadPlatesMenu;
    private javax.swing.JFrame loadingFrame;
    private javax.swing.JLabel loadingLabel;
    private javax.swing.JProgressBar loadingProgress;
    private javax.swing.JMenuItem manuallyMenu;
    private javax.swing.JMenuItem newGameMenu;
    private javax.swing.JFrame ocrFrame;
    private javax.swing.JLabel plateCount;
    private javax.swing.JTextArea yourNumber;
    // End of variables declaration//GEN-END:variables
}
