package org.example.view;

import javax.swing.*;
import java.awt.*;

public class PanelContent extends JPanel {
    private JPanel panel;
    public PanelContent() {
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }

    public void changePanel() {
        this.revalidate();
        this.repaint();
    }

    void addPanelNew(JPanel p) {
        this.panel = p;
        this.add(p, BorderLayout.CENTER);
    }

    void removePanelContainNow() {
        this.removeAll();
    }
}
