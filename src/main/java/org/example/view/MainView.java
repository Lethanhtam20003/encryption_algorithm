package org.example.view;

import org.example.view.asymmetricEncryptionView.*;
import org.example.view.digitalSign.PanelDigitalSign;
import org.example.view.hashView.HashView;
import org.example.view.symmetricEncryptionView.Panel_RSA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private JPanel contentPane;
    private Menu menu;
    private PanelContent panelShow;

    public MainView() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximized
        generationView();
//        this.pack();
        this.setVisible(true);
    }
    public void generationView(){
        contentPane = new JPanel();

        contentPane.setLayout(new BorderLayout());
        menu = new Menu(this);
        panelShow = new PanelContent();
        panelShow.addPanelNew(new PANEL_DES(this));

        JPanel pnE = new JPanel();
        pnE.add(Box.createRigidArea(new Dimension(200, 10)));

        contentPane.add(menu, BorderLayout.WEST);
        contentPane.add(panelShow, BorderLayout.CENTER);
        contentPane.add(pnE, BorderLayout.EAST);

        this.setContentPane(contentPane);


        ActionListener menuListener = createActionMenu();
        menu.getBtAffine().addActionListener(menuListener);
        menu.getBtHill().addActionListener(menuListener);
        menu.getBtCeasar().addActionListener(menuListener);
        menu.getBtSubstitution().addActionListener(menuListener);
        menu.getBtVigenere().addActionListener(menuListener);
        menu.getBtAES().addActionListener(menuListener);
        menu.getBtDES().addActionListener(menuListener);
        menu.getBtBlowfish().addActionListener(menuListener);
        menu.getBtRSA().addActionListener(menuListener);
        menu.getBtMD5().addActionListener(menuListener);
        menu.getBtSHA().addActionListener(menuListener);
        menu.getBtSHA_256().addActionListener(menuListener);
        menu.getBtSHA_384().addActionListener(menuListener);
//        menu.getBtFileChecksum().addActionListener(menuListener);
        menu.getBtKeyDigital().addActionListener(menuListener);

    }

    private ActionListener createActionMenu() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
                switch (e.getActionCommand()) {
                    case "Caesar":
                        Panel_Caesar pn_Caesar = new Panel_Caesar(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(pn_Caesar);
                        break;
                    case "Affine":
                        Panel_Affine pn_Affine = new Panel_Affine(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(pn_Affine);
                        break;
                    case "Hill":
                        Panel_Hill panelHill = new Panel_Hill(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(panelHill);
                        break;
                    case "Substitution":
                        Panel_Substitution panelSubstitution = new Panel_Substitution(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(panelSubstitution);
                        break;
                    case "Vigenere":
                        Panel_Vigenere panelVigenere = new Panel_Vigenere(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(panelVigenere);
                        break;
                    case "AES":
                        PANEL_AES panelAes = new PANEL_AES(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(panelAes);

                        break;
                    case "DES":
                        PANEL_DES panelDes = new PANEL_DES(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(panelDes);
                        break;
                    case "Blowfish":
                        Panel_Blowfish panelBlowfish = new Panel_Blowfish(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(panelBlowfish);
                        break;
                    case "RSA":
                        Panel_RSA panelRsa = new Panel_RSA(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(panelRsa);
                        break;
                    case "MD5":
                        HashView hashView = new HashView(MainView.this,"MD5");
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(hashView);
                        break;
                    case "SHA-1":
                        HashView sha = new HashView(MainView.this,"SHA");
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(sha);
                        break;
                    case "SHA-256":
                        HashView sha256 = new HashView(MainView.this,"SHA-256");
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(sha256);
                        break;
                    case "SHA-384":
                        HashView sha384 = new HashView(MainView.this,"SHA-384");
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(sha384);
                        break;
                    case "Key Digital":
                        PanelDigitalSign digitalSign = new PanelDigitalSign(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(digitalSign);
                        break;
                    case "FileChecksum":

                        break;
                    default:

                        break;

                }
                panelShow.changePanel();
            }
        };

    }


}
