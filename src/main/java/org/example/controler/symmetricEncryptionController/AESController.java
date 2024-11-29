package org.example.controler.symmetricEncryptionController;

import org.example.model.CheckFile;
import org.example.model.KeyManager;
import org.example.model.symmetricEncryptionModel.AES;
import org.example.view.FileManager;
import org.example.view.asymmetricEncryptionView.PANEL_AES;
import org.example.view.custom.DialogNotification;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESController implements ActionListener {
    private PANEL_AES view;
    private AES model;
    private Frame frame;
    public AESController(PANEL_AES view, AES model, Frame frame) {
        this.view = view;
        this.model = model;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "change Key Size":
                changeKeySise();
                break;
            case "GenKey":
                try {
                    genKey();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "LoadKey":
                loadKey();
                break;
            case "Encrypt String":
                try {
                    eventEncryptStr();
                } catch (InvalidAlgorithmParameterException ex) {
                    throw new RuntimeException(ex);
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
                break;
            case "Decrypt String":
                try {
                    eventDecryptStr();
                } catch (InvalidAlgorithmParameterException ex) {
                    throw new RuntimeException(ex);
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
                break;
            case "ChooseFileIn":
                eventChooseFleIn();
                break;
            case "ChooseFileOut":
                view.getTfOutputFile().setText(FileManager.getInstance().chooseFileToSave(frame));
                break;
            case "Encrypt file":
                try {
                    eventEncryptFile();
                } catch (InvalidAlgorithmParameterException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalBlockSizeException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (BadPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Decrypt file":
                try {
                    eventDecryptFile();
                } catch (InvalidAlgorithmParameterException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalBlockSizeException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (BadPaddingException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            default:
                break;
        }

    }

    private void changeKeySise() {
        Dialog dialog = new Dialog(frame, "Change Key Sise",false);
        dialog.setLayout(new BorderLayout(5, 5));
        dialog.setSize(new Dimension(300, 300));

        dialog.add(new JLabel("Choose key size:",SwingUtilities.CENTER), BorderLayout.NORTH);
        JPanel panelCenter = new JPanel();
        dialog.add(panelCenter, BorderLayout.CENTER);
        for (int i = 0;i <= model.listKeySize.length-1;i++ ){
            JButton button = new JButton(model.listKeySize[i]+"");
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.setKeySize(model.listKeySize[finalI]);
                    view.getTfKeySize().setText(button.getText());
                    dialog.dispose();
                }
            });

            panelCenter.add(button);
        }

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void genKey() throws NoSuchAlgorithmException {
        SecretKey key = model.genKey();
        view.getTfGenKey().setText(Base64.getEncoder().encodeToString(key.getEncoded()));
    }

    private void loadKey() {
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
//                  ep kieu string sang secrect key
            SecretKey keyTest =model.stringToSecretKey(load);
            setLoadKeyText(keyTest);
//              add key from this tool
        }else {
            SecretKey keyTest = model.stringToSecretKey(gen);
            setLoadKeyText(keyTest);
        }
    }

    private void setLoadKeyText(SecretKey keyTest) {
        if(keyTest != null){
            model.loadKey(keyTest);
            view.getTfLoadKey().setText("");
            view.getLbKeyInfoShow().setText(KeyManager.getInstance().SecretKeyToString(keyTest));
        }else{
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("Key is not valid. please change!");
            dialog.setVisible(true);
        }
    }

    private void eventEncryptStr() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
                DialogNotification dialog = new DialogNotification(frame, "Encryption failed!", true);
        switch (checkBeforEncryption()){
            case 1:
                dialog.setMessage("key does not exist!");
                dialog.setVisible(true);
                break;
            case 2:
                dialog.setMessage("input String is null!");
                dialog.setVisible(true);
                break;

            default:
                view.getTfOutputString().setText(model.encryptBase64(view.getTfInputString().getText()));
                break;
        }

    }

    private void eventDecryptStr() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        DialogNotification dialog = new DialogNotification(frame, "Encryption failed!", true);
        switch (checkBeforEncryption()){
            case 1:
                dialog.setMessage("key does not exist!");
                dialog.setVisible(true);
                break;
            case 2:
                dialog.setMessage("input String is null!");
                dialog.setVisible(true);
                break;

            default:
                view.getTfOutputString().setText(model.decryptStr(Base64.getDecoder().decode(view.getTfInputString().getText())));
                break;
        }
    }

    private void eventEncryptFile() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, BadPaddingException, InvalidKeyException {
        if (checkBeforEncryptionFile()) {
            if (model.encryptFile(view.getTfInputFile().getText(), view.getTfOutputFile().getText())) {
                DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
                dialog.setMessage("encrypted file successfully!");
                dialog.setVisible(true);
            } else {
                DialogNotification dialog = new DialogNotification(frame, "Error!", true);
                dialog.setMessage("encrypted file is not valid!");
                dialog.setVisible(true);
            }
        }
    }

    private void eventDecryptFile() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, BadPaddingException, InvalidKeyException {
        if (checkBeforEncryptionFile()) {
          if (model.decryptFile(view.getTfInputFile().getText(), view.getTfOutputFile().getText())) {
            DialogNotification dialog = new DialogNotification(frame, "Encryption successful!", true);
            dialog.setMessage("decrypted file successfully!");
            dialog.setVisible(true);
          }else{
              DialogNotification dialog = new DialogNotification(frame, "Error!", true);
              dialog.setMessage("Decrypted file is not valid!");
              dialog.setVisible(true);
          }
        }
    }

    private int checkBeforEncryption() {
        if (model.getKey() == null) {
            return 1;
        }
        if(view.getTfInputString().getText().trim().isEmpty()){
            return 2;
        }
        return 0;
    }

    private boolean checkBeforEncryptionFile() {
        CheckFile checkFile = CheckFile.getInstance();
        if (model.getKey() == null) {
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("empty key");
            dialog.setVisible(true);
            return false;
        }
        String pathIn = view.getTfInputFile().getText().trim();
        String pathOut = view.getTfOutputFile().getText().trim();
        if (pathIn.equals("") || pathOut.equals("")) {
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("empty file");
            dialog.setVisible(true);
            return false;
        }
        if (!checkFile.checkFileExisted(pathIn)) {
            DialogNotification dialogNotification1 = new DialogNotification(frame, "Error!", true);
            dialogNotification1.setMessage("The system cannot find the file specified to encryption");
            dialogNotification1.setVisible(true);
            return false;
        }
        if (!checkFile.checkDirectoryExisted(pathOut)) {
            DialogNotification dialogNotification1 = new DialogNotification(frame, "Error!", true);
            dialogNotification1.setMessage("The system cannot find the Directory specified to save file!");
            dialogNotification1.setVisible(true);
            return false;
        }
        if (checkFile.checkFileExisted(pathOut)) {
            DialogNotification dialogNotification1 = new DialogNotification(frame, "Error!", true);
            dialogNotification1.setMessage("The file result has existed! please change.");
            dialogNotification1.setVisible(true);
            return false;

        }
        return true;

    }

    private void eventChooseFleIn() {
        String file = FileManager.getInstance().chosoeFile(frame);
        view.getTfInputFile().setText(file);
        if (!file.endsWith(".crypt")) {
            file += ".crypt";
        } else {
            file = file.replace(".crypt", "");
        }
        view.getTfOutputFile().setText(file);
    }

}
