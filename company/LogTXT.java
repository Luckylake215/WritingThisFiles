package com.company;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogTXT {
    static Logger logger;
    public static void main(String[] args) {
        createLogFile();
        try {
            logger.log(Level.INFO, "1234455");
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "неправильно");
        }
    }

    public static void createLogFile() {
        File filePath = new File("log");
        filePath.mkdir();
        PrintStream logfile = null;
        try {
            logfile = new PrintStream(new FileOutputStream("log.txt"));
        }
        catch (FileNotFoundException e) {
            System.err.println("File no found!");
            //здесь либо как-то создавать файл самому либо:
            System.exit(1);
        }
        logfile.println("First note to logfile");
    }

}
