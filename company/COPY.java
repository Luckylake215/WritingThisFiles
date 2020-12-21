package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class COPY {
    public static void main(String[] args) {
        Logger logger = logFile();
        Scanner scanner = new Scanner(System.in);
        logger.log(Level.INFO,"Введите копируемый каталог");
        String source = scanner.nextLine();
        logger.log(Level.INFO, "source = " + source);
        logger.log(Level.INFO,"Куда вставить?");
        String destination = scanner.nextLine();
        logger.log(Level.INFO, "destination = " + destination);

        try {
            copyDirectory(new File(source), new File(destination + source.substring(source.lastIndexOf("\\"))));
            logger.log(Level.INFO, "Копирование произошло...");
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, " Wrong");
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
    public static Logger logFile() {
        Logger logger = Logger.getLogger(COPY.class.getName());
        FileHandler fh;

        try {
            fh = new FileHandler("log\\log.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            return logger;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}