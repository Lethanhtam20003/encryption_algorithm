package org.example.controler.MaHoaDoiXungController;

import org.example.model.CheckFile;
import org.example.model.KeyManager;
import org.example.model.MaHoaDoiXungModel.DES;
import org.example.view.FileManager;
import org.example.view.MaHoaDoiXungView.DES_PANEL;
import org.example.view.custom.DialogNotification;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DESController implements ActionListener {
    private DES_PANEL view;
    private DES model;
    private Frame frame;
    public DESController(DES_PANEL aesView, DES desModel, Frame frame) {
        this.frame = frame;
        this.view = aesView;
        this.model = desModel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = "";
        String chucNang = e.getActionCommand();
        System.out.println(chucNang);
        switch (chucNang) {
            case "change Key Size":
                this.eventChangkeySize();
                break;
            case "GenKey":
               this.eventGenKey();
                break;
            case "LoadKey":
                try {
                    this.eventLoadKey();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Encrypt String":
              this.eventEncryptStr();
                break;
            case "Decrypt String":
               this.eventDecryptStr();
                break;
            case "Encrypt file":
                try {
                    this.eventEncryptFile();
                } catch (NoSuchPaddingException | IOException | InvalidAlgorithmParameterException |
                         InvalidKeyException | BadPaddingException | NoSuchAlgorithmException |
                         IllegalBlockSizeException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Decrypt file":
                try {
                    this.eventDecryptFile();
                } catch (NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException |
                         BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "ChooseFileIn":
                eventChooseFleIn();
                break;
            case "ChooseFileOut":
                String pathOut = FileManager.getInstance().chooseFileToSave(frame);
                view.getTfOutputFile().setText(pathOut);
                break;

        }
    }

    public void setKeySize(int keySize){
        this.model.setKeySize(keySize);

    }
    public void eventChangkeySize(){
        DialogNotification dlgChangeKeyLog = new DialogNotification(frame,"",true);
        dlgChangeKeyLog.setTitle("Change Key size");
        dlgChangeKeyLog.setMessage("The DES encryption algorithm only supports a fixed key size of 56!");
        dlgChangeKeyLog.setVisible(true);
    }
    public void eventGenKey(){
        try {
            SecretKey key = model.GenKey();
            view.getTfGenKey().setText(KeyManager.getInstance().SecretKeyToString(key));
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void eventLoadKey() throws NoSuchAlgorithmException {
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
            SecretKey keyTest =model.stringToSecretKey(load );
            setLoadKeyText(keyTest);
//              add key from this tool
        }else {
            SecretKey keyTest = model.stringToSecretKey(gen );
            setLoadKeyText(keyTest);
        }
    }
    public void eventEncryptStr(){
        if (model.getKey() == null || view.getTfGenKey().getText().trim().equals("") || view.getTfGenKey().getText().trim().equals("")) {
//                    thong bao
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("empty key");
            dialog.setVisible(true);
        } else if (view.getTfInputString().getText().trim().equals("")) {
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("input String is empty! please add text!");
            dialog.setVisible(true);
        } else {
            String input = view.getTfInputString().getText().trim();
            try {
                String output = Base64.getEncoder().encodeToString( model.encryptBase64(input));
                view.getTfOutputString().setText(output);

            } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                     BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void eventDecryptStr(){
        if (model.getKey() == null || view.getTfGenKey().getText().trim().equals("") || view.getTfGenKey().getText().trim().equals("")) {
//                    thong bao
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("empty key");
            dialog.setVisible(true);
        } else if (view.getTfInputString().getText().trim().equals("")) {
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("input String is empty! please add text!");
            dialog.setVisible(true);
        } else {
            String input = view.getTfInputString().getText().trim();

            try {
                byte[] ciphertext = Base64.getDecoder().decode(input);
                String output = model.decryptBase64(ciphertext);
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
            } catch (InvalidAlgorithmParameterException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void eventEncryptFile() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        CheckFile checkFile = CheckFile.getInstance();
        if (model.getKey() == null) {
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("empty key");
            dialog.setVisible(true);
            return;
        }
        String pathIn = view.getTfInputFile().getText().trim();
        String pathOut = view.getTfOutputFile().getText().trim();
        if(pathIn.equals("") || pathOut.equals("")){
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("empty file");
            dialog.setVisible(true);
            return;
        }
        if(!checkFile.checkFileExisted(pathIn)){
            DialogNotification dialogNotification1 = new DialogNotification(frame,"Error!",true);
            dialogNotification1.setMessage("The system cannot find the file specified to encryption");
            dialogNotification1.setVisible(true);
            return;
        }
        if(!checkFile.checkDirectoryExisted(pathOut)){
            DialogNotification dialogNotification1 = new DialogNotification(frame,"Error!",true);
            dialogNotification1.setMessage("The system cannot find the Directory specified to save file!");
            dialogNotification1.setVisible(true);
            return;
        }
        if (checkFile.checkFileExisted(pathOut)){
            DialogNotification dialogNotification1 = new DialogNotification(frame,"Error!",true);
            dialogNotification1.setMessage("The file result has existed! please change.");
            dialogNotification1.setVisible(true);
            return;

        }
        if(model.encryptFile(pathIn,pathOut)){
            DialogNotification dialog = new DialogNotification(frame,"Notification!", true);
            dialog.setMessage("encrypted file successfully!");
            dialog.setVisible(true);
        }else{
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("encrypted file is not valid!");
            dialog.setVisible(true);
        }

//        model.encryptStr(path);
    }

    public void eventDecryptFile() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        CheckFile checkFile = CheckFile.getInstance();
        if (model.getKey() == null) {
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("empty key");
            dialog.setVisible(true);
            return;
        }
        String pathIn = view.getTfInputFile().getText().trim();
        String pathOut = view.getTfOutputFile().getText().trim();
        if(pathIn.equals("") || pathOut.equals("")){
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
            dialog.setMessage("empty file");
            dialog.setVisible(true);
            return;

        }
        if(!checkFile.checkFileExisted(pathIn)){
            DialogNotification dialogNotification1 = new DialogNotification(frame,"Error!",true);
            dialogNotification1.setMessage("The system cannot find the file specified to decryption");
            dialogNotification1.setVisible(true);
            return;
        }
        if(!checkFile.checkDirectoryExisted(pathOut)){
            DialogNotification dialogNotification1 = new DialogNotification(frame,"Error!",true);
            dialogNotification1.setMessage("The system cannot find the Directory specified to save file!");
            dialogNotification1.setVisible(true);
            return;
        }
        if (checkFile.checkFileExisted(pathOut)){
            DialogNotification dialogNotification1 = new DialogNotification(frame,"Error!",true);
            dialogNotification1.setMessage("The file result has existed! please change.");
            dialogNotification1.setVisible(true);
            return;

        }
        if(model.decryptFile(pathIn,pathOut)){
            DialogNotification dialog = new DialogNotification(frame,"Notification!", true);
            dialog.setMessage("encrypted file successfully!");
            dialog.setVisible(true);
        }else{
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("encrypted file is not valid!");
            dialog.setVisible(true);
        }

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
    private void setLoadKeyText(SecretKey keyTest) throws NoSuchAlgorithmException {
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
}
