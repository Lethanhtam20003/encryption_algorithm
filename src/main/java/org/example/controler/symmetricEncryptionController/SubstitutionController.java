package org.example.controler.symmetricEncryptionController;

import org.example.model.classicalEncryption.Caesar;
import org.example.model.classicalEncryption.Substitution;
import org.example.view.asymmetricEncryptionView.Panel_Caesar;
import org.example.view.asymmetricEncryptionView.Panel_Substitution;
import org.example.view.custom.DialogNotification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubstitutionController implements ActionListener {
    private Substitution model;
    private Panel_Substitution view;
    private JFrame frame;
    public SubstitutionController(Panel_Substitution view, Substitution model, JFrame frame) {
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
        String key = model.genKey();
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

            if(!model.loadKey(load)){
                DialogNotification dialog = new DialogNotification(frame, "Error!", true);
                dialog.setMessage("Key is not valid.\n please add key!");
                dialog.setVisible(true);
            }
            view.getLbKeyInfoShow().setText(model.getKey());
//              add key from this tool
        }else {
            model.loadKey(gen);
            view.getLbKeyInfoShow().setText(model.getKey());
        }
    }
    private void eventEncryptStr() {
        String in = view.getTfInputString().getText().trim();
        String out = model.encrypt(in);
        view.getTfOutputString().setText(out);
    }

    private void eventDecryptStr() {
        String in = view.getTfInputString().getText().trim();
        String out = model.decrypt(in);
        view.getTfOutputString().setText(out);
    }


}
