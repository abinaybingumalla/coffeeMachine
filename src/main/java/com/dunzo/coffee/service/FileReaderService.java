package com.dunzo.coffee.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderService {
    public List<String> loadData(String fileName) {
        String filePath = getClass().getClassLoader().getResource(fileName).getPath();
        List<String> beverageData = new ArrayList<>();
        Scanner sc = null;
        try {
            File file = new File(filePath);
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                beverageData.add(sc.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found Exception at filePath: " + filePath + " ExceptionMsg: " + ex);
        } finally {
            if (sc != null) sc.close();
        }
        return beverageData;
    }
}
