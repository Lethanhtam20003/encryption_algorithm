package org.example.controler.symmetricEncryptionController;

import org.example.model.classicalEncryption.Affine;
import org.example.model.classicalEncryption.Caesar;
import org.example.view.asymmetricEncryptionView.Panel_Affine;
import org.example.view.asymmetricEncryptionView.Panel_Caesar;
import org.example.view.custom.DialogNotification;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AffineController implements ActionListener {
    private Affine model;
    private Panel_Affine view;
    private JFrame frame;
    public AffineController(Panel_Affine view, Affine model, JFrame frame) {
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
        int[] key = model.genKey();
        view.getTfGenKeyA().setText(String.valueOf(key[0]));
        view.getTfGenKeyB().setText(String.valueOf(key[1]));
    }

    private void eventLoadKey() {
        String genA = view.getTfGenKeyA().getText().trim();
        String genB = view.getTfGenKeyB().getText().trim();
        String loadA = view.getTfLoadKeyA().getText().trim();
        String loadB = view.getTfLoadKeyB().getText().trim();

//                 input load key empty
        if(genA.isEmpty() && genB.isEmpty() && loadA.isEmpty() && loadB.isEmpty()) {
//                    notification
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("Key is empty.\n please add key!");
            dialog.setVisible(true);
//                 add String key from user
        }else if(!loadA.isEmpty() || !loadB.isEmpty()) {
            try {
                Integer.parseInt(loadA);
                Integer.parseInt(loadB);
            }catch (Exception e){
                DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
                dialog.setMessage("Key is not number.\n please add key!");
                dialog.setVisible(true);
            }
            int[] key = {Integer.parseInt(loadA),Integer.parseInt(loadB)};
            if(!model.loadKey(key)){
                DialogNotification dialog = new DialogNotification(frame, "Key is loaded!", true);
                dialog.setMessage("key is invalid.!");
                dialog.setVisible(true);
            }
            view.getLbKeyInfoShow().setText(model.getKey()[0]+"x +"+model.getKey()[1]);

//              add key from this tool
        }else {
            int[] key = {Integer.parseInt(genA),Integer.parseInt(genB)};
            model.loadKey(key);
            view.getLbKeyInfoShow().setText(model.getKey()[0]+"x +"+model.getKey()[1]);
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
