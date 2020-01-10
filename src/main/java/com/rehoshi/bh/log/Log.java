package com.rehoshi.bh.log;

import java.util.Scanner;

public class Log {
    public static void log(Process process){
        Scanner scanner = new Scanner(process.getInputStream());
        while (scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
    }
}

