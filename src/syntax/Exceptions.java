package syntax;


public class Exceptions {

    public class MyException extends Exception {
        MyException() {
            super("This is MyException");
        }
    }

    public class MyThrowable extends RuntimeException {
        MyThrowable() {
            super("This is MyThrowable");
        }
    }

    Exceptions() {

        try {
            throw new MyThrowable();
        }
        catch (MyThrowable ex) {
            System.out.println("My Throwable Exception is caught!");
        }
        catch (Exception ex) {
            System.out.println("Exception is caught!");
        }
        catch (Throwable ex) {
            System.out.println("Throwable Exception is caught!");
        }

        try {
            throw new MyException();
        }
        catch (MyException ex) {
            System.out.println("My Exception is caught!");
        }
        catch (Exception ex) {
            System.out.println("Exception is caught!");
        }
        catch (Throwable ex) {
            System.out.println("Throwable Exception is caught!");
        }

        try {
            throw new Exception();
        }
        catch (MyException ex) {
            System.out.println("My Exception is caught!");
        }
        catch (Exception ex) {
            System.out.println("Exception is caught!");
        }
        catch (Throwable ex) {
            System.out.println("Throwable Exception is caught!");
        }
    }

    public String ex() {
        try {
            //return "";
            if (true) return "Return from try block";
            throw new MyException();
        }
        catch (MyException | NullPointerException ex) {
            System.out.println("My Exception is caught in ex() method");
            return "Return from catch block";
        }
        finally {
            return "Return from finally block";
        }
    }

    public static void main (String... args) {
        System.out.println(new Exceptions().ex());
    }
}
