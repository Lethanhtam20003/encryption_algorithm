package org.example.view.symmetricEncryptionView;

import org.example.controler.asymmetricEncryptionController.RSAController;
import org.example.model.asymmetricEncryptionModel.RSA;
import org.example.view.custom.FontCustom;

import javax.swing.*;
import java.awt.*;

public class Panel_RSA extends JPanel {
    private  JLabel lbPublicKeyInfoShow,lbPrivateKeyInfoShow;
    private JTextArea tfGenPublicKey,tfGenPrivateKey,tfLoadPublicKey,tfLoadPrivateKey;
    private JTextField tfKeySize,tfInputString,tfOutputString,tfInputFile,tfOutputFile;
    private JButton btGenPublicKey,btGenPrivateKey,btnLoadPublicKey,btnLoadPrivateKey,btEncryptString,btDecryptString,btEncryptFile,btDecryptFile,btKeySize,btChooseFile,btResultFile;
    private RSA model;
    private RSAController controller;
    public Panel_RSA(JFrame frame)  {
        model = new RSA();
        controller = new RSAController(this, model, frame);
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Algorithm RSA", JLabel.CENTER);
        title.setFont(new FontCustom().titleFont);
        title.setPreferredSize(new Dimension(100,50));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);
// key size
        JPanel pnKeySize = new JPanel(new GridLayout(1,2,10,10));
        pnKeySize.add(new JLabel("Key Size:",SwingConstants.RIGHT));
        tfKeySize = new JTextField(20);
        tfKeySize.setEditable(false);
        tfKeySize.setText(String.valueOf(model.getListKeySize()[0]));
        btKeySize = new JButton("Change Key Size");

        JPanel pn1 = new JPanel(new GridLayout(1,2,10,10));
        pn1.add(tfKeySize);
        pn1.add(btKeySize);
        pnKeySize.add(pn1);
        pnKeySize.setPreferredSize(new Dimension(0,40));
// gen key
        JPanel pnGenKey = new JPanel(new GridLayout(2, 2,10,10));
        tfGenPublicKey = new JTextArea();
        tfGenPublicKey.setLineWrap(true);
        tfGenPublicKey.setWrapStyleWord(true);
        tfGenPublicKey.setEditable(false);
        JScrollPane sp1 = new JScrollPane(tfGenPublicKey);
        btGenPublicKey = new JButton("Gen Public Key");
        tfGenPrivateKey = new JTextArea( );
        tfGenPrivateKey.setLineWrap(true);
        tfGenPrivateKey.setWrapStyleWord(true);
        tfGenPrivateKey.setEditable(false);
        JScrollPane sp2 = new JScrollPane(tfGenPrivateKey);
        btGenPrivateKey = new JButton("Gen Private Key");
        JPanel pn2 = new JPanel(new GridLayout(1,2,10,10));
        pn2.add(sp1);
        pn2.add(btGenPublicKey);
        JPanel pn3 = new JPanel(new GridLayout(1,2,10,10));
        pn3.add(sp2);
        pn3.add(btGenPrivateKey);
        pnGenKey.add(new JLabel("GEN PUBLIC KEY:", SwingConstants.RIGHT));
        pnGenKey.add(pn2);
        pnGenKey.add(new JLabel("GEN PRIVATE KEY:", SwingConstants.RIGHT));
        pnGenKey.add(pn3);
        pnGenKey.setPreferredSize(new Dimension(0, 120));

// load key
        JPanel pnLoadKey = new JPanel();
        pnLoadKey.setLayout(new GridLayout(2, 2,10,10));
        tfLoadPublicKey = new JTextArea();
        tfLoadPublicKey.setLineWrap(true);
        tfLoadPublicKey.setWrapStyleWord(true);
        JScrollPane sp3 = new JScrollPane(tfLoadPublicKey);
        btnLoadPublicKey = new JButton("Load Public Key");
        tfLoadPrivateKey = new JTextArea();
        tfLoadPrivateKey.setLineWrap(true);
        tfLoadPrivateKey.setWrapStyleWord(true);
        JScrollPane sp4 = new JScrollPane(tfLoadPrivateKey);
        btnLoadPrivateKey = new JButton("Load Private Key");
        JPanel pn4 = new JPanel(new GridLayout(1,2,10,10));
        pn4.add(sp3);
        pn4.add(btnLoadPublicKey);

        JPanel pn5 = new JPanel(new GridLayout(1,2,10,10));
        pn5.add(sp4);
        pn5.add(btnLoadPrivateKey);

        pnLoadKey.add(new JLabel("LOAD PUBLIC KEY:", SwingConstants.RIGHT));
        pnLoadKey.add(pn4);
        pnLoadKey.add(new JLabel("LOAD PRIVATE KEY:", SwingConstants.RIGHT));
        pnLoadKey.add(pn5);
        pnLoadKey.setPreferredSize(new Dimension(0, 120));
// key information
        JLabel lbPublicKeyInfo = new JLabel("Public Key actived:", SwingConstants.RIGHT);
        lbPublicKeyInfoShow = new JLabel("...", SwingConstants.LEFT);
        lbPublicKeyInfo.setFont(new FontCustom().titleFont3);
        lbPublicKeyInfoShow.setFont(new FontCustom().titleFont3);

        JLabel lbPrivateKeyInfo = new JLabel("Private Key Actived:", SwingConstants.RIGHT);
        lbPrivateKeyInfoShow = new JLabel("...", SwingConstants.LEFT);
        lbPrivateKeyInfo.setFont(new FontCustom().titleFont3);
        lbPrivateKeyInfoShow.setFont(new FontCustom().titleFont3);
        JPanel pnKeyInfo = new JPanel(new GridLayout(2,2,10,10));
        pnKeyInfo.add(lbPublicKeyInfo);
        pnKeyInfo.add(lbPublicKeyInfoShow);
        pnKeyInfo.add(lbPrivateKeyInfo);
        pnKeyInfo.add(lbPrivateKeyInfoShow);
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



//        action
        btKeySize.addActionListener(controller);
        btGenPublicKey.addActionListener(controller);
        btGenPrivateKey.addActionListener(controller);
        btnLoadPublicKey.addActionListener(controller);
        btnLoadPrivateKey.addActionListener(controller);
        btEncryptString.addActionListener(controller);
        btDecryptString.addActionListener(controller);


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
//        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//
//        pnContext.add(pn4);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnSting);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        this.add(pnContext, BorderLayout.CENTER);
    }

    public JLabel getLbPublicKeyInfoShow() {
        return lbPublicKeyInfoShow;
    }

    public void setLbPublicKeyInfoShow(JLabel lbPublicKeyInfoShow) {
        this.lbPublicKeyInfoShow = lbPublicKeyInfoShow;
    }

    public JLabel getLbPrivateKeyInfoShow() {
        return lbPrivateKeyInfoShow;
    }

    public void setLbPrivateKeyInfoShow(JLabel lbPrivateKeyInfoShow) {
        this.lbPrivateKeyInfoShow = lbPrivateKeyInfoShow;
    }

    public JTextField getTfKeySize() {
        return tfKeySize;
    }

    public void setTfKeySize(JTextField tfKeySize) {
        this.tfKeySize = tfKeySize;
    }

    public JTextArea getTfGenPublicKey() {
        return tfGenPublicKey;
    }

    public void setTfGenPublicKey(JTextArea tfGenPublicKey) {
        this.tfGenPublicKey = tfGenPublicKey;
    }

    public JTextArea getTfGenPrivateKey() {
        return tfGenPrivateKey;
    }

    public void setTfGenPrivateKey(JTextArea tfGenPrivateKey) {
        this.tfGenPrivateKey = tfGenPrivateKey;
    }

    public JTextArea getTfLoadPublicKey() {
        return tfLoadPublicKey;
    }

    public void setTfLoadPublicKey(JTextArea tfLoadPublicKey) {
        this.tfLoadPublicKey = tfLoadPublicKey;
    }

    public JTextArea getTfLoadPrivateKey() {
        return tfLoadPrivateKey;
    }

    public void setTfLoadPrivateKey(JTextArea tfLoadPrivateKey) {
        this.tfLoadPrivateKey = tfLoadPrivateKey;
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

    public JButton getBtGenPublicKey() {
        return btGenPublicKey;
    }

    public void setBtGenPublicKey(JButton btGenPublicKey) {
        this.btGenPublicKey = btGenPublicKey;
    }

    public JButton getBtGenPrivateKey() {
        return btGenPrivateKey;
    }

    public void setBtGenPrivateKey(JButton btGenPrivateKey) {
        this.btGenPrivateKey = btGenPrivateKey;
    }

    public JButton getBtnLoadPublicKey() {
        return btnLoadPublicKey;
    }

    public void setBtnLoadPublicKey(JButton btnLoadPublicKey) {
        this.btnLoadPublicKey = btnLoadPublicKey;
    }

    public JButton getBtnLoadPrivateKey() {
        return btnLoadPrivateKey;
    }

    public void setBtnLoadPrivateKey(JButton btnLoadPrivateKey) {
        this.btnLoadPrivateKey = btnLoadPrivateKey;
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

    public RSA getModel() {
        return model;
    }

    public void setModel(RSA model) {
        this.model = model;
    }

    public RSAController getController() {
        return controller;
    }

    public void setController(RSAController controller) {
        this.controller = controller;
    }
}
