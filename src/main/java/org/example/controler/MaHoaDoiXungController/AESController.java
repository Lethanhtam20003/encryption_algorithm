package org.example.controler.MaHoaDoiXungController;

import org.example.model.MaHoaDoiXungModel.AES;
import org.example.view.MaHoaDoiXungView.AES_PANEL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AESController implements ActionListener {
    private AES_PANEL view;
    private AES model;
    private Frame frame;
    public AESController(AES_PANEL view, AES model, Frame frame) {
        this.view = view;
        this.model = model;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
