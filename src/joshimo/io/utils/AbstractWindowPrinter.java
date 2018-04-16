package joshimo.io.utils;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public abstract class AbstractWindowPrinter extends JFrame {
    private JButton saveButton;
    private JButton hideButton;
    private JButton exitButton;
    protected JTextPane textPane;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private Style conStyle;
    private Style outStyle;
    private Style exceptionStyle;
    private String preffix = "";
    private String header = "";
    protected String fileName;

    protected void setPreffix(String preffix) {
        this.preffix = preffix;
    }

    protected void setHeader(String header) {
        this.header = header;
    }

    protected String getHeader() {
        return header;
    }

    private class Timer extends Date {

        @Override
        @SuppressWarnings("deprecation")
        public String toGMTString() {
            StringBuilder localTime = new StringBuilder();
            localTime.append(getHours())
                    .append(":")
                    .append(getMinutes())
                    .append(":")
                    .append(getSeconds());
            return localTime.toString();
        }
    }

    protected AbstractWindowPrinter() {
        this.setBounds(100,100,1024, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        hideButton.addActionListener((ActionEvent e) -> AbstractWindowPrinter.this.setVisible(false));
        saveButton = new JButton("Save");
        saveButton.addActionListener((ActionEvent e) -> {
                if (fileName == null || fileName.isEmpty())
                    print0("File name not set! Log not saved.", exceptionStyle);
                else if (save(fileName))
                    print0("Log saved to " + fileName, conStyle);
            });
        exitButton = new JButton("Exit");
        exitButton.addActionListener((ActionEvent e) -> System.exit(0));
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
        this.addWindowStateListener((WindowEvent e) -> resize());
    }

    private void resize() {
        scrollPane.setBounds(0,10,AbstractWindowPrinter.this.getWidth() - 15, AbstractWindowPrinter.this.getHeight() - 100);
        buttonPanel.setBounds(0, AbstractWindowPrinter.this.getHeight() - 80, AbstractWindowPrinter.this.getWidth() - 15, 60);
    }

    protected void print0(String message, Style style) {
        print0(message, style, preffix);
    }

    protected void print0(String message, Style style, String conPrexffix) {
        if (!isVisible())
            setVisible(true);
        try {
            Document document = this.textPane.getDocument();
            if (document.getLength() == 0)
                textPane.getDocument().insertString(0, header, conStyle);
            document.insertString(document.getLength(), "\n" + conPrexffix, conStyle);
            document.insertString(document.getLength(), message, style);
        } catch (BadLocationException ble){}
        this.textPane.setCaretPosition(textPane.getDocument().getLength());
    }

    protected abstract boolean save(String fileName);
}