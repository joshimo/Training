package io;


import java.io.*;

public class IoClass {

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();

        PrintStream print = System.out;
        print.println(str);
    }
}
