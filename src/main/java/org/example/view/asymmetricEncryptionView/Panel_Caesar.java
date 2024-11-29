package org.example.view.asymmetricEncryptionView;

import org.example.controler.symmetricEncryptionController.CaesarController;
import org.example.model.classicalEncryption.Caesar;
import org.example.view.custom.FontCustom;

import javax.swing.*;
import java.awt.*;

public class Panel_Caesar extends JPanel {
    private Caesar model;
    private CaesarController controller;

    private JLabel lbKeyInfoShow;
    private JTextField tfGenKey,tfLoadKey,tfInputString,tfOutputString;
    private JButton btGenKey,btnLoadKey,btEncryptString,btDecryptString;

    public Panel_Caesar(JFrame frame) {
        model = new Caesar();
        controller = new CaesarController(this,model,frame);

        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Algorithm Caesar", JLabel.CENTER);
        title.setFont(new FontCustom().titleFont);
        title.setPreferredSize(new Dimension(100,50));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);

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

    public Caesar getModel() {
        return model;
    }

    public void setModel(Caesar model) {
        this.model = model;
    }

    public CaesarController getController() {
        return controller;
    }

    public void setController(CaesarController controller) {
        this.controller = controller;
    }

    public JLabel getLbKeyInfoShow() {
        return lbKeyInfoShow;
    }

    public void setLbKeyInfoShow(JLabel lbKeyInfoShow) {
        this.lbKeyInfoShow = lbKeyInfoShow;
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
