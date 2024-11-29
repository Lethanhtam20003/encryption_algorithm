package org.example.controler.symmetricEncryptionController;

import org.example.model.classicalEncryption.Caesar;
import org.example.model.classicalEncryption.Hill;
import org.example.view.asymmetricEncryptionView.Panel_Caesar;
import org.example.view.asymmetricEncryptionView.Panel_Hill;
import org.example.view.custom.DialogNotification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HillController implements ActionListener {
    private Hill model;
    private Panel_Hill view;
    private JFrame frame;
    public HillController(Panel_Hill view, Hill model, JFrame frame) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        switch (command) {
            case "GenKey":
                eventGenKey();
                break;
            case "LoadKey":
                eventLoadKey();
                break;
            case "Encrypt String":
                eventEncryptStr();
                break;
            case "Decrypt String":
                eventDecryptStr();
                break;
        }

    }

    private void eventGenKey() {
        if(view.getTfKeySize().getText().isEmpty()){
            DialogNotification dialogNotification = new DialogNotification(frame,"Notification!",true);
            dialogNotification.setMessage("add key size first!");
            dialogNotification.setVisible(true);
            return;
        }
        try {
            int n = Integer.parseInt(view.getTfKeySize().getText());
            int[][] key = model.genKey(n);
            view.getTfGenKey().setText(model.printArr(key));
        }catch (Exception e){
            DialogNotification dialogNotification = new DialogNotification(frame,"Notification!",true);
            dialogNotification.setMessage("Key is invalid!");
            dialogNotification.setVisible(true);
        }
    }

    private void eventLoadKey() {
        String gen = view.getTfGenKey().getText().trim();
        String load = view.getTfLoadKey().getText().trim();

//                 input load key empty
        if(gen.isEmpty() && load.isEmpty()){
//                    notification
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("Key is empty.\n please add key!");
            dialog.setVisible(true);
//                 add String key from user
        }else if(!load.isEmpty()){
            int arrLen = Integer.parseInt(view.getTfKeySize().getText());
            int[][] key = model.createKey(load,arrLen);
            model.loadKey(key);
            view.getLbKeyInfoShow().setText(model.printArr(model.getKey()));
//              add key from this tool
        }else {
            int arrLen = Integer.parseInt(view.getTfKeySize().getText());
            int[][] key = model.createKey(gen,arrLen);
            model.loadKey(key);
            view.getLbKeyInfoShow().setText(model.printArr(model.getKey()));
        }
    }
    private void eventEncryptStr() {
        String in = view.getTfInputString().getText().trim();
        String out = model.encrypt(in,model.getKey());
        view.getTfOutputString().setText(out);
    }

    private void eventDecryptStr() {
        String in = view.getTfInputString().getText().trim();
        String out = model.decrypt(in,model.getKey());
        view.getTfOutputString().setText(out);
    }

}
