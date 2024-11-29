package org.example.controler.symmetricEncryptionController;

import org.example.model.classicalEncryption.Caesar;
import org.example.view.asymmetricEncryptionView.Panel_Caesar;
import org.example.view.custom.DialogNotification;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarController implements ActionListener {
    private Caesar model;
    private Panel_Caesar view;
    private JFrame frame;
    public CaesarController(Panel_Caesar view, Caesar model, JFrame frame) {
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
        int key = model.genKey();
        view.getTfGenKey().setText(String.valueOf(key));
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
            try {
                Integer.parseInt(load);
            }catch (Exception e){
                DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
                dialog.setMessage("Key is not number.\n please add key!");
                dialog.setVisible(true);
            }
                model.loadKey(Integer.parseInt(load));
                view.getLbKeyInfoShow().setText(model.getKey()+"");
//              add key from this tool
        }else {
            model.loadKey(Integer.parseInt(gen));
            view.getLbKeyInfoShow().setText(model.getKey()+"");
        }
    }
    private void eventEncryptStr() {
        String in = view.getTfInputString().getText().trim();
        String out = model.encryptString(in);
        view.getTfOutputString().setText(out);
    }

    private void eventDecryptStr() {
        String in = view.getTfInputString().getText().trim();
        String out = model.decryptString(in);
        view.getTfOutputString().setText(out);
    }


}
