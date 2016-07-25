package com.company;

import Services.ExecutorProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static ExecutorProcessor executor;
    private static String line;
    private static int number_line = 1;

    public static void main(String[] args) throws IOException {
        String filePathString = args[0];
        File file = new File(filePathString);
        executor = new ExecutorProcessor();
        System.out.println("Start work");
        if(file.exists() && !file.isDirectory()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while ((line = br.readLine()) != null) {
                    executor.addFunction(number_line, line);
                    number_line++;
                }
                executor.finished();
            }

        } else {
            System.out.println("Can't open file.");
        }

        System.out.println("Finish work");


    }
}
