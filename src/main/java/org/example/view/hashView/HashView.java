package org.example.view.hashView;

import org.example.controler.hashController.HashController;
import org.example.model.hashModel.Hash;
import org.example.view.custom.FontCustom;

import javax.swing.*;
import java.awt.*;

public class HashView extends JPanel {
    private Hash model;
    private HashController controller;
    private JTextField tfInputString,tfOutputString,tfInputFile,tfOutputFile;
    private JButton  btEncryptString,btDecryptString,btEncryptFile,btDecryptFile,btChooseFile;

    public HashView(JFrame frame,String algorithm) {
        model = new Hash();
        model.setAlgorithm(algorithm);
        controller = new HashController(this, model,frame);
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Algorithm "+model.getAlgorithm(), JLabel.CENTER);
        title.setFont(new FontCustom().titleFont);
        title.setPreferredSize(new Dimension(100,50));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);

// chuoi
        JPanel pnSting = new JPanel();
        pnSting.setLayout(new BorderLayout());
        JLabel lbString = new JLabel("Encrypt with string");
        lbString.setFont(new FontCustom().titleFont2);
        lbString.setHorizontalAlignment(SwingConstants.CENTER);
        pnSting.add(lbString, BorderLayout.NORTH);

        JLabel lbInputString = new JLabel("String:", SwingConstants.RIGHT);
        JLabel lbOutputString = new JLabel("Result:", SwingConstants.RIGHT);
        tfInputString = new JTextField(10);
        tfOutputString = new JTextField(10);
        tfOutputString.setEditable(false);
        btEncryptString = new JButton("Hash");

        JPanel pnContextString = new JPanel();
        pnContextString.setLayout(new BoxLayout(pnContextString, BoxLayout.Y_AXIS));

        JPanel pnEn = new JPanel(new GridLayout(1, 2,10,10));
        pnEn.add(lbInputString);
        pnEn.add(tfInputString);

        JPanel pnDe = new JPanel(new GridLayout(1, 2,10,10));
        pnDe.add(lbOutputString);
        pnDe.add(tfOutputString);

        JPanel pnEnDe = new JPanel(new GridLayout(2, 1,10,10));
        pnEnDe.add(pnEn);
        pnEnDe.add(pnDe);

        JPanel pnStringBt = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnStringBt.add(btEncryptString);


        pnContextString.add(pnEnDe);
        pnContextString.add(pnStringBt);
        pnSting.add(pnContextString);

// file
        JPanel pnFile = new JPanel();
        pnFile.setLayout(new BorderLayout());
        JLabel lbFile = new JLabel("Encrypt with file");
        lbFile.setFont(new FontCustom().titleFont2);
        lbFile.setHorizontalAlignment(SwingConstants.CENTER);
        pnFile.add(lbFile, BorderLayout.NORTH);

        JLabel lbInputFile = new JLabel("File:", SwingConstants.RIGHT);
        JLabel lbOutputFile = new JLabel("Result:", SwingConstants.RIGHT);
        tfInputFile = new JTextField(10);

        tfOutputFile = new JTextField(10);

        JPanel pnInputFile = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Cột đầu tiên
        gbc.gridy = 0; // Hàng đầu tiên
        gbc.weightx = 0.7; // 70% không gian theo chiều ngang
        gbc.weighty = 1.0; // 100% không gian theo chiều dọc
        gbc.fill = GridBagConstraints.BOTH;
        pnInputFile.add(tfInputFile, gbc);
        btChooseFile = new JButton("Choose file");
        btChooseFile.setActionCommand("ChooseFileIn");
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx=0.3;
        gbc.weighty=1.0;
        gbc.fill = GridBagConstraints.CENTER;
        pnInputFile.add(btChooseFile, gbc);

        JPanel pnOutputFile = new JPanel(new GridBagLayout());
        gbc.gridx = 0; // Cột đầu tiên
        gbc.gridy = 0; // Hàng đầu tiên
        gbc.weightx = 0.7; // 70% không gian theo chiều ngang
        gbc.weighty = 1.0; // 100% không gian theo chiều dọc
        gbc.fill = GridBagConstraints.BOTH;
        pnOutputFile.add(tfOutputFile, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx=0.3;
        gbc.weighty=1.0;
        gbc.fill = GridBagConstraints.CENTER;
        pnOutputFile.add(Box.createRigidArea(new Dimension(100,0)),gbc);


        btEncryptFile = new JButton("Hash File");

        JPanel pnContextFile = new JPanel();
        pnContextFile.setLayout(new BoxLayout(pnContextFile, BoxLayout.Y_AXIS));

        JPanel pnEnFile = new JPanel(new GridLayout(1, 2,10,10));
        pnEnFile.add(lbInputFile);
        pnEnFile.add(pnInputFile);

        JPanel pnDeFile = new JPanel(new GridLayout(1, 2,10,10));
        pnDeFile.add(lbOutputFile);
        pnDeFile.add(pnOutputFile);

        JPanel pnEnDeFile = new JPanel(new GridLayout(2, 1,10,10));
        pnEnDeFile.add(pnEnFile);
        pnEnDeFile.add(pnDeFile);

        JPanel pnFileBt = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnFileBt.add(btEncryptFile);


        pnContextFile.add(pnEnDeFile);
        pnContextFile.add(pnFileBt);
        pnFile.add(pnContextFile);

//        action
        btEncryptString.addActionListener(controller);
        btEncryptFile.addActionListener(controller);
        btChooseFile.addActionListener(controller);


//        context panel
        JPanel pnContext = new JPanel();
        pnContext.setLayout(new BoxLayout(pnContext, BoxLayout.Y_AXIS));
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnSting);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnFile);
        this.add(pnContext, BorderLayout.CENTER);
    }

    public Hash getModel() {
        return model;
    }

    public void setModel(Hash model) {
        this.model = model;
    }

    public HashController getController() {
        return controller;
    }

    public void setController(HashController controller) {
        this.controller = controller;
    }

    public JTextField getTfInputString() {
        return tfInputString;
    }

    public void setTfInputString(JTextField tfInputString) {
        this.tfInputString = tfInputString;
    }

    public JTextField getTfOutputString() {
        return tfOutputString;
    }

    public void setTfOutputString(JTextField tfOutputString) {
        this.tfOutputString = tfOutputString;
    }

    public JTextField getTfInputFile() {
        return tfInputFile;
    }

    public void setTfInputFile(JTextField tfInputFile) {
        this.tfInputFile = tfInputFile;
    }

    public JTextField getTfOutputFile() {
        return tfOutputFile;
    }

    public void setTfOutputFile(JTextField tfOutputFile) {
        this.tfOutputFile = tfOutputFile;
    }

    public JButton getBtEncryptString() {
        return btEncryptString;
    }

    public void setBtEncryptString(JButton btEncryptString) {
        this.btEncryptString = btEncryptString;
    }

    public JButton getBtDecryptString() {
        return btDecryptString;
    }

    public void setBtDecryptString(JButton btDecryptString) {
        this.btDecryptString = btDecryptString;
    }

    public JButton getBtEncryptFile() {
        return btEncryptFile;
    }

    public void setBtEncryptFile(JButton btEncryptFile) {
        this.btEncryptFile = btEncryptFile;
    }

    public JButton getBtDecryptFile() {
        return btDecryptFile;
    }

    public void setBtDecryptFile(JButton btDecryptFile) {
        this.btDecryptFile = btDecryptFile;
    }

    public JButton getBtChooseFile() {
        return btChooseFile;
    }

    public void setBtChooseFile(JButton btChooseFile) {
        this.btChooseFile = btChooseFile;
    }
}
