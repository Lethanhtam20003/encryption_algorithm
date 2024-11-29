package org.example.controler.asymmetricEncryptionController;

import org.example.model.asymmetricEncryptionModel.RSA;
import org.example.view.FileManager;
import org.example.view.custom.DialogNotification;
import org.example.view.symmetricEncryptionView.Panel_RSA;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.*;
import java.util.Base64;

public class RSAController implements ActionListener {
    private JFrame frame;
    private Panel_RSA view;
    private RSA model;

    public RSAController(Panel_RSA panel, RSA model, JFrame frame) {
        this.frame = frame;
        this.view = panel;
        this.model = model;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String chucNang = e.getActionCommand();
        System.out.println(chucNang);
        switch (chucNang) {
            case "Change Key Size":
                this.eventChangkeySize();
                break;
            case "Gen Public Key":
                this.eventGenPublicKey();
                break;
            case "Gen Private Key":
                 this.eventGenPrivateKey();
                 break;
             
            case "Load Public Key":
                this.eventLoadPublicKey();
                break;
            case "Load Private Key":
                this.eventLoadPrivateKey();
                break;
            case "Encrypt String":
                this.eventEncryptStr();
                break;
            case "Decrypt String":
                this.eventDecryptStr();
                break;
        }
    }

    private void eventDecryptStr() {
        if (model.getPublicKey() == null ) {
//                    thong bao
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("empty public key");
            dialog.setVisible(true);
        } else  if (model.getPrivateKey() == null ) {
//                    thong bao
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("empty private key");
            dialog.setVisible(true);
        }else if (view.getTfInputString().getText().trim().isEmpty()) {
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("input String is empty! please add text!");
            dialog.setVisible(true);
        } else {
            String input = view.getTfInputString().getText().trim();

            try {
                byte[] ciphertext = model.decryptBase64(input);
                String output = model.decrypt(ciphertext);
                view.getTfOutputString().setText(output);
            } catch (NoSuchPaddingException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalBlockSizeException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            } catch (BadPaddingException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeyException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void eventEncryptStr() {
        if (model.getPublicKey() == null ) {
//                    thong bao
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("empty public key");
            dialog.setVisible(true);
        } else  if (model.getPrivateKey() == null ) {
//                    thong bao
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("empty private key");
            dialog.setVisible(true);
        }else if (view.getTfInputString().getText().trim().isEmpty()) {
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("input String is empty! please add text!");
            dialog.setVisible(true);
        } else {
            String input = view.getTfInputString().getText().trim();

            try {
                String output = model.encryptBase64(input);
                view.getTfOutputString().setText(output);

            } catch (NoSuchPaddingException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalBlockSizeException ex) {
                throw new RuntimeException(ex);
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            } catch (BadPaddingException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeyException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void eventLoadPrivateKey() {
        String load = view.getTfLoadPrivateKey().getText().trim();
        String gen = view.getTfGenPrivateKey().getText().trim();
//                 input load key empty
        if(gen.isEmpty() && load.isEmpty()){
//                    notification
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("private Key is empty.\n please gen key!");
            dialog.setVisible(true);
//                 add String key from user
        }else if(!load.isEmpty()){
//                  ep kieu string sang secrect key
            if(model.loadPrivateKey(load )){
                view.getLbPrivateKeyInfoShow().setText(model.keyToString(model.getPrivateKey()));
            }else {
                DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
                dialog.setMessage("private Key is invalid");
                dialog.setVisible(true);
            }
//              add key from this tool
        }else {
            if (model.loadPrivateKey(gen))
                view.getLbPrivateKeyInfoShow().setText(model.keyToString(model.getPrivateKey()));
        }
    }

    private void eventLoadPublicKey() {
        String load = view.getTfLoadPublicKey().getText().trim();
        String gen = view.getTfGenPublicKey().getText().trim();
//                 input load key empty
        if(gen.isEmpty() && load.isEmpty()){
//                    notification
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("private Key is empty.\n please gen key!");
            dialog.setVisible(true);
//                 add String key from user
        }else if(!load.isEmpty()){
//                  ep kieu string sang secrect key
            if(model.loadPublicKey(load)){
                view.getLbPublicKeyInfoShow().setText(model.keyToString(model.getPublicKey()));
            }else {
                DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
                dialog.setMessage("public Key is invalid");
                dialog.setVisible(true);
            }
//              add key from this tool
        }else {
            if (model.loadPublicKey(gen))
                view.getLbPublicKeyInfoShow().setText(model.keyToString(model.getPublicKey()));
        }
    }

    private void eventGenPrivateKey() {
        PrivateKey key = model.genPrivateKey();
        String keyStr = Base64.getEncoder().encodeToString(key.getEncoded());
        view.getTfGenPrivateKey().setText(keyStr);
    }

    private void eventGenPublicKey() {
        PublicKey key = model.genPublicKey();
        String keyStr = Base64.getEncoder().encodeToString(key.getEncoded());
        view.getTfGenPublicKey().setText(keyStr);
    }

    private void eventChangkeySize() {
        Dialog dialog = new Dialog(frame, "Change Key Sise", false);
        dialog.setLayout(new BorderLayout(5, 5));
        dialog.setSize(new Dimension(300, 300));

        dialog.add(new JLabel("Choose key size:", SwingUtilities.CENTER), BorderLayout.NORTH);
        JPanel panelCenter = new JPanel();
        dialog.add(panelCenter, BorderLayout.CENTER);
        for (int i = 0; i <= model.getListKeySize().length - 1; i++) {
            JButton button = new JButton(model.getListKeySize()[i] + "");
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.setKeySize(model.getListKeySize()[finalI]);
                    view.getTfKeySize().setText(button.getText());
                    dialog.dispose();
                }
            });

            panelCenter.add(button);
        }
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
