package org.example.view;

import org.example.model.classicalEncryption.Caesar;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private JButton btCeasar,btAffine,btHill,btSubstitution,btVigenere,btDES,btAES,btBlowfish,btRSA,btMD5,btSHA,btKeyDigital,btFileChecksum;

    public Menu(Frame frame) {

        this.init();
    }

    private void init() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel lb1 = new JLabel("Mã Hóa Đối Xứng Cỗ Điển");
        JLabel lb2 = new JLabel("Mã Hóa Đối Xứng Hiện Đại");
        JLabel lb3 = new JLabel("Mã Hóa Bất Đối Xứng");
        JLabel lb4 = new JLabel("Mã Hóa HASH");
        JLabel lb5 = new JLabel("Key digital");
        JLabel lb6 = new JLabel("extends");
// 1
        btCeasar = new JButton("Caesar");
        btAffine = new JButton("Affine");
        btHill = new JButton("Hill");
        btSubstitution = new JButton("Substitution");
        btVigenere = new JButton("Vigenere");
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        panel1.add(lb1);
        panel1.add(btCeasar);
        panel1.add(btAffine);
        panel1.add(btHill);
        panel1.add(btSubstitution);
        panel1.add(btVigenere);

        panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

//        2
        btDES = new JButton("DES");
        btAES = new JButton("AES");
        btBlowfish = new JButton("Blowfish");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(lb2);
        panel2.add(btDES);
        panel2.add(btAES);
        panel2.add(btBlowfish);

        panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        3
        btRSA = new JButton("RSA");

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.add(lb3);
        panel3.add(btRSA);

        panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        4
        btMD5 = new JButton("MD5");
        btSHA = new JButton("SHA-1");

        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panel4.add(lb4);
        panel4.add(btMD5);
        panel4.add(btSHA);

        panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        5
        btKeyDigital = new JButton("Key Digital");
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        panel5.add(lb5);
        panel5.add(btKeyDigital);

        panel5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        6
        btFileChecksum = new JButton("file checksum");

        JPanel panel6 = new JPanel();
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
        panel6.add(lb6);
        panel6.add(btFileChecksum);

        panel6.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(panel6);

        JScrollPane jsbTotal = new JScrollPane(panel);

        this.add(jsbTotal);

    }

    public JButton getBtCeasar() {
        return btCeasar;
    }

    public void setBtCeasar(JButton btCeasar) {
        this.btCeasar = btCeasar;
    }

    public JButton getBtAffine() {
        return btAffine;
    }

    public void setBtAffine(JButton btAffine) {
        this.btAffine = btAffine;
    }

    public JButton getBtHill() {
        return btHill;
    }

    public void setBtHill(JButton btHill) {
        this.btHill = btHill;
    }

    public JButton getBtSubstitution() {
        return btSubstitution;
    }

    public void setBtSubstitution(JButton btSubstitution) {
        this.btSubstitution = btSubstitution;
    }

    public JButton getBtVigenere() {
        return btVigenere;
    }

    public void setBtVigenere(JButton btVigenere) {
        this.btVigenere = btVigenere;
    }

    public JButton getBtDES() {
        return btDES;
    }

    public void setBtDES(JButton btDES) {
        this.btDES = btDES;
    }

    public JButton getBtAES() {
        return btAES;
    }

    public void setBtAES(JButton btAES) {
        this.btAES = btAES;
    }

    public JButton getBtBlowfish() {
        return btBlowfish;
    }

    public void setBtBlowfish(JButton btBlowfish) {
        this.btBlowfish = btBlowfish;
    }

    public JButton getBtRSA() {
        return btRSA;
    }

    public void setBtRSA(JButton btRSA) {
        this.btRSA = btRSA;
    }

    public JButton getBtMD5() {
        return btMD5;
    }

    public void setBtMD5(JButton btMD5) {
        this.btMD5 = btMD5;
    }

    public JButton getBtSHA() {
        return btSHA;
    }

    public void setBtSHA(JButton btSHA) {
        this.btSHA = btSHA;
    }

    public JButton getBtFileChecksum() {
        return btFileChecksum;
    }

    public void setBtFileChecksum(JButton btFileChecksum) {
        this.btFileChecksum = btFileChecksum;
    }

}
