package org.example.view;

import org.example.controler.MenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Menu extends JPanel {
    private JButton bt1;
    private JButton btDES,btAES,btBlowfish,btRSA,btMD5,btSHA,btFileChecksum;

    public Menu(Frame frame) {

        this.init();
    }

    private void init() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel lb1 = new JLabel("Mã Hóa Đối Xứng");
        JLabel lb2 = new JLabel("Mã Hóa Bất Đối Xứng");
        JLabel lb3 = new JLabel("Mã Hóa HASH");
        JLabel lb4 = new JLabel("extends");

        btDES = new JButton("DES");
        btAES = new JButton("AES");
        btBlowfish = new JButton("Blowfish");

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(lb1);
        panel1.add(btDES);
        panel1.add(btAES);
        panel1.add(btBlowfish);

        JScrollPane jsb1 = new JScrollPane(panel1);

        btRSA = new JButton("RSA");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(lb2);
        panel2.add(btRSA);

        JScrollPane jsb2 = new JScrollPane(panel2);

        btMD5 = new JButton("MD5");
        btSHA = new JButton("SHA-1");

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.add(lb3);
        panel3.add(btMD5);
        panel3.add(btSHA);

        JScrollPane jsb3 = new JScrollPane(panel3);

        btFileChecksum = new JButton("file checksum");

        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panel4.add(lb4);
        panel4.add(btFileChecksum);

        JScrollPane jsb4 = new JScrollPane(panel4);

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(jsb1);
        panel.add(jsb2);
        panel.add(jsb3);
        panel.add(jsb4);


        this.add(panel);

    }

    public JButton getBt1() {
        return bt1;
    }

    public void setBt1(JButton bt1) {
        this.bt1 = bt1;
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
