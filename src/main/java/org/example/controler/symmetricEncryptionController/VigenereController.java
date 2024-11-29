package org.example.controler.symmetricEncryptionController;

import org.example.model.classicalEncryption.Caesar;
import org.example.model.classicalEncryption.Vigenere;
import org.example.view.asymmetricEncryptionView.Panel_Caesar;
import org.example.view.asymmetricEncryptionView.Panel_Vigenere;
import org.example.view.custom.DialogNotification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VigenereController implements ActionListener {
    private Vigenere model;
    private Panel_Vigenere view;
    private JFrame frame;
    public VigenereController(Panel_Vigenere view, Vigenere model, JFrame frame) {
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
        try {
            int keySize = Integer.parseInt(view.getTfKeySize().getText().trim());
            String key = model.genKey(keySize);
            view.getTfGenKey().setText(key);
        }catch (Exception e) {
            DialogNotification dialogNotification = new DialogNotification(frame,"Notification!",true);
            dialogNotification.setMessage("please enter the key size");
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

            model.loadKey(load);
            view.getLbKeyInfoShow().setText(model.getKey()+"");
//              add key from this tool
        }else {
            model.loadKey(gen);
            view.getLbKeyInfoShow().setText(model.getKey()+"");
        }
    }
    private void eventEncryptStr() {
        String in = view.getTfInputString().getText().trim();
        if(in.length()<model.getKey().length()){
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("The input character must be longer than the key length.");
            dialog.setVisible(true);
        }
        String out = model.encrypt(in);
        view.getTfOutputString().setText(out);
    }

    private void eventDecryptStr() {
        String in = view.getTfInputString().getText().trim();
        if(in.length()<model.getKey().length()){
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("The input character must be longer than the key length.");
            dialog.setVisible(true);
        }
        String out = model.decrypt(in);
        view.getTfOutputString().setText(out);
    }


}
