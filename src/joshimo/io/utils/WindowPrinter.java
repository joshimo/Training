package joshimo.io.utils;

import javax.swing.*;
import java.awt.event.*;
import java.util.Date;

public class WindowPrinter extends JFrame {

    private JButton hideButton;
    private JButton exitButton;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;

    private static WindowPrinter printer = new WindowPrinter();

    private WindowPrinter() {
        this.setBounds(100,100,1024, 720);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setName("WindowPrinter");
        this.setLayout(null);
        textArea = new JTextArea(new Date().toString());
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(0,10,this.getWidth() - 12,this.getHeight() - 120);
        hideButton = new JButton("Close");
        hideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowPrinter.this.setVisible(false);
            }
        });
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel = new JPanel();
        buttonPanel.setBounds(10, this.getHeight() - 100, this.getWidth() - 15, 50);
        buttonPanel.add(exitButton);
        buttonPanel.add(hideButton);
        this.add(scrollPane);
        this.add(buttonPanel);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scrollPane.setBounds(0,10,WindowPrinter.this.getWidth() - 15, WindowPrinter.this.getHeight() - 120);
                buttonPanel.setBounds(0, WindowPrinter.this.getHeight() - 100, WindowPrinter.this.getWidth() - 15, 50);
            }
        });
        this.setVisible(true);
    }

    public static void print(String message) {
        printer.print0(message);
    }

    private void print0(String message) {
        this.setVisible(true);
        this.textArea.append("\n-->" + message);
        this.textArea.setCaretPosition(textArea.getText().length());
    }
}