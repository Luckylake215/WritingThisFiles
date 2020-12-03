package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class COPY {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите копируемый каталог");
        String source = scanner.nextLine();

        System.out.println("Куда вставить?");
        String destination = scanner.nextLine();

        try {
            copyDirectory(new File(source), new File(destination + source.substring(source.lastIndexOf("\\"))));
            System.out.println("Копирование произошло...");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка ");
        }
    }

    public static void copyDirectory(File source, File destination) throws IOException {

        if (source.isDirectory()) {

            if (!destination.exists())
                destination.mkdir();

            String[] files = source.list();

            if (files == null) {
                return;
            }

            for (String file : files) {
                File srcFile = new File(source, file);
                File destFile = new File(destination, file);
                copyDirectory(srcFile, destFile);
            }

        } else {
            try {

                FileInputStream in = new FileInputStream(source);
                FileOutputStream out = new FileOutputStream(destination);

                byte[] buffer = new byte[1024];

                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}