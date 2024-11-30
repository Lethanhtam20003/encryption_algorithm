package org.example.view.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogNotification extends JDialog {
    private final JPanel buttonPanel;
    private JLabel lblTitle;
    private JTextArea txtArea;
    public DialogNotification(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.setSize(300, 200);
        this.setLocationRelativeTo(owner);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        lblTitle = new JLabel(title);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        // Nội dung của JDialog
        txtArea = new JTextArea();
        txtArea.setEditable(false);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(txtArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        // Tạo nút OK
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Đóng dialog
            }
        });

        // Thêm nút OK vào phần dưới
        buttonPanel = new JPanel();
        buttonPanel.add(btnOK);

        lblTitle.setFont(new FontCustom().titleFont2);
        txtArea.setFont(new FontCustom().titleFont3);


        this.setModal(true);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.add(lblTitle, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void setMessage(String s) {
        txtArea.setText(s);
    }
}
