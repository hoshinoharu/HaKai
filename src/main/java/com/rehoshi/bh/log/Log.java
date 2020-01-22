package com.rehoshi.bh.log;

import java.util.Scanner;

public class Log {
    public static void log(Process process){
        Scanner log = new Scanner(process.getInputStream());
        while (log.hasNextLine()){
            System.out.println(log.nextLine());
        }

        Scanner error = new Scanner(process.getErrorStream());
        while (error.hasNextLine()){
            System.out.println(error.nextLine());
        }
    }
}

