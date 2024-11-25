package org.example.view;

import org.example.view.MaHoaDoiXungView.AES_PANEL;
import org.example.view.MaHoaDoiXungView.DES_PANEL;

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
        panelShow.addPanelNew(new DES_PANEL(this));

        JPanel pnE = new JPanel();
        pnE.add(Box.createRigidArea(new Dimension(300, 10)));

        contentPane.add(menu, BorderLayout.WEST);
        contentPane.add(panelShow, BorderLayout.CENTER);
        contentPane.add(pnE, BorderLayout.EAST);

        this.setContentPane(contentPane);


        ActionListener menuListener = createActionMenu();
        menu.getBtAES().addActionListener(menuListener);
        menu.getBtDES().addActionListener(menuListener);
        menu.getBtBlowfish().addActionListener(menuListener);
        menu.getBtRSA().addActionListener(menuListener);
        menu.getBtMD5().addActionListener(menuListener);
        menu.getBtSHA().addActionListener(menuListener);
        menu.getBtFileChecksum().addActionListener(menuListener);

    }

    private ActionListener createActionMenu() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "AES":
                        AES_PANEL p = new AES_PANEL(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(p);

                        break;
                    case "DES":
                        DES_PANEL p2 = new DES_PANEL(MainView.this);
                        panelShow.removePanelContainNow();
                        panelShow.addPanelNew(p2);
                        break;
                    case "Blowfish":

                        break;
                    case "RSA":

                        break;
                    case "MD5":

                        break;
                    case "SHA":

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
