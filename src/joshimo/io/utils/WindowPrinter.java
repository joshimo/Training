package joshimo.io.utils;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;

public class WindowPrinter extends JFrame {

    private JButton saveButton;
    private JButton hideButton;
    private JButton exitButton;
    protected JTextPane textPane;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private static Style conStyle;
    private static Style outStyle;
    private static Style exceptionStyle;

    protected class Timer extends Date {

        @Override
        @SuppressWarnings("deprecation")
        public String toGMTString() {
            StringBuilder localTime = new StringBuilder();
            localTime.append(super.getHours())
                     .append(":")
                     .append(super.getMinutes())
                     .append(":")
                     .append(super.getSeconds());
            return localTime.toString();
        }
    }

    private static WindowPrinter printer = new WindowPrinter();

    protected WindowPrinter() {
        this.setBounds(100,100,1024, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setName("WindowPrinter");
        this.setLayout(null);
        textPane = new JTextPane();
        conStyle = textPane.addStyle("conStyle", null);
        outStyle = textPane.addStyle("outStyle", null);
        exceptionStyle = textPane.addStyle("exceptionStyle", null);
        StyleConstants.setForeground(conStyle, Color.gray);
        StyleConstants.setItalic(conStyle, true);
        StyleConstants.setForeground(outStyle, Color.BLACK);
        StyleConstants.setForeground(exceptionStyle, Color.RED);
        textPane.setEditable(false);
        scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(0,10,this.getWidth() - 12,this.getHeight() - 120);
        hideButton = new JButton("Hide");
        hideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowPrinter.this.setVisible(false);
            }
        });
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (save())
                    print("Log saved!");
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
        buttonPanel.add(hideButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);
        this.add(scrollPane);
        this.add(buttonPanel);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
               resize();
            }
        });
        this.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                resize();
            }
        });
    }

    private void resize() {
        scrollPane.setBounds(0,10,WindowPrinter.this.getWidth() - 15, WindowPrinter.this.getHeight() - 100);
        buttonPanel.setBounds(0, WindowPrinter.this.getHeight() - 80, WindowPrinter.this.getWidth() - 15, 60);
    }

    public static void print(Object object) {
        printer.print0(object.toString(), outStyle);
    }

    public static void print(Exception e) {
        StringBuilder stack = new StringBuilder(e.toString() + "\n");
        StackTraceElement[] ste = e.getStackTrace();
        for (StackTraceElement element : ste)
            stack.append("at " + element.toString() + "\n");
        printer.print0(stack.toString(), exceptionStyle);
    }

    public static void print(String message) {
        printer.print0(message, outStyle);
    }

    protected void print0(String message, Style style) {
        if (!isVisible())
            setVisible(true);
        try {
            Document document = this.textPane.getDocument();
            if (document.getLength() == 0)
                textPane.getDocument().insertString(0, "Starting at " + new Timer().toString(), conStyle);
            document.insertString(document.getLength(), "\n" + new Timer().toGMTString() +  "-->", conStyle);
            document.insertString(document.getLength(), message, style);
        } catch (BadLocationException ble){}
        this.textPane.setCaretPosition(textPane.getDocument().getLength());
    }

    private boolean save(String fileName) {
        FileOutputStream stream;
        File file = new File(fileName);
        try {
            stream = new FileOutputStream(file, false);
            byte[] b = textPane.getText().getBytes();
            stream.write(b);
            stream.close();
            return true;
        }
        catch (IOException ioe) {
            print(ioe);
            return false;
        }
    }

    private boolean save() {
        return save("WindowLogger.log");
    }
}