package com.javaops.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        printNameOfFiles(new File("./src/com/javaops/webapp"), "");
    }

    public static void printNameOfFiles(File file, String tab){
        if (file == null || !file.exists()) return;

        File[] files = file.listFiles();
        if (files == null) return;

        for (File f : files){
            if (f.isDirectory()){
                System.out.println(tab + f.getName());
                printNameOfFiles(f, tab + "\t");
            } else {
                System.out.println(tab + f.getName());
            }
        }
    }
}