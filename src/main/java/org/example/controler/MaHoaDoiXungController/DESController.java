package org.example.controler.MaHoaDoiXungController;

import org.example.model.CheckFile;
import org.example.model.KeyManager;
import org.example.model.MaHoaDoiXungModel.DES;
import org.example.view.MaHoaDoiXungView.DES_PANEL;
import org.example.view.custom.DialogNotification;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DESController implements ActionListener {
    private SecretKey key;
    private DES_PANEL view;
    private DES model;
    private Frame frame;
    private CheckFile checkFile;
    public DESController(DES_PANEL aesView, DES desModel, Frame frame) {
        this.frame = frame;
        this.view = aesView;
        this.model = desModel;
        this.checkFile = new CheckFile();
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
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Decrypt file":
                try {
                    this.eventDecryptFile();
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
            case "ChooseFileIn":
                String path = chosoeFile();
                view.getTfInputFile().setText(path);
                break;
            case "ChooseFileOut":
                String pathOut = chooseFileToSave();
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
            key = model.GenKey();
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
        if(key == null ||  view.getTfGenKey().getText().trim().equals("") || view.getTfGenKey().getText().trim().equals("")){
//                    thong bao
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("empty key");
        } else{
            String input = view.getTfInputString().getText().trim();

            try {
                String output = Base64.getEncoder().encodeToString( model.encryptBase64(input));
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
    public void eventDecryptStr(){
        if(key == null ||  view.getTfGenKey().getText().trim().equals("") || view.getTfGenKey().getText().trim().equals("")){
//                    thong bao
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("empty key");
        } else{
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
            }
        }
    }
    public void eventEncryptFile() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IOException {
        if(key == null){
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
    public void eventDecryptFile() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, BadPaddingException, InvalidKeyException {
        if(key == null){
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
    public String chosoeFile(){
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return "";
    }
    private String chooseFileToSave() { // Tạo JFileChooser chỉ cho phép chọn thư mục
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Chọn tệp (not thư mục)

        // Tạo chế độ để người dùng chọn thư mục và tên tệp
        fileChooser.setDialogTitle("Choose file to save");
        fileChooser.setAcceptAllFileFilterUsed(false);

        // Hiển thị hộp thoại và lấy đường dẫn khi người dùng chọn tệp
        int returnValue = fileChooser.showSaveDialog(frame);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn của file đã chọn
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            return filePath;
        } else {
          return "";
        }

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
