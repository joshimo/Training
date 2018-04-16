package joshimo.io.utils;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class WindowLogger extends AbstractWindowPrinter {

    private Level level;
    private boolean printStackTrace;
    private static WindowLogger logger = new WindowLogger();
    private Style finestStyle;
    private Style finerStyle;
    private Style fineStyle;
    private Style configStyle;
    private Style infoStyle;
    private Style warningStyle;
    private Style severeStyle;
    private Style anywayStyle;
    private Style defaultStyle;
    private StringBuilder htmlProtocol;
    private ArrayList<LoggerMessage> loggerMessageList;

    private class Timer extends Date {

        @Override
        @SuppressWarnings("deprecation")
        public String toGMTString() {
            StringBuilder localTime = new StringBuilder();
            localTime.append(getHours() < 10 ? "0" + getHours() : getHours())
                    .append(":")
                    .append(getMinutes() < 10 ? "0" + getMinutes() : getMinutes())
                    .append(":")
                    .append(getSeconds() < 10 ? "0" + getSeconds() : getSeconds());
            return localTime.toString();
        }
    }

    private class LoggerMessage {
        private String preffix;
        private String text;
        private Level level;
        private String stack;

        public LoggerMessage(Level level, String preffix, String text) {
            this.preffix = preffix;
            this.text = text;
            this.level = level;
            this.stack = "";
        }

        public LoggerMessage(Level level, String preffix, String text, String stack) {
            this.preffix = preffix;
            this.text = text;
            this.level = level;
            this.stack = stack;
        }

        public String getPreffix() {
            return preffix;
        }

        public String getText() {
            return text;
        }

        public Level getLevel() {
            return level;
        }

        public String getStack() {
            return stack;
        }
    }

    public static WindowLogger getInstance() {
        return logger;
    }

    public void setLevel(Level level) {
        this.level = level;
        switch (this.level) {
            case FINEST: defaultStyle = finestStyle; break;
            case FINER: defaultStyle = finerStyle; break;
            case FINE: defaultStyle = fineStyle; break;
            case CONFIG: defaultStyle = configStyle; break;
            case INFO: defaultStyle = infoStyle; break;
            case WARNING: defaultStyle = warningStyle; break;
            case SEVERE: defaultStyle = severeStyle; break;
            default: defaultStyle = finestStyle; break;
        }
    }

    public void showStackTrace(boolean printStackTrace) {
        this.printStackTrace = printStackTrace;
    }

    public void setFileName(String fileName) {
        super.fileName = fileName;
    }

    private WindowLogger() {
        setHeader(new Timer().toString());
        loggerMessageList = new ArrayList<>();
        createLevelStyles();
        level = Level.FINEST;
        defaultStyle = finestStyle;
        fileName = "WindowLogger.html";
    }

    private void createLevelStyles() {
        finestStyle = textPane.addStyle("finestStyle", null);
        finerStyle = textPane.addStyle("finerStyle", null);
        fineStyle = textPane.addStyle("fineStyle", null);
        configStyle = textPane.addStyle("configStyle", null);
        infoStyle = textPane.addStyle("infoStyle", null);
        warningStyle = textPane.addStyle("warningStyle", null);
        severeStyle = textPane.addStyle("severeStyle", null);
        anywayStyle = textPane.addStyle("anywayStyle", null);
        StyleConstants.setForeground(finestStyle, Color.gray);
        StyleConstants.setForeground(finerStyle, Color.darkGray);
        StyleConstants.setForeground(fineStyle, Color.black);
        StyleConstants.setForeground(configStyle, Color.green);
        StyleConstants.setForeground(infoStyle, Color.blue);
        StyleConstants.setForeground(warningStyle, Color.pink);
        StyleConstants.setForeground(severeStyle, Color.red);
        StyleConstants.setForeground(anywayStyle, Color.black);
    }

    private void createHTMLProtocol() {
        htmlProtocol = new StringBuilder().append("<!DOCTYPE html>\n")
                .append("<style type=\"text/css\">\n")
                .append(".finest {\n\tcolor: rgb(150,150,150);\n}\n")
                .append(".finer {\n\tcolor: rgb(90,90,90);\n}\n")
                .append(".fine {\n\tcolor: rgb(0,0,0);\n}\n")
                .append(".config {\n\tcolor: rgb(0,150,0);\n}\n")
                .append(".info {\n\tcolor: rgb(120,120,0);\n}\n")
                .append(".warning {\n\tcolor: rgb(90,0,0);\n}\n")
                .append(".severe {\n\tcolor: rgb(255,0,0);\n}\n")
                .append(".console {\n\tcolor: rgb(150,150,150);\n\tfont-style: italic;\n}\n")
                .append("</style>\n")
                .append("<body>\n")
                .append("\t<div class=\"console\">" + getHeader() + "</div>");
        for (LoggerMessage record : loggerMessageList) {
            htmlProtocol.append("\n\t<div class=\"" + record.getLevel().name().toLowerCase() + "\">" +
                    "\n\t\t<span class=\"console\">" + record.getPreffix().replaceAll("\n", "<br/>\n") + "</span>" +
                    record.getText().replaceAll("\n", "<br/>\n") + "\n\t</div>");
        }
        htmlProtocol.append("\n</body>\n</html>");
    }

    public void log(String message) {
        log(level, message);
    }

    public void log(Level level, String message) {
        String stack = "";
        if (printStackTrace) {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            if (elements.length > 1)
                for (int index = 1; index < elements.length; index ++)
                    stack += "\n" + elements[index].getClassName() + "." + elements[index].getMethodName() + "(line " + elements[index].getLineNumber() + ")";
        }
        String preffix = new Timer().toGMTString() + " " + stack + "-->";
        if (level.ordinal() >= this.level.ordinal())
            switch (level) {
                case FINEST: {
                    loggerMessageList.add(new LoggerMessage(Level.FINEST, preffix, message, stack));
                    print0(message, finestStyle, preffix);
                } break;
                case FINER: {
                    loggerMessageList.add(new LoggerMessage(Level.FINER, preffix, message, stack));
                    print0(message, finerStyle, preffix);
                } break;
                case FINE: {
                    loggerMessageList.add(new LoggerMessage(Level.FINE, preffix, message, stack));
                    print0(message, fineStyle, preffix);
                } break;
                case CONFIG: {
                    loggerMessageList.add(new LoggerMessage(Level.CONFIG, preffix, message, stack));
                    print0(message, configStyle, preffix);
                } break;
                case INFO: {
                    loggerMessageList.add(new LoggerMessage(Level.INFO, preffix, message, stack));
                    print0(message, infoStyle, preffix);
                } break;
                case WARNING: {
                    loggerMessageList.add(new LoggerMessage(Level.WARNING, preffix, message, stack));
                    print0(message, warningStyle, preffix);
                } break;
                case SEVERE: {
                    loggerMessageList.add(new LoggerMessage(Level.SEVERE, preffix, message, stack));
                    print0(message, severeStyle, preffix);
                } break;
                case ANYWAY: {
                    loggerMessageList.add(new LoggerMessage(Level.ANYWAY, preffix, message, stack));
                    print0(message, anywayStyle, preffix);
                } break;
                default: {
                    loggerMessageList.add(new LoggerMessage(Level.FINEST, preffix, message, stack));
                    print0(message, defaultStyle);
                } break;
            }
    }

    public void log(Object obj) {
        log(level, obj);
    }

    public void log(Level level, Object obj) {
        log(level, obj.toString());
    }

    public void log(Exception e) {
        log(Level.SEVERE, e);
    }

    public void log(Level level, Exception e) {
        StringBuilder stack = new StringBuilder(e.toString());
        StackTraceElement[] ste = e.getStackTrace();
        for (StackTraceElement element : ste)
            stack.append("\n  at " + element.toString());
        log(level, stack.toString());
    }

    public <T> void log(T t, Predicate<T> predicate) {
        log(predicate.test(t));
    }

    public <T> void log(T t, UnaryOperator<T> operator) {
        log(operator.apply(t));
    }

    public <T, R> void log (T t, Function<T, R> function) {
        log(function.apply(t).toString());
    }

    @Override
    protected boolean save(String fileName) {
        createHTMLProtocol();
        FileOutputStream stream;
        File file = new File(fileName);
        if ((file.getParentFile() != null) && (!file.getParentFile().exists()))
            file.getParentFile().mkdirs();
        try {
            stream = new FileOutputStream(file, false);
            byte[] b = htmlProtocol.toString().getBytes();
            stream.write(b);
            stream.close();
            return true;
        }
        catch (IOException ioe) {
            log(ioe);
            return false;
        }
    }
}