package org.example.controler.hashController;

import org.example.model.hashModel.Hash;
import org.example.view.FileManager;
import org.example.view.hashView.HashView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class HashController implements ActionListener {
    private HashView view;
    private Hash model;
    private JFrame frame;

    public HashController(HashView view, Hash model, JFrame frame) {
        this.view = view;
        this.model = model;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Hash":
                try {
                    eventHashString();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
                break;

            case "ChooseFileIn":
                eventChoseFileIn();
                break;
            case "Hash File":
                try {
                    eventHashFile();
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
    }

    private void eventHashString() throws NoSuchAlgorithmException {
        String input = view.getTfInputString().getText().trim();
        if(!input.isEmpty()){
            String output = model.hash(input);
            view.getTfOutputString().setText(output);
        }
    }

    private void eventChoseFileIn() {
        String file = FileManager.getInstance().chosoeFile(frame);
        view.getTfInputFile().setText(file);
    }

    private void eventHashFile() throws NoSuchAlgorithmException, IOException {
        String in = view.getTfInputFile().getText().trim();
        String out = model.hashFile(in);
        System.out.println("ok"+out);
        view.getTfOutputFile().setText(out);
    }


}
