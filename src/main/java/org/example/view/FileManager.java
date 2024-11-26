package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileManager {
    public static FileManager instance;

    private FileManager() {
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public String chosoeFile(Frame frame) {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return "";
    }

    public String chooseFileToSave(Frame frame) { // Tạo JFileChooser chỉ cho phép chọn thư mục
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
            return selectedFile.getAbsolutePath();
        } else {
            return "";
        }

    }


}
