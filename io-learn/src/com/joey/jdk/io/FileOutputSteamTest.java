package com.joey.jdk.io;

import java.io.*;

public class FileOutputSteamTest {

    public static void main(String[] args) {

        String content = "my first file content";
        try {
            File file = new File("D:\\workspace\\IDEA\\java-learn\\io-learn\\src\\com\\joey\\jdk\\io\\a.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
