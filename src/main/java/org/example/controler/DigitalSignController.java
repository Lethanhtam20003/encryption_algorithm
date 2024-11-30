package org.example.controler;

import org.example.model.CheckFile;
import org.example.model.keySign.DigitalSign;
import org.example.view.FileManager;
import org.example.view.custom.DialogNotification;
import org.example.view.digitalSign.PanelDigitalSign;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidKeyException;
import java.security.SignatureException;

public class DigitalSignController implements ActionListener {
    private PanelDigitalSign view;
    private DigitalSign model;
    private JFrame frame;
    public DigitalSignController(PanelDigitalSign view, DigitalSign model, JFrame frame) {
        this.view = view;
        this.model = model;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()){
            case "Gen Public Key":
                eventGenPublicKey();
                break;
            case "Gen Private Key":
                eventGenPrivateKey();
                break;
            case "Load Public Key":
                eventLoadPublicKey();
                break;
            case "Load Private Key":
                eventLoadPrivateKey();
                break;
            case "Hash String":
                try {
                    eventHashString();
                } catch (SignatureException ex) {
                    throw new RuntimeException(ex);
                } catch (InvalidKeyException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Verify String":
                eventVerifyString();
                break;
            case "Hash File":
                eventHashFile();
                break;
            case "Verify File":
                eventVerifyFile();
                break;
            case "Choose File1":
                eventChooseFile(1);
                break;
            case "Choose File2":
                eventChooseFile(2);
                break;
        }

    }

    private void eventGenPublicKey() {
        String plKey = model.keyToString(model.genPublicKey());
        view.getTfGenPublicKey().setText(plKey);
    }

    private void eventGenPrivateKey() {
        String pvKey = model.keyToString(model.genPrivateKey());
        view.getTfGenPrivateKey().setText(pvKey);
    }

    private void eventLoadPublicKey() {
        String gen = view.getTfGenPublicKey().getText();
        String load = view.getTfLoadPublicKey().getText();
//                 input load key empty
        if(gen.isEmpty() && load.isEmpty()){
//                    notification
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("load public key is empty.\n please add key!");
            dialog.setVisible(true);
//                 add String key from user
        }else if(!load.isEmpty()){
            if(!model.LoadPublicKey(load)) {
                DialogNotification d = new DialogNotification(frame, "Notification!", true);
                d.setMessage("public key is Invalid");
                d.setVisible(true);
            }
//              add key from this tool
        }else {
            if(!model.LoadPublicKey(gen)) {
                DialogNotification d = new DialogNotification(frame, "Notification!", true);
                d.setMessage("public key is Invalid");
                d.setVisible(true);
            }
        }
        if(model.getPublicKey() != null){
            view.getLbPublicKeyInfoShow().setText(model.keyToString(model.getPublicKey()));
        }

    }

    private void eventLoadPrivateKey() {
        String gen = view.getTfGenPrivateKey().getText();
        String load = view.getTfLoadPrivateKey().getText();
//                 input load key empty
        if(gen.isEmpty() && load.isEmpty()){
//                    notification
            DialogNotification dialog = new DialogNotification(frame, "Notification!", true); // true: Modal dialog
            dialog.setMessage("load Private key is empty.\n please add key!");
            dialog.setVisible(true);
//                 add String key from user
        }else if(!load.isEmpty()){
            if(!model.LoadPrivateKey(load)) {
                DialogNotification d = new DialogNotification(frame, "Notification!", true);
                d.setMessage("Private key is Invalid");
                d.setVisible(true);
            }
//              add key from this tool
        }else {
            if(!model.LoadPrivateKey(gen)) {
                DialogNotification d = new DialogNotification(frame, "Notification!", true);
                d.setMessage("Private key is Invalid");
                d.setVisible(true);
            }
        }
        if(model.getPrivateKey() != null){
            view.getLbPrivateKeyInfoShow().setText(model.keyToString(model.getPrivateKey()));
        }

    }

    private void eventHashString() throws SignatureException, InvalidKeyException {
        if(!checkPrivateKey()){
            return;
        }
        String input = view.getTfInputString().getText();
        if(input.isEmpty()){
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("hash string is empty.\n please add message!");
            dialog.setVisible(true);
        }else {
            try{
                String hash = model.sign(input);
                view.getTfOutputString1().setText(hash);
            }catch (Exception e){
                DialogNotification d = new DialogNotification(frame, "Error!", true);
                d.setMessage("hash string is Invalid");
                d.setVisible(true);
            }
        }
    }

    private boolean checkPrivateKey() {
        if(model.getPrivateKey() == null){
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("Private key is empty.\n please add key!");
            dialog.setVisible(true);
            return false;
        }
        return true;
    }
    private boolean checkPublicKey() {
        if(model.getPublicKey() == null){
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("public key is empty.\n please add key!");
            dialog.setVisible(true);
            return false;
        }
        return true;
    }


    private void eventVerifyString() {
        System.out.println("s");
        String signStr = view.getTfOutputString2().getText();
        String verifyStr = view.getTfVerifyString().getText();
        if(!checkPublicKey()){
            return;
        }
        if(signStr.isEmpty()){
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("Sign string is empty.\n please add!");
            dialog.setVisible(true);
        }else if(verifyStr.isEmpty()) {
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("verify string is empty.\n please add!");
            dialog.setVisible(true);
        }else {
            try{
                if(model.verify(verifyStr,signStr)){
                    DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
                    dialog.setMessage("verify string is Valid");
                    dialog.setVisible(true);
                }
            }catch (Exception e){
                DialogNotification d = new DialogNotification(frame, "Error!", true);
                d.setMessage("hash string is Invalid");
                d.setVisible(true);
            }
        }

    }


    private void eventHashFile() {
        System.out.println("s");
        if(!checkPrivateKey()){
            return;
        }
        String path = view.getTfInputFile().getText();
        if(path.isEmpty()) {
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("verify string is empty.\n please add!");
            dialog.setVisible(true);
            return;
        }
        if (!CheckFile.getInstance().checkFileExisted(path)){
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("Hash Success");
            dialog.setVisible(true);
            return;
        }
        try{
            String hash = model.signFile(path);
            view.getTfOutputFile1().setText(hash);
        }catch (Exception e){
            DialogNotification d = new DialogNotification(frame, "Error!", true);
            d.setMessage("hash file is Invalid");
            d.setVisible(true);
        }

    }

    private void eventVerifyFile() {
        if(!checkPrivateKey()){
            return;
        }
        String path = view.getTfVerifyFile().getText();
        String sign = view.getTfOutputFile2().getText();
        System.out.println(path+"sign"+sign);

        if(path.isEmpty()) {
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("verify file is empty.\n please add!");
            dialog.setVisible(true);
            return;
        }
        if(sign.isEmpty()) {
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("verify file is empty.\n please add!");
            dialog.setVisible(true);
            return;
        }

        if(!CheckFile.getInstance().checkFileExisted(path)){
            DialogNotification dialog = new DialogNotification(frame, "Error!", true);
            dialog.setMessage("hash file is Invalid");
            dialog.setVisible(true);
            return;
        }
        try{
            if(model.verifyFile(path,sign)){
                DialogNotification dialog = new DialogNotification(frame, "Notification!", true);
                dialog.setMessage("verify hash Success");
                dialog.setVisible(true);
            }
        }catch (Exception e){
            DialogNotification d = new DialogNotification(frame, "Error!", true);
            d.setMessage("sign is Invalid");
            d.setVisible(true);
        }
    }

    private void eventChooseFile(int i) {
        String file = FileManager.getInstance().chosoeFile(frame);
        if (i ==1){
            view.getTfInputFile().setText(file);
        }else
            view.getTfVerifyFile().setText(file);
    }
}
