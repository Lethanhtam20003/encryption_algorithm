package org.example.view.asymmetricEncryptionView;

import org.example.controler.symmetricEncryptionController.AffineController;
import org.example.controler.symmetricEncryptionController.CaesarController;
import org.example.model.classicalEncryption.Affine;
import org.example.model.classicalEncryption.Caesar;
import org.example.view.custom.FontCustom;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Panel_Affine extends JPanel {
    private Affine model;
    private AffineController controller;

    private JLabel lbKeyInfoShow;
    private JTextField tfGenKeyA,tfGenKeyB,tfLoadKeyA,tfLoadKeyB,tfInputString,tfOutputString;
    private JButton btGenKey,btnLoadKey,btEncryptString,btDecryptString;

    public Panel_Affine(JFrame frame) {
        model = new Affine();
        controller = new AffineController(this,model,frame);

        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Algorithm Affine", JLabel.CENTER);
        title.setFont(new FontCustom().titleFont);
        title.setPreferredSize(new Dimension(100,50));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);

// gen key
        JPanel pnGenKey = new JPanel(new GridLayout(1, 2,10,10));
        tfGenKeyA = new JTextField(10);
        tfGenKeyA.setEditable(false);
        tfGenKeyA.setBorder(new TitledBorder("number A"));
        tfGenKeyB = new JTextField(10);
        tfGenKeyB.setEditable(false);
        tfGenKeyB.setBorder(new TitledBorder("number B"));
        btGenKey = new JButton("GenKey");
        JPanel pn2 = new JPanel(new GridLayout(1,3,10,10));
        pn2.add(tfGenKeyA);
        pn2.add(tfGenKeyB);
        pn2.add(btGenKey);
        pnGenKey.add(new JLabel("GEN KEY:", SwingConstants.RIGHT));
        pnGenKey.add(pn2);
        pnGenKey.setPreferredSize(new Dimension(0, 40));

// load key
        JPanel pnLoadKey = new JPanel();
        pnLoadKey.setLayout(new GridLayout(1, 2,10,10));
        tfLoadKeyA = new JTextField(10);
        tfGenKeyA.setBorder(new TitledBorder("number A"));
        tfLoadKeyB = new JTextField(10);
        tfLoadKeyB.setBorder(new TitledBorder("number B"));
        btnLoadKey = new JButton("LoadKey");
        JPanel pn3 = new JPanel(new GridLayout(1,2,10,10));
        pn3.add(tfLoadKeyA);
        pn3.add(tfLoadKeyB);
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

//        action
        btGenKey.addActionListener(controller);
        btnLoadKey.addActionListener(controller);
        btEncryptString.addActionListener(controller);
        btDecryptString.addActionListener(controller);

//        context panel
        JPanel pnContext = new JPanel();
        pnContext.setLayout(new BoxLayout(pnContext, BoxLayout.Y_AXIS));
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnGenKey);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnLoadKey);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnKeyInfo);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnSting);
        this.add(pnContext, BorderLayout.CENTER);
    }

    public JLabel getLbKeyInfoShow() {
        return lbKeyInfoShow;
    }

    public void setLbKeyInfoShow(JLabel lbKeyInfoShow) {
        this.lbKeyInfoShow = lbKeyInfoShow;
    }

    public Affine getModel() {
        return model;
    }

    public void setModel(Affine model) {
        this.model = model;
    }

    public AffineController getController() {
        return controller;
    }

    public void setController(AffineController controller) {
        this.controller = controller;
    }

    public JTextField getTfGenKeyA() {
        return tfGenKeyA;
    }

    public void setTfGenKeyA(JTextField tfGenKeyA) {
        this.tfGenKeyA = tfGenKeyA;
    }

    public JTextField getTfGenKeyB() {
        return tfGenKeyB;
    }

    public void setTfGenKeyB(JTextField tfGenKeyB) {
        this.tfGenKeyB = tfGenKeyB;
    }

    public JTextField getTfLoadKeyA() {
        return tfLoadKeyA;
    }

    public void setTfLoadKeyA(JTextField tfLoadKeyA) {
        this.tfLoadKeyA = tfLoadKeyA;
    }

    public JTextField getTfLoadKeyB() {
        return tfLoadKeyB;
    }

    public void setTfLoadKeyB(JTextField tfLoadKeyB) {
        this.tfLoadKeyB = tfLoadKeyB;
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
}
