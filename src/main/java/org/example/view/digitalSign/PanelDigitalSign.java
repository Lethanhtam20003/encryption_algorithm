package org.example.view.digitalSign;

import org.example.controler.DigitalSignController;
import org.example.model.keySign.DigitalSign;
import org.example.view.custom.FontCustom;

import javax.swing.*;
import java.awt.*;

public class PanelDigitalSign extends JPanel {
    private JLabel lbPublicKeyInfoShow,lbPrivateKeyInfoShow;
    private JTextArea tfGenPublicKey,tfGenPrivateKey,tfLoadPublicKey,tfLoadPrivateKey,tfInputString,tfOutputString1,tfOutputString2,tfVerifyString,tfInputFile,tfOutputFile1,tfOutputFile2,tfVerifyFile;
    private JButton btGenPublicKey,btGenPrivateKey,btnLoadPublicKey,btnLoadPrivateKey, btHashString, btVerifyString,btHashFile,btVerifyFile,btChooseFile1,btChooseFile2;
    private DigitalSign model;
    private DigitalSignController controller;
    public PanelDigitalSign(JFrame frame)   {
        model = new DigitalSign();
        controller = new DigitalSignController(this, model, frame);
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Digital Sign", JLabel.CENTER);
        title.setFont(new FontCustom().titleFont);
        title.setPreferredSize(new Dimension(100,50));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title, BorderLayout.NORTH);
// gen key
        JPanel pnKey = new JPanel(new GridLayout(3,2,30,10));

        JPanel pnKey_left1 = new JPanel(new BorderLayout());
        JLabel lbPublicKey = new JLabel("Gen Public Key");
        lbPublicKey.setPreferredSize(new Dimension(150,50));
        tfGenPublicKey = new JTextArea();
        tfGenPublicKey.setLineWrap(true);
        tfGenPublicKey.setWrapStyleWord(true);
        JScrollPane spGenPublicKey = new JScrollPane(tfGenPublicKey);
        tfGenPublicKey.setBorder(BorderFactory.createLineBorder(Color.black));
        btGenPublicKey = new JButton("Gen Public Key");
        pnKey_left1.add(lbPublicKey, BorderLayout.WEST);
        pnKey_left1.add(spGenPublicKey, BorderLayout.CENTER);
        pnKey_left1.add(btGenPublicKey, BorderLayout.EAST);

        JPanel pnKey_right1 = new JPanel(new BorderLayout());
        JLabel lbPrivateKey = new JLabel("Gen Private Key:");
        lbPrivateKey.setPreferredSize(new Dimension(150,50));
        tfGenPrivateKey = new JTextArea();
        tfGenPrivateKey.setLineWrap(true);
        tfGenPrivateKey.setWrapStyleWord(true);
        tfGenPrivateKey.setBorder(BorderFactory.createLineBorder(Color.black));
        JScrollPane spGenPrivateKey = new JScrollPane(tfGenPrivateKey);
        btGenPrivateKey = new JButton("Gen Private Key");
        pnKey_right1.add(lbPrivateKey, BorderLayout.WEST);
        pnKey_right1.add(spGenPrivateKey, BorderLayout.CENTER);
        pnKey_right1.add(btGenPrivateKey, BorderLayout.EAST);

        JPanel pnKey_left2 = new JPanel(new BorderLayout());
        JLabel lbPublicKey2 = new JLabel("Load Public Key:");
        lbPublicKey2.setPreferredSize(new Dimension(150,50));
        tfLoadPublicKey = new JTextArea();
        tfLoadPublicKey.setBorder(BorderFactory.createLineBorder(Color.black));
        btnLoadPublicKey = new JButton("Load Public Key");
        pnKey_left2.add(lbPublicKey2, BorderLayout.WEST);
        pnKey_left2.add(tfLoadPublicKey, BorderLayout.CENTER);
        pnKey_left2.add(btnLoadPublicKey, BorderLayout.EAST);

        JPanel pnKey_right2 = new JPanel(new BorderLayout());
        JLabel lbPrivateKey2 = new JLabel("Load Private Key:");
        lbPrivateKey2.setPreferredSize(new Dimension(150,50));
        tfLoadPrivateKey = new JTextArea();
        tfLoadPrivateKey.setBorder(BorderFactory.createLineBorder(Color.black));
        btnLoadPrivateKey = new JButton("Load Private Key");
        pnKey_right2.add(lbPrivateKey2, BorderLayout.WEST);
        pnKey_right2.add(tfLoadPrivateKey, BorderLayout.CENTER);
        pnKey_right2.add(btnLoadPrivateKey, BorderLayout.EAST);

        JPanel pnKey_left3 = new JPanel(new BorderLayout());
        JLabel lbPublicKey3 = new JLabel("Public Key Actived:");
        lbPublicKeyInfoShow = new JLabel("...",SwingConstants.LEFT);
        pnKey_left3.add(lbPublicKey3, BorderLayout.WEST);
        pnKey_left3.add(lbPublicKeyInfoShow, BorderLayout.CENTER);
        JPanel pnKey_right3 = new JPanel(new BorderLayout());
        JLabel lbPrivateKey3 = new JLabel("Private Key Actived:");
        lbPrivateKeyInfoShow = new JLabel("...", SwingConstants.LEFT);
        pnKey_right3.add(lbPrivateKey3, BorderLayout.WEST);
        pnKey_right3.add(lbPrivateKeyInfoShow, BorderLayout.CENTER);

        pnKey.add(pnKey_left1);
        pnKey.add(pnKey_right1);
        pnKey.add(pnKey_left2);
        pnKey.add(pnKey_right2);
        pnKey.add(pnKey_left3);
        pnKey.add(pnKey_right3);
//        hash String
        JPanel pnHash_String =  new JPanel(new GridLayout(4,2,10,20));

        JPanel pnHash_in = new JPanel(new BorderLayout());
        JLabel hashString_in = new JLabel("Message: ",SwingConstants.RIGHT);
        hashString_in.setPreferredSize(new Dimension(100,50));
        tfInputString = new JTextArea();
        tfInputString.setBorder(BorderFactory.createTitledBorder("Hash"));
        pnHash_in.add(hashString_in, BorderLayout.WEST);
        pnHash_in.add(tfInputString, BorderLayout.CENTER);

        JPanel pnHash_out1 = new JPanel(new BorderLayout());
        JLabel hashString_out = new JLabel("Sign: ",SwingConstants.RIGHT);
        hashString_out.setPreferredSize(new Dimension(100,50));
        tfOutputString1 = new JTextArea();
        tfOutputString1.setLineWrap(true);
        tfOutputString1.setWrapStyleWord(true);
        tfOutputString1.setBorder(BorderFactory.createTitledBorder("Hash"));
        tfOutputString1.setEditable(false);
        tfOutputString1.setBorder(BorderFactory.createLineBorder(Color.black));
        pnHash_out1.add(hashString_out, BorderLayout.WEST);
        pnHash_out1.add(tfOutputString1, BorderLayout.CENTER);

        JPanel pnHash_out2 = new JPanel(new BorderLayout());
        JLabel hashString_out2 = new JLabel("Sign: ",SwingConstants.RIGHT);
        hashString_out2.setPreferredSize(new Dimension(100,50));
        tfOutputString2 = new JTextArea();
        tfOutputString2.setLineWrap(true);
        tfOutputString2.setWrapStyleWord(true);
        tfOutputString2.setBorder(BorderFactory.createTitledBorder("verify"));
        pnHash_out2.add(hashString_out2, BorderLayout.WEST);
        pnHash_out2.add(tfOutputString2, BorderLayout.CENTER);

        JPanel pnHash_verify = new JPanel(new BorderLayout());
        JLabel hashString_verify = new JLabel("Message: ",SwingConstants.RIGHT);
        hashString_verify.setPreferredSize(new Dimension(100,50));
        tfVerifyString = new JTextArea();
        tfVerifyString.setBorder(BorderFactory.createTitledBorder("verify"));
        pnHash_verify.add(hashString_verify, BorderLayout.WEST);
        pnHash_verify.add(tfVerifyString, BorderLayout.CENTER);

        btHashString = new JButton("Hash String");
        btVerifyString = new JButton("Verify String");

        pnHash_String.add(pnHash_in);
        pnHash_String.add(pnHash_verify);
        pnHash_String.add(pnHash_out1);
        pnHash_String.add(pnHash_out2);
        pnHash_String.add(btHashString);
        pnHash_String.add(btVerifyString);

//        Hash File
        JPanel pnHash_File = new JPanel(new GridLayout(4,2,10,20));

        JPanel pnHash_Filein = new JPanel(new BorderLayout());
        JLabel hashFile_in = new JLabel("file : ",SwingConstants.RIGHT);
        hashFile_in .setPreferredSize(new Dimension(100,50));
        tfInputFile = new JTextArea();
        tfInputFile.setBorder(BorderFactory.createLineBorder(Color.black));
        btChooseFile1 = new JButton("Choose File");
        btChooseFile1.setActionCommand("Choose File1");
        pnHash_Filein.add(hashFile_in, BorderLayout.WEST);
        pnHash_Filein.add(tfInputFile, BorderLayout.CENTER);
        pnHash_Filein.add(btChooseFile1, BorderLayout.EAST);

        JPanel pnHashFile_out1 = new JPanel(new BorderLayout());
        JLabel hashFile_out = new JLabel("Sign: ",SwingConstants.RIGHT);
        hashFile_out.setPreferredSize(new Dimension(100,50));
        tfOutputFile1 = new JTextArea();
        tfOutputFile1.setBorder(BorderFactory.createLineBorder(Color.black));
        tfOutputFile1.setEditable(false);
        tfOutputFile1.setBorder(BorderFactory.createLineBorder(Color.black));
        pnHashFile_out1.add(hashFile_out, BorderLayout.WEST);
        pnHashFile_out1.add(tfOutputFile1, BorderLayout.CENTER);

        JPanel pnHash_Fileout2 = new JPanel(new BorderLayout());
        JLabel hashFile_out2 = new JLabel("Sign: ",SwingConstants.RIGHT);
        hashFile_out2.setPreferredSize(new Dimension(100,50));
        tfOutputFile2 = new JTextArea();
        tfOutputFile2.setBorder(BorderFactory.createLineBorder(Color.black));
        pnHash_Fileout2.add(hashFile_out2, BorderLayout.WEST);
        pnHash_Fileout2.add(tfOutputFile2, BorderLayout.CENTER);

        JPanel pnHash_verifyFile = new JPanel(new BorderLayout());
        JLabel hashFile_verify = new JLabel("File: ",SwingConstants.RIGHT);
        hashFile_verify.setPreferredSize(new Dimension(100,50));
        tfVerifyFile = new JTextArea();
        tfVerifyFile.setBorder(BorderFactory.createLineBorder(Color.black));
        btChooseFile2 = new JButton("Choose File");
        btChooseFile2.setActionCommand("Choose File2");
        pnHash_verifyFile.add(hashFile_verify, BorderLayout.WEST);
        pnHash_verifyFile.add(tfVerifyFile, BorderLayout.CENTER);
        pnHash_verifyFile.add(btChooseFile2, BorderLayout.EAST);

        btHashFile = new JButton("Hash File");
        btVerifyFile = new JButton("Verify File");

        pnHash_File.add(pnHash_Filein);
        pnHash_File.add(pnHash_verifyFile);
        pnHash_File.add(pnHashFile_out1);
        pnHash_File.add(pnHash_Fileout2);
        pnHash_File.add(btHashFile);
        pnHash_File.add(btVerifyFile);


//        action
        btGenPublicKey.addActionListener(controller);
        btGenPrivateKey.addActionListener(controller);
        btnLoadPublicKey.addActionListener(controller);
        btnLoadPrivateKey.addActionListener(controller);
        btHashString.addActionListener(controller);
        btVerifyString.addActionListener(controller);
        btHashFile.addActionListener(controller);
        btChooseFile1.addActionListener(controller);
        btChooseFile2.addActionListener(controller);
        btVerifyFile.addActionListener(controller);


//        context panel
        JPanel pnContext = new JPanel();
        pnContext.setLayout(new BoxLayout(pnContext, BoxLayout.Y_AXIS));
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        pnContext.add(pnKey);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        JLabel lbHashString = new JLabel("Hash String:",SwingConstants.CENTER);
        lbHashString.setFont(new FontCustom().titleFont2);
        pnContext.add(lbHashString);
        pnContext.add(pnHash_String);
        pnContext.add(Box.createRigidArea(new Dimension(0, 15)));//        margin
        JLabel lbHashFile = new JLabel("Hash File:",SwingConstants.CENTER);
        lbHashFile.setFont(new FontCustom().titleFont2);
        pnContext.add(lbHashFile);
        pnContext.add(pnHash_File);

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

    public JTextArea getTfInputString() {
        return tfInputString;
    }

    public void setTfInputString(JTextArea tfInputString) {
        this.tfInputString = tfInputString;
    }

    public JTextArea getTfOutputString1() {
        return tfOutputString1;
    }

    public void setTfOutputString1(JTextArea tfOutputString1) {
        this.tfOutputString1 = tfOutputString1;
    }

    public JTextArea getTfOutputString2() {
        return tfOutputString2;
    }

    public void setTfOutputString2(JTextArea tfOutputString2) {
        this.tfOutputString2 = tfOutputString2;
    }

    public JTextArea getTfVerifyString() {
        return tfVerifyString;
    }

    public void setTfVerifyString(JTextArea tfVerifyString) {
        this.tfVerifyString = tfVerifyString;
    }

    public JTextArea getTfInputFile() {
        return tfInputFile;
    }

    public void setTfInputFile(JTextArea tfInputFile) {
        this.tfInputFile = tfInputFile;
    }

    public JTextArea getTfOutputFile1() {
        return tfOutputFile1;
    }

    public void setTfOutputFile1(JTextArea tfOutputFile1) {
        this.tfOutputFile1 = tfOutputFile1;
    }

    public JTextArea getTfOutputFile2() {
        return tfOutputFile2;
    }

    public void setTfOutputFile2(JTextArea tfOutputFile2) {
        this.tfOutputFile2 = tfOutputFile2;
    }

    public JTextArea getTfVerifyFile() {
        return tfVerifyFile;
    }

    public void setTfVerifyFile(JTextArea tfVerifyFile) {
        this.tfVerifyFile = tfVerifyFile;
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

    public JButton getBtHashString() {
        return btHashString;
    }

    public void setBtHashString(JButton btHashString) {
        this.btHashString = btHashString;
    }

    public JButton getBtVerifyString() {
        return btVerifyString;
    }

    public void setBtVerifyString(JButton btVerifyString) {
        this.btVerifyString = btVerifyString;
    }

    public JButton getBtHashFile() {
        return btHashFile;
    }

    public void setBtHashFile(JButton btHashFile) {
        this.btHashFile = btHashFile;
    }

    public JButton getBtVerifyFile() {
        return btVerifyFile;
    }

    public void setBtVerifyFile(JButton btVerifyFile) {
        this.btVerifyFile = btVerifyFile;
    }



    public DigitalSign getModel() {
        return model;
    }

    public void setModel(DigitalSign model) {
        this.model = model;
    }

    public DigitalSignController getController() {
        return controller;
    }

    public void setController(DigitalSignController controller) {
        this.controller = controller;
    }
}
