package com.javaops.webapp;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
//        String filePath = ".\\.gitignore";
//
//        File file = new File(filePath);
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }
//
//        File dir = new File("./src/com/javaops/webapp");
//        System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String name : list) {
//                System.out.println(name);
//            }
//        }
//
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        File[] files = new File("..\\basejava\\src").listFiles();
        showFiles(files, "");
    }

    public static void showFiles(File[] files, String tab) {
        Objects.requireNonNull(files, "files must not be null");
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(tab + "Directory: " + file.getName());
                showFiles(file.listFiles(), tab + "\t");
            } else if (file.isFile()) {
                System.out.println(tab + "File: " + file.getName());
            }
        }
    }
}
