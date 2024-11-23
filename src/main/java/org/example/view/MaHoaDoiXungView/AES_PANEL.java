package org.example.view.MaHoaDoiXungView;

import org.example.controler.MaHoaDoiXungController.AESController;
import org.example.model.MaHoaDoiXungModel.AES;
import org.example.view.custom.FontCustom;

import javax.swing.*;
import java.awt.*;

public class AES_PANEL extends JPanel {
    private JLabel lbKeySizeShow,lbKeyInfoShow;
    private JTextField tfKeySize,tfGenKey,tfLoadKey,tfInputString,tfOutputString,tfInputFile,tfOutputFile;
    private JButton btGenKey,btnLoadKey,btEncryptString,btDecryptString,btEncryptFile,btDecryptFile,btKeySize,btChooseFile,btResultFile;
    private AES model;
    private AESController controller;
    public AES_PANEL(Frame frame) {
        model = new AES();
        controller = new AESController(this, model,frame);
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Algorithm AES", JLabel.CENTER);
        title.setFont(new FontCustom().titleFont);
        title.setPreferredSize(new Dimension(100,50));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);
// key size
        JPanel pnKeySize = new JPanel(new GridLayout(1,2,10,10));
        pnKeySize.add(new JLabel("Key Size:",SwingConstants.RIGHT));
        tfKeySize = new JTextField(20);
        tfKeySize.setEditable(false);
        tfKeySize.setText(String.valueOf(model.listKeySize[0]));
        btKeySize = new JButton("change Key Size");

        JPanel pn1 = new JPanel(new GridLayout(1,2,10,10));
        pn1.add(tfKeySize);
        pn1.add(btKeySize);
        pnKeySize.add(pn1);
        pnKeySize.setPreferredSize(new Dimension(0,40));
// gen key
        JPanel pnGenKey = new JPanel(new GridLayout(1, 2,10,10));
        tfGenKey = new JTextField(10);
        tfGenKey.setEditable(false);
        btGenKey = new JButton("GenKey");
        JPanel pn2 = new JPanel(new GridLayout(1,2,10,10));
        pn2.add(tfGenKey);
        pn2.add(btGenKey);
        pnGenKey.add(new JLabel("GEN KEY:", SwingConstants.RIGHT));
        pnGenKey.add(pn2);
        pnGenKey.setPreferredSize(new Dimension(0, 40));

// load key
        JPanel pnLoadKey = new JPanel();
        pnLoadKey.setLayout(new GridLayout(1, 2,10,10));
        tfLoadKey = new JTextField(10);
        btnLoadKey = new JButton("LoadKey");
        JPanel pn3 = new JPanel(new GridLayout(1,2,10,10));
        pn3.add(tfLoadKey);
        pn3.add(btnLoadKey);
        pnLoadKey.add(new JLabel("LOAD KEY:", SwingConstants.RIGHT));
        pnLoadKey.add(pn3);
        pnLoadKey.setPreferredSize(new Dimension(0, 40));
// key information
        JLabel lbkeyInfo = new JLabel("Key actived:", SwingConstants.RIGHT);
        lbKeyInfoShow = new JLabel("...", SwingConstants.LEFT);

        lbkeyInfo.setFont(new FontCustom().titleFont3);
        lbKeyInfoShow.setFont(new FontCustom().titleFont3);

        JPanel pnKeyInfo = new JPanel(new GridLayout(1,4,10,10));
        pnKeyInfo.add(lbkeyInfo);
        pnKeyInfo.add(lbKeyInfoShow);

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
        btDecryptString = new JButton("Decrypt String");
        btEncryptString = new JButton("Encrypt String");

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
        pnStringBt.add(btDecryptString);


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
        btResultFile = new JButton("Choose file");
        btResultFile.setActionCommand("ChooseFileOut");
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
        pnOutputFile.add(btResultFile,gbc);


        btEncryptFile = new JButton("Encrypt file");
        btDecryptFile = new JButton("Decrypt file");

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
        pnFileBt.add(btDecryptFile);


        pnContextFile.add(pnEnDeFile);
        pnContextFile.add(pnFileBt);
        pnFile.add(pnContextFile);

//        action
        btKeySize.addActionListener(controller);
        btGenKey.addActionListener(controller);
        btnLoadKey.addActionListener(controller);
        btEncryptString.addActionListener(controller);
        btDecryptString.addActionListener(controller);
        btEncryptFile.addActionListener(controller);
        btDecryptFile.addActionListener(controller);
        btChooseFile.addActionListener(controller);
        btResultFile.addActionListener(controller);


//        context panel
        JPanel pnContext = new JPanel();
        pnContext.setLayout(new BoxLayout(pnContext, BoxLayout.Y_AXIS));
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnKeySize);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnGenKey);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnLoadKey);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnKeyInfo);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnSting);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnFile);
        this.add(pnContext, BorderLayout.CENTER);
    }

    public JLabel getLbKeySizeShow() {
        return lbKeySizeShow;
    }

    public void setLbKeySizeShow(JLabel lbKeySizeShow) {
        this.lbKeySizeShow = lbKeySizeShow;
    }

    public JLabel getLbKeyInfoShow() {
        return lbKeyInfoShow;
    }

    public void setLbKeyInfoShow(JLabel lbKeyInfoShow) {
        this.lbKeyInfoShow = lbKeyInfoShow;
    }

    public JTextField getTfKeySize() {
        return tfKeySize;
    }

    public void setTfKeySize(JTextField tfKeySize) {
        this.tfKeySize = tfKeySize;
    }

    public JTextField getTfGenKey() {
        return tfGenKey;
    }

    public void setTfGenKey(JTextField tfGenKey) {
        this.tfGenKey = tfGenKey;
    }

    public JTextField getTfLoadKey() {
        return tfLoadKey;
    }

    public void setTfLoadKey(JTextField tfLoadKey) {
        this.tfLoadKey = tfLoadKey;
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

    public JButton getBtGenKey() {
        return btGenKey;
    }

    public void setBtGenKey(JButton btGenKey) {
        this.btGenKey = btGenKey;
    }

    public JButton getBtnLoadKey() {
        return btnLoadKey;
    }

    public void setBtnLoadKey(JButton btnLoadKey) {
        this.btnLoadKey = btnLoadKey;
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

    public JButton getBtKeySize() {
        return btKeySize;
    }

    public void setBtKeySize(JButton btKeySize) {
        this.btKeySize = btKeySize;
    }

    public JButton getBtChooseFile() {
        return btChooseFile;
    }

    public void setBtChooseFile(JButton btChooseFile) {
        this.btChooseFile = btChooseFile;
    }

    public JButton getBtResultFile() {
        return btResultFile;
    }

    public void setBtResultFile(JButton btResultFile) {
        this.btResultFile = btResultFile;
    }

    public AES getModel() {
        return model;
    }

    public void setModel(AES model) {
        this.model = model;
    }

    public AESController getController() {
        return controller;
    }

    public void setController(AESController controller) {
        this.controller = controller;
    }
}
