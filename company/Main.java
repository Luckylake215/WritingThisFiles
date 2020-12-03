package com.company;
import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long size = 0;
        System.out.println("Введите путь до папки");

        String path = scanner.nextLine();

        try {
            File file = new File(path);
            if (file.exists()) {
                String answer = null;
                {
                    size = FolderSize(file);

                    if (size < 1024) {
                        answer = String.valueOf(size) + " Bytes";
                    } else if (size < 1024 * 1024) {
                        answer = String.valueOf(size / 1024) + " KB";
                    } else if (size < 1024 * 1024 * 1024) {
                        answer = String.valueOf(size / (1024 * 1024)) + " MBytes";
                    } else if (size < 1024 * 1024 * 1024 * 1024) {
                        answer = String.valueOf(size / (1024 * 1024 * 1024)) + " GBytes";
                    }

                }
                System.out.println("Размер папки " + path + "составляет " + answer);
            }
            else
            {
                System.out.println("Такого каталога нет");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public static long FolderSize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else if (file.isDirectory())
                length += FolderSize(file);
        }
        return length;
    }

}